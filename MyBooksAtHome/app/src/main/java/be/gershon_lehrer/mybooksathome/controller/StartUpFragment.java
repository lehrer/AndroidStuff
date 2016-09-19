package be.gershon_lehrer.mybooksathome.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.controller.GoogleApiAdapter;
import be.gershon_lehrer.mybooksathome.controller.LibraryManagement;
import be.gershon_lehrer.mybooksathome.model.Author;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.BookStatus;
import be.gershon_lehrer.mybooksathome.model.SearchMethodGoogleApi;
import io.realm.RealmResults;

/**
 * Created by gershonlehrer on 06/07/16.
 */
public class StartUpFragment extends Fragment {
    private static final String TAG = "MYBOOKStag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //mijn code vanaf hier:
        View v = inflater.inflate(R.layout.start_up_fragment, container, false);

        LibraryManagement.setDefaultRealmConfiguration(v.getContext());
//         LibraryManagement.deleteAllBooks(this);
        //LibraryManagement.deleteAllDBContent(v.getContext());

//        Book book1 = new Book();
//        Log.v(TAG, "new Book id " + book1.getId());
//        book1.setTitle("Jan en John");
//        book1.setISBN("12345");
//        book1.setValueOfBookStatus(BookStatus.OWNING);
//
//        LibraryManagement.addOrUpdateBook(book1);
//        Log.v(TAG, "book1 voor verandering: " + book1.getTitle());
//        book1.setTitle("andere titel");
//        book1.setTitle("andere title 3");
//        LibraryManagement.addOrUpdateBook(book1);
//        Log.v(TAG, "book1 na verandering: " + book1.getTitle());
//        book1 = LibraryManagement.getBook(book1.getId());
//        Log.v(TAG, "new Book id " + book1.getId());
//
//        for (int i=0;i<50;i++){
//        Book book = new Book();
//        book.setTitle("nog een boekske "+ i);
//        LibraryManagement.addOrUpdateBook(book);}
//
//        Book book5 = LibraryManagement.getBook(book1.getId());
//        //Book book6 = new Book(book5);
//
//        book5.setTitle("titel 5");
//        LibraryManagement.addOrUpdateBook(book5);
//        Log.v(TAG, "book5 id " + book5.getId());
//        //We roepen constructor public Book(Book update) op
//        //Book updatedBook = new Book(book1);
//        /* THE CHANGE */
//        Log.v(TAG, "new Book id " + book1.getId());
//
//        book1.setISBN("896986");
//        book1.addAuthor(new Author("Alfa"));
//        book1.addAuthor(new Author("Omega"));
//        //updatedBook.addAuthor();
//
//        LibraryManagement.updateBook(book1, v.getContext());
//
//        //LibraryManagement.addBook(book1,this);
//
//        RealmResults<Book> boekenOverzicht = LibraryManagement.getBookBasedOnTitle("Jan en John", v.getContext());
//        Log.i(TAG, "aantalboeken:" + boekenOverzicht.size());
//        for (Book boek : boekenOverzicht)
//            Log.i(TAG, "boekenoverzicht:" + boek.getTitle() + " " + boek.getISBN());
//
//        Log.v(TAG, "BEGIN GOOGLE API");


        return v;
    }

//    public class FetchBookByISBN extends AsyncTask<Void, Void, List<Book>> {
//
//
//        /**
//         * Override this method to perform a computation on a background thread. The
//         * specified parameters are the parameters passed to {@link #execute}
//         * by the caller of this task.
//         * <p/>
//         * This method can call {@link #publishProgress} to publish updates
//         * on the UI thread.
//         *
//         * @param params The parameters of the task.
//         * @return A result, defined by the subclass of this task.
//         * @see #onPreExecute()
//         * @see #onPostExecute
//         * @see #publishProgress
//         */
//        @Override
//        protected List<Book> doInBackground(Void... params) {
//            try {
//                return googleApiAdapter.getBooksByISBN("9780134171456");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(List<Book> bookList) {
//            Log.v(TAG,"Title found is " + bookList.get(0).getTitle());
//
//        }
//    }
}
