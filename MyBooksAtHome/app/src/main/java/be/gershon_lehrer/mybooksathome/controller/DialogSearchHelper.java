package be.gershon_lehrer.mybooksathome.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.MainActivity;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.controller.fragments.BooksListFragment;
import be.gershon_lehrer.mybooksathome.model.SearchMethodGoogleApi;
import be.gershon_lehrer.mybooksathome.model.SourceOfResults;

/**
 * Created by gershonlehrer on 14/07/16.
 */
public class DialogSearchHelper implements SearchView.OnQueryTextListener {
    Dialog mDialog;
    private String mExportQuery = "";
    private SearchView ISBNSearchView;
    private SearchView titleSearchView;
    private SearchView authorSearchView;
    private SearchView subjectSearchView;
    private SearchView publisherSearchView;
    private BooksListFragment mBooksListFragment;
    private Context mContext;


    public DialogSearchHelper(Dialog dialog, Context context) {
        this.mDialog = dialog;
mContext=context;
        ISBNSearchView = (SearchView) dialog.findViewById(R.id.ISBN_search_dialog_searchview);
        titleSearchView = (SearchView) dialog.findViewById(R.id.search_dialog_searchview);
        authorSearchView = (SearchView) dialog.findViewById(R.id.author_dialog_searchview);
        subjectSearchView = (SearchView) dialog.findViewById(R.id.subject_dialog_searchview);
        publisherSearchView = (SearchView) dialog.findViewById(R.id.publisher_dialog_searchview);


        //listeners
        ISBNSearchView.setOnQueryTextListener(this);
        titleSearchView.setOnQueryTextListener(this);
        authorSearchView.setOnQueryTextListener(this);
        subjectSearchView.setOnQueryTextListener(this);
        publisherSearchView.setOnQueryTextListener(this);


    }



    private void queryCreator(SearchView searchView, SearchMethodGoogleApi searchMethodGoogleApi) {
        String keyword = searchView.getQuery().toString();
        if (keyword.length()!=0){
        searchView.clearFocus();
        //SearchMethodGoogleApi.AUTHOR.ordinal() == position
        if (keyword.length() <= 0) {
            Toast.makeText(App.getContext(), R.string.empty_book_search_view_textView, Toast.LENGTH_SHORT).show();
        }

        String prefixForQuery = searchMethodGoogleApi.getPrefixForQuery();
        if (!mExportQuery.equals("")) mExportQuery += "#";  //to split between different queries
        mExportQuery += prefixForQuery + "|" + keyword;
        Log.d(App.getTAG() + "serchFrgmnt", mExportQuery);}

    }

    public void bindQueryCreator() {
        queryCreator(ISBNSearchView, SearchMethodGoogleApi.ISBN);
        queryCreator(titleSearchView, SearchMethodGoogleApi.TITLE);
        queryCreator(authorSearchView, SearchMethodGoogleApi.AUTHOR);
        queryCreator(subjectSearchView, SearchMethodGoogleApi.SUBJECT);
        queryCreator(publisherSearchView, SearchMethodGoogleApi.PUBLISHER);


    }


    public String getExportQuery(){

        return mExportQuery;
    }



    public interface OnDialogSearchPerformedListener {
        void onSearchedPerformed();
    }

    private static OnDialogSearchPerformedListener mListener=null;
    public static void addListener(OnDialogSearchPerformedListener listener) {
        mListener=listener;
    }


    /**
     * Called when the user submits the query. This could be due to a key press on the
     * keyboard or due to pressing a submit button.
     * The listener can override the standard behavior by returning true
     * to indicate that it has handled the submit request. Otherwise return false to
     * let the SearchView handle the submission by launching any associated intent.
     *
     * @param query the query text that is to be submitted
     * @return true if the query has been handled by the listener, false to let the
     * SearchView perform the default action.
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        bindQueryCreator();
        mDialog.cancel();
//        GoogleApiAdapter.GetBooks task = new GoogleApiAdapter.GetBooks(mContext);
//        task.execute(mExportQuery);
mListener.onSearchedPerformed();
        return false;
    }

    /**
     * Called when the query text is changed by the user.
     *
     * @param newText the new content of the query text field.
     * @return false if the SearchView should perform the default action of showing any
     * suggestions if available, true if the action was handled by the listener.
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
