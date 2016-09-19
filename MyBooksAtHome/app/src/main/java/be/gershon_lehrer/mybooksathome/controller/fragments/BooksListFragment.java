package be.gershon_lehrer.mybooksathome.controller.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.controller.GoogleApiAdapter;
import be.gershon_lehrer.mybooksathome.controller.LibraryManagement;
import be.gershon_lehrer.mybooksathome.controller.RecylerViewAdapter;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.SourceOfResults;
import io.realm.Realm;
import io.realm.RealmResults;




/**
 * Created by gershonlehrer on 07/07/16.
 */
public class BooksListFragment extends Fragment implements App.OnBookListChangedListener {
    private RecylerViewAdapter mRecylerViewAdapter;
    private RecyclerView mRecyclerView;
    private TextView mResultCountText;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.books_list_fragment, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.books_recycler_view_list1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecylerViewAdapter = new RecylerViewAdapter(App.getInstance().getBookList(), getActivity());
        mRecyclerView.setAdapter(mRecylerViewAdapter);
        mResultCountText = (TextView) v.findViewById(R.id.book_list_layout_results_found_textview);

        //App.getInstance().addOnBookListChangedListener(this);

       //animatie bij verwijderen of toevoegen van cardviews
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        mRecyclerView.setItemAnimator(itemAnimator);


        init();
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        //onBookListChanged();//???
        App.getInstance().addOnBookListChangedListener(this);
        init();
    }

    @Override
    public void onPause() {
        super.onPause();
        App.getInstance().removeOnBookListChangedListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getInstance().removeOnBookListChangedListener(this);
    }

    //initalizes textfields etc (for example mResultCountText)
    public void init() {
        mResultCountText.setText("");
    }


    public void loadAllOwnedBooksDb() {
        init();
        RealmResults<Book> allBooks = LibraryManagement.getAllBooks(getActivity());
        //mBookList.clear();
        //mBookList.addAll(Realm.getDefaultInstance().copyFromRealm(allBooks));
        App.getInstance().setBookList(Realm.getDefaultInstance().copyFromRealm(allBooks));
        setResultCountText(SourceOfResults.LOCALDB);
        //mRecylerViewAdapter.notifyDataSetChanged();
    }

//    public void loadGoogleBooksFromISBN(String ISBN) throws IOException{
//
//        GoogleApiAdapter.GetBooksByISBN task=new GoogleApiAdapter.GetBooksByISBN(this);
//        task.execute(ISBN);
//
//    }

    public void loadGoogleBooks(String query) throws IOException {
        init();
        GoogleApiAdapter.GetBooks task = new GoogleApiAdapter.GetBooks(getContext());
        mRecylerViewAdapter.setSourceOfResults(SourceOfResults.GOOGLE);
        task.execute(query);

    }



    public void setResultCountText(SourceOfResults sourceOfResults) {
        //add on top text with notice of count results and source
        if (mRecylerViewAdapter.getItemCount() == 0) {
            //mRecyclerView.setVisibility(View.INVISIBLE);
            mResultCountText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
            mResultCountText.setText(R.string.No_results_found);
        } else {
            String sourceOfResultsAndCount = App.getContext().getString(R.string.total_results);
            sourceOfResultsAndCount += " " + sourceOfResults.getSourceToUserFriendlyString();
            sourceOfResultsAndCount += ": " + mRecylerViewAdapter.getItemCount();
            mResultCountText.setText(sourceOfResultsAndCount);
            mResultCountText.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        }
    }

    public void onBookItemRemoved(int position){
        mRecylerViewAdapter.notifyItemRemoved(position);
        setResultCountText(SourceOfResults.LOCALDB);

    }

    @Override
    public void onBookListChanged() {
        mRecylerViewAdapter.notifyDataSetChanged();
        if (mRecylerViewAdapter.getSourceOfResults()!=null)
        setResultCountText(mRecylerViewAdapter.getSourceOfResults());

    }
}
