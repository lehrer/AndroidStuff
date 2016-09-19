package be.gershon_lehrer.mybooksathome.controller.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.controller.BarCodeScannerActivity;
import be.gershon_lehrer.mybooksathome.controller.GoogleApiAdapter;
import be.gershon_lehrer.mybooksathome.controller.RecylerViewAdapter;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.SearchMethodGoogleApi;
import be.gershon_lehrer.mybooksathome.model.SourceOfResults;


/**
 * Created by gershonlehrer on 08/07/16.
 */
public class BookSearchFragment extends Fragment implements App.OnBookListChangedListener {
    private RecylerViewAdapter mRecylerViewAdapter;
    private RecyclerView mRecyclerView;
    private Spinner searchOptionsSpinner;
    //private Button mSearchButton;
    private TextView mResultCountText;
    private ImageButton mScannerImageButton;
    private final static int BOOKSCAN_INTENT=0;


    //private List<Book> mGoogleBooksList = App.getBookList();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_search_layout, container, false);

        searchOptionsSpinner = (Spinner) view.findViewById(R.id.search_options_spinner);
        final SearchView searchKeyWordSearchView = (SearchView) view.findViewById(R.id.search_book_editText);
       // mSearchButton=(Button)view.findViewById(R.id.book_search_layout_search_button);

        mScannerImageButton = (ImageButton)view.findViewById(R.id.scan_imageButtonimageButton);
        mScannerImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BarCodeScannerActivity.class);
                startActivityForResult(intent, BOOKSCAN_INTENT);
            }
        });

        App.getInstance().addOnBookListChangedListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.books_recycler_view_list2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecylerViewAdapter = new RecylerViewAdapter(App.getInstance().getBookList(), getActivity());
        mRecyclerView.setAdapter(mRecylerViewAdapter);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, SearchMethodGoogleApi.getUserFriendlyValues());
        for (String a:SearchMethodGoogleApi.getPrefixesForQuery()) Log.d(App.getTAG()+"getPrefixes",a);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        searchOptionsSpinner.setAdapter(adapter);
        mResultCountText=(TextView)view.findViewById(R.id.book_search_layout_results_found_textview);

        searchKeyWordSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //bij het er op klikken, kun je zoeken omdat het zoekveld verbreedt wordt
                searchKeyWordSearchView.setIconified(false);
            }
        });

        searchKeyWordSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //hier komt mijn code
                //neem focus weer weg tot er weer op wordt geklikt:
                String keyword = searchKeyWordSearchView.getQuery().toString();
                searchKeyWordSearchView.setQuery("",false);
                searchKeyWordSearchView.clearFocus();
                //SearchMethodGoogleApi.AUTHOR.ordinal() == position
                if (keyword.length() <= 0)
                {Toast.makeText(getContext(), R.string.empty_book_search_view_textView, Toast.LENGTH_LONG).show();}
                else {
                    int positionInSpinner=searchOptionsSpinner.getSelectedItemPosition();
                    String prefixForQuery=SearchMethodGoogleApi.getByOrdinal(positionInSpinner).getPrefixForQuery();

                    String query1 = prefixForQuery + "|"+keyword;
                    Log.d(App.getTAG()+"serchFrgmnt",query1);
                    executeGoogleQuery(query1);}

                //tot hier mijn code
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });







return view;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)App.getInstance().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==BOOKSCAN_INTENT){
            String ISBN=(String)data.getExtras().get("result");
            Log.v(App.getTAG(),"ISBN gescand: "+ISBN);
            executeGoogleQuery(SearchMethodGoogleApi.ISBN.getPrefixForQuery()+ "|"+ISBN);

        }
    }


    public void executeGoogleQuery(String query){
        GoogleApiAdapter.GetBooks task = new GoogleApiAdapter.GetBooks(this.getContext());
        task.execute(query);
    }

    @Override
    public void onBookListChanged() {
        mRecylerViewAdapter.notifyDataSetChanged();
        mRecylerViewAdapter.setSourceOfResults(SourceOfResults.GOOGLE);
        String sourceOfResultsAndCount=App.getContext().getString(R.string.total_results);
        sourceOfResultsAndCount+=" "+mRecylerViewAdapter.getSourceOfResults().getSourceToUserFriendlyString();
        sourceOfResultsAndCount+=": "+mRecylerViewAdapter.getItemCount();
        mResultCountText.setText(sourceOfResultsAndCount);
    }

    @Override
    public void onBookItemRemoved(int position) {
        //mRecylerViewAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getInstance().removeOnBookListChangedListener(this);
    }
}