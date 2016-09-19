package be.gershon_lehrer.mybooksathome.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.SearchTypeLocalDB;
import be.gershon_lehrer.mybooksathome.model.SourceOfResults;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by gershonlehrer on 07/07/16.
 */
public class RecylerViewAdapter extends RecyclerView.Adapter<BookItemView_Holder> {

    //  RealmResults<Book> mBookRealmResults;
    private List<Book> mBookList=new ArrayList<>();
    private Context mContext;
    private SourceOfResults mSourceOfResults;


    public RecylerViewAdapter(RealmResults<Book> bookRealmResults, Context context) {
        mBookList = Realm.getDefaultInstance().copyFromRealm(bookRealmResults);
        //App.getInstance().setBookList(Realm.getDefaultInstance().copyFromRealm(bookRealmResults));
        this.mContext = context;
    }

    public RecylerViewAdapter(List<Book> bookList, Context context) {
        mBookList = bookList;
        //App.getInstance().setBookList(bookList);
        this.mContext = context;
    }

    @Override
    public BookItemView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.book_row_layout, parent, false);

        return new BookItemView_Holder(view,getSourceOfResults());
    }

    @Override
    public void onBindViewHolder(BookItemView_Holder holder, int position) {
        Book book = App.getInstance().getBookList().get(position);

        holder.bindBook(book, position);
    }


    @Override
    public int getItemCount() {
        return App.getInstance().getBookList().size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setSourceOfResults(SourceOfResults sourceOfResults){
        if (sourceOfResults!=null)
        {
            this.mSourceOfResults=sourceOfResults;
        }
    }

    public SourceOfResults getSourceOfResults(){
        if (mSourceOfResults!=null){
        return mSourceOfResults;}
        else return null;
    }

    /*// Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Book book) {
        mList.add(position, book);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Book book) {
        int position = mList.indexOf(book);
        mList.remove(position);
        notifyItemRemoved(position);
    }*/

}
