package be.gershon_lehrer.mybooksathome.controller;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.controller.fragments.BooksPagerFragment;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.SearchTypeLocalDB;
import be.gershon_lehrer.mybooksathome.model.SourceOfResults;

/**
 * Created by gershonlehrer on 07/07/16.
 */
public class BookItemView_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitle, mShortDescription, mAuthor, mBookDescription, mBookAuthor, mBookISBN;
    private CardView mCardView;
    private ImageView mBookThumbnail;
    private int itemPosition;
    private String mBookId;
    private Toolbar mToolbar;
private SourceOfResults mSourceOfResults;
    private RecyclerView mRecyclerView;

    public BookItemView_Holder(View itemView, SourceOfResults sourceOfResults) {
        super(itemView);
        mSourceOfResults = sourceOfResults;
        mCardView = (CardView) itemView.findViewById(R.id.cardViewBookConsiceOverView);
        mBookThumbnail = (ImageView) itemView.findViewById(R.id.book_imageView);
        mTitle = (TextView) itemView.findViewById(R.id.book_title);
        mAuthor = (TextView) itemView.findViewById(R.id.book_author);
        //mDescription=(TextView)itemView.findViewById(R.id.book_description);
        mShortDescription = (TextView) itemView.findViewById(R.id.book_short_description);
        mBookISBN = (TextView) itemView.findViewById(R.id.textViewISBN);


        //toolbar met drie bolletjes en menu laden en inflaten
        mToolbar = (Toolbar)itemView.findViewById(R.id.book_row_layout_card_toolbar);

        mToolbar.inflateMenu(R.menu.book_item_view_holder_toolbar_menu);




        itemView.setOnClickListener(this);

    }

    public void bindBook(final Book book, final int itemPosition) {
        //mBookThumbnail.setImageURI();
        Context context = App.getContext();

        mBookId = book.getId();
        int maxLengthTitle = 50;
        String title = book.getTitle();
        mTitle.setText(title.substring(0, title.length() > maxLengthTitle ? maxLengthTitle : title.length()));
        String authorNames = App.getContext().getString(R.string.Author_s);
        for (int i = 0; i < book.getAuthors().size(); i++) {
            authorNames += book.getAuthors().get(i).getName();
            //if there is a next author in the list, we need to add a newline and a comma
            if (i < book.getAuthors().size() - 1) authorNames += ",\n";
        }
        mAuthor.setText(authorNames);
        //mDescription.setText(App.getContext().getString(R.string.Description)+": "+book.getDescription());
        mBookISBN.setText(context.getString(R.string.isbn) + ": " + book.getISBN());
        String description = book.getDescription();
        //blijkbaar is description soms null in overzicht
        String shortDescription = "";
        if (description != null && description != "") {
            int shortDescriptionLength = description.length();
            int maxLengthShortD = 60;
            shortDescription = description.substring(0, description.length() > maxLengthShortD ? maxLengthShortD : description.length()) + App.getContext().getString(R.string.etc_after_book_description);
        } else if (description == null || description == "") {
            shortDescription = context.getString(R.string.sorry_no_google_books_description);
        }
        mShortDescription.setText(context.getString(R.string.short_description) + shortDescription);
        ImageLoader imageLoader = App.getImageLoader();
        String uriBookThumbnail = book.getThumbnailURL();

        // Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
//  which implements ImageAware interface)
        if (uriBookThumbnail!="") {
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
            imageLoader.displayImage(uriBookThumbnail, mBookThumbnail, options);
            Log.d(App.getTAG(), "RESULTSNULL?" + LibraryManagement.getExactSameBook(book.getId(), SearchTypeLocalDB.UID) != null ? "null" : LibraryManagement.getExactSameBook(book.getId(), SearchTypeLocalDB.UID).getTitle());
        }
        else{
            //"drawable://" + R.drawable.img // from drawables (non-9patch images)
            uriBookThumbnail="drawable://"+R.drawable.no_image_found_en;
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
            imageLoader.displayImage(uriBookThumbnail, mBookThumbnail, options);
        }



        if (App.isInLocalDB(mBookId)) {
            mToolbar.getMenu().findItem(R.id.menu_item_copy_to_db).setEnabled(false);
            mToolbar.getMenu().findItem(R.id.menu_item_delete_from_db).setEnabled(true);
        } else {
            mToolbar.getMenu().findItem(R.id.menu_item_copy_to_db).setEnabled(true);
            mToolbar.getMenu().findItem(R.id.menu_item_delete_from_db).setEnabled(false);
        }


        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_copy_to_db:
                        LibraryManagement.addOrUpdateBook(book);
                        item.setEnabled(false);
                        mToolbar.getMenu().findItem(R.id.menu_item_delete_from_db).setEnabled(true); //eigenlijk overbodig omdat Recyclerviewer geupdated dient te worden
                        break;
                    case R.id.menu_item_delete_from_db:
                        boolean deleted=LibraryManagement.deleteSpecificBook(book);
                        if (deleted) Toast.makeText(App.getContext(),String.format(App.getContext().getString(R.string.has_been_deleted),book.getTitle()),Toast.LENGTH_LONG).show();
                        else Toast.makeText(App.getContext(),String.format(App.getContext().getString(R.string.has_not_been_deleted),book.getTitle()),Toast.LENGTH_LONG).show();
                        item.setEnabled(false);
                        mToolbar.getMenu().findItem(R.id.menu_item_copy_to_db).setEnabled(true); //eigenlijk overbodig omdat Recyclerviewer geupdated dient te worden


                        App.getInstance().removeBook(mBookId);


                }
                return true;
            }
        });

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
//Intent intent=BooksPagerFragment.newIntent(App.getContext(), mBookId);
//v.getContext().startActivity(intent);

//als ik hier klik, moet er een BookDetailFragment worden gecreerd en geswapt
        FragmentManager manager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);
        Log.d(App.getTAG(), fragment.toString());

        //App.getInstance().clearBooks();
        fragment = BooksPagerFragment.newBooksPagerActivity(mBookId);
        Log.d(App.getTAG(), fragment.toString());
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();


    }
}
