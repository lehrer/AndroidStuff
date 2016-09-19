package be.gershon_lehrer.mybooksathome.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.controller.LibraryManagement;
import be.gershon_lehrer.mybooksathome.controller.PreviewGoogleBookWebviewActivity;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.SearchTypeLocalDB;

/**
 * Created by gershonlehrer on 08/07/16.
 */
public class BookDetailFragment extends Fragment{
    private TextView mTitle, mShortDescription, mAuthor, mBookDescription, mBookAuthor, mBookISBN;
    private ImageView mBookThumbnail;
    private  Book mBook;
    private ImageButton mGetGoogleBookPreviewButton;
    private Toolbar mToolbar;
    private static final String POSITION="be.gershon-lehrer.mybooksathome.book_detail_position";
    private static final String PREVIEWBOOKISBN="be.gershon-lehrer.mybooksathome.book_detail_google_book_preview_ISBN";


//    public static BookDetailFragment newInstance(Book book){
//        mBook.importChanges(book);
//        BookDetailFragment fragment=new BookDetailFragment();
//        return fragment;
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        int position = bundle.getInt(POSITION);
        mBook=new Book();
        mBook.importChanges(App.getInstance().getBookList().get(position));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_detail_layout, container, false);
        super.onCreate(savedInstanceState);
        mBookThumbnail = (ImageView) v.findViewById(R.id.book_detail_layout_imageView);
        mTitle = (TextView) v.findViewById(R.id.book_detail_layout_book_title);
        mAuthor = (TextView) v.findViewById(R.id.book_detail_layout_book_author);
        mBookDescription = (TextView) v.findViewById(R.id.book_detail_layout_book_long_description);
        //mShortDescription = (TextView) v.findViewById(R.id.book_detail_layout_book_short_description);
        mBookISBN = (TextView) v.findViewById(R.id.book_detail_layout_textViewISBN);
        //toolbar met drie bolletjes en menu laden en inflaten
        mToolbar = (Toolbar)v.findViewById(R.id.book_detail_layout_card_toolbar);
        mToolbar.inflateMenu(R.menu.book_item_view_holder_toolbar_menu);

        if (App.isInLocalDB(mBook)) {
            mToolbar.getMenu().findItem(R.id.menu_item_copy_to_db).setEnabled(false);
            mToolbar.getMenu().findItem(R.id.menu_item_delete_from_db).setEnabled(true);
        } else {
            mToolbar.getMenu().findItem(R.id.menu_item_copy_to_db).setEnabled(true);
            mToolbar.getMenu().findItem(R.id.menu_item_delete_from_db).setEnabled(false);
        }

        //ToolBar en Menu actions:
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_copy_to_db:
                        LibraryManagement.addOrUpdateBook(mBook);
                        item.setEnabled(false);
                        mToolbar.getMenu().findItem(R.id.menu_item_delete_from_db).setEnabled(true); //eigenlijk overbodig omdat Recyclerviewer geupdated dient te worden

                        break;
                    case R.id.menu_item_delete_from_db:
                        boolean deleted=LibraryManagement.deleteSpecificBook(mBook);
                        if (deleted) Toast.makeText(App.getContext(),String.format(getString(R.string.has_been_deleted),mBook.getTitle()),Toast.LENGTH_LONG).show();
                        else Toast.makeText(App.getContext(),String.format(getString(R.string.has_not_been_deleted),mBook.getTitle()),Toast.LENGTH_LONG).show();
                        item.setEnabled(false);
                        mToolbar.getMenu().findItem(R.id.menu_item_copy_to_db).setEnabled(true); //eigenlijk overbodig omdat Recyclerviewer geupdated dient te worden

                        App.getInstance().setBookList(LibraryManagement.getAllBooks(App.getContext()));

                }
                return true;
            }
        });




        //GoogleBooks Preview Button
        mGetGoogleBookPreviewButton=(ImageButton)v.findViewById(R.id.preview_google_book_link_button);


        if (mBook.getPreviewLink().length()<=0){
            mGetGoogleBookPreviewButton.setVisibility(View.INVISIBLE);
        }
        else {
            mGetGoogleBookPreviewButton.setVisibility(View.VISIBLE);
            mGetGoogleBookPreviewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(App.getTAG(),"STARTING INTENT with"+mBook.getISBN());
                    Intent intent=new Intent(v.getContext(), PreviewGoogleBookWebviewActivity.class);
                    intent.putExtra(PREVIEWBOOKISBN,mBook.getISBN());
                    startActivity(intent);
                }
            });
        }








        mTitle.setText(mBook.getTitle());
        mBookDescription.setText(mBook.getDescription());
        String authorNames = App.getContext().getString(R.string.Author_s);
        for (int i = 0; i < mBook.getAuthors().size(); i++) {
            authorNames += mBook.getAuthors().get(i).getName();
            //if there is a next author in the list, we need to add a newline and a comma
            if (i < mBook.getAuthors().size() - 1) authorNames += ",\n";
        }
        mAuthor.setText(authorNames);
        mBookISBN.setText(this.getString(R.string.isbn) + ": " + mBook.getISBN());


        ImageLoader imageLoader=App.getImageLoader();

            String uriBookThumbnail=mBook.getThumbnailURL();
// Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
//  which implements ImageAware interface)
        if (uriBookThumbnail!="") {
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
            imageLoader.displayImage(uriBookThumbnail, mBookThumbnail, options);
            Log.d(App.getTAG(), "RESULTSNULL?" + LibraryManagement.getExactSameBook(mBook.getId(), SearchTypeLocalDB.UID) != null ? "null" : LibraryManagement.getExactSameBook(mBook.getId(), SearchTypeLocalDB.UID).getTitle());
        }
        else{
            //"drawable://" + R.drawable.img // from drawables (non-9patch images)
            uriBookThumbnail="drawable://"+R.drawable.no_image_found_en;
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
            imageLoader.displayImage(uriBookThumbnail, mBookThumbnail, options);
        }

            return v;
        }


    }
