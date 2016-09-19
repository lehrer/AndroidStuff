package be.gershon_lehrer.mybooksathome.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.model.SearchMethodGoogleApi;

/**
 * Created by gershonlehrer on 15/07/16.
 */
public class ShowBookSearchDialogActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private Button mDialogSearchCancelButton;
    private Button  mDialogSearchOkButton;

    private String mExportQuery = "";
    private SearchView ISBNSearchView;
    private SearchView titleSearchView;
    private SearchView authorSearchView;
    private SearchView subjectSearchView;
    private SearchView publisherSearchView;
    
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_in_dialog_search_layout);

        ISBNSearchView = (SearchView) findViewById(R.id.ISBN_search_dialog_searchview);
        titleSearchView = (SearchView) findViewById(R.id.search_dialog_searchview);
        authorSearchView = (SearchView) findViewById(R.id.author_dialog_searchview);
        subjectSearchView = (SearchView) findViewById(R.id.subject_dialog_searchview);
        publisherSearchView = (SearchView) findViewById(R.id.publisher_dialog_searchview);


        //listeners
        ISBNSearchView.setOnQueryTextListener(this);
        titleSearchView.setOnQueryTextListener(this);
        authorSearchView.setOnQueryTextListener(this);
        subjectSearchView.setOnQueryTextListener(this);
        publisherSearchView.setOnQueryTextListener(this);


        setOnIconifiedOnClick(ISBNSearchView);
        setOnIconifiedOnClick(titleSearchView);
        setOnIconifiedOnClick(authorSearchView);
        setOnIconifiedOnClick(subjectSearchView);
        setOnIconifiedOnClick(publisherSearchView);

        //TODO: uitzoeken hoe dit moet, dus als ik focus verander, de rest icnonify true.
//        publisherSearchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//               if (publisherSearchView.isIconified()) publisherSearchView.setIconified(false);
//            }
//        });


        
        mDialogSearchOkButton=(Button)findViewById(R.id.ok_button_in_dialog_search_layout);
        mDialogSearchOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindQueryCreator();

                Intent data=new Intent();
                data.putExtra("createquery",mExportQuery);
                setResult(RESULT_OK,data);
                finish();
            }
        });

        mDialogSearchCancelButton=(Button)findViewById(R.id.cancel_button_in_dialog_search_layout);
        mDialogSearchCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });}





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

    private void setOnIconifiedOnClick(final SearchView searchView){
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        bindQueryCreator();

        Intent data=new Intent();
        data.putExtra("createquery",mExportQuery);
        setResult(RESULT_OK,data);
        finish();

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

