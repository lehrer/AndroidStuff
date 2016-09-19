package be.gershon_lehrer.mybooksathome.controller;

import android.content.Context;
import android.util.Log;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.model.Author;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.Publisher;
import be.gershon_lehrer.mybooksathome.model.SearchMethodGoogleApi;
import be.gershon_lehrer.mybooksathome.model.SearchTypeLocalDB;
import be.gershon_lehrer.mybooksathome.model.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by gershonlehrer on 04/07/16.
 */
public class LibraryManagement {


    public static void addOrUpdateBook(final Book book) {
        // Open the Realm for the UI thread.
        Realm realm = Realm.getDefaultInstance();
        // final Book tmp = book;  //opdat inner class het kan lezen, moet dit final zijn

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Persist unmanaged objects
                Book managedBook = realm.copyToRealmOrUpdate(book);
            }
        });


    }


//voorbeeld van gebruik:
//We roepen constructor public Book(Book update) op
//Book updatedBook = new Book(book1);
//        /* THE CHANGE */
//
//    updatedBook.setISBN("896986");
//    updatedBook.addAuthor(new Author("Alfa"));
//    updatedBook.addAuthor(new Author("Omega"));

//
//    LibraryManagement.updateBook(updatedBook,this);

    //TODO zoek uit of je een boolean kunt teruggeven indien deze transactie een succes was
    public static void updateBook(final Book toUpdate, Context context) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.copyToRealmOrUpdate(toUpdate);


            }

        });

    }

    public static void setDefaultRealmConfiguration(Context context) {
        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        RealmConfiguration config = new RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static RealmResults<Book> getAllBooks(Context context) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Book> results = realm.where(Book.class).findAll();

        return results;
    }

    public static Book getBook(String bookID) {
        if (bookID == null) return null;
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Book> results = realm.where(Book.class).equalTo("id", bookID).findAll();
        if (results.size() > 0) return new Book(results.get(0));
        else return null;
    }

    /**
     * @param value             here come what you are looking for
     * @param searchTypeLocalDB here comes the type of what you're looking for
     * @return one book or null if false
     * <p/>
     * This method returns a book if it exists, example: LibraryManagement.getExactSameBook(mBookId, SearchTypeLocalDB.UID)!=null
     */
    public static Book getExactSameBook(String value, SearchTypeLocalDB searchTypeLocalDB) {
        Log.d(App.getTAG(), "value:" + value + " srchlclType:" + searchTypeLocalDB.toString());
        if (value == null) return null;
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Book> results = realm.where(Book.class).equalTo(searchTypeLocalDB.toString(), value).findAll();
        for (Book b : results) Log.d(App.getTAG(), "BooksReturned: " + b.toString());
        Log.d(App.getTAG(), "Lengte results: " + results.size());
        if (results.size() > 0) return new Book(results.get(0));
        else return null;
    }

    public static RealmResults<Book> getBookBasedOnTitle(String titel, Context context) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Book> results = realm.where(Book.class).equalTo("mTitle", titel).findAll();
        return results;
    }

    public static boolean deleteSpecificBook(Book book) {
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Book> result = realm.where(Book.class).equalTo(SearchTypeLocalDB.UID.toString(), book.getId()).findAll();
        if (result.size() == 0 || result == null) return false;
        else
            // All changes to data must happen in a transaction
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    result.get(0).deleteFromRealm();
                }
            });
        return true;
    }


    public static void deleteAllBooks(Context context) {
        Realm realm = Realm.getDefaultInstance();

        // obtain the results of a query
        final RealmResults<Book> results = realm.where(Book.class).findAll();

// All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

// Delete all matches
                results.deleteAllFromRealm();
            }
        });


    }

    public static void deleteAllDBContent(Context context) {
        Realm realm = Realm.getDefaultInstance();

        // obtain the results of a query
        final RealmResults<Book> books = realm.where(Book.class).findAll();
        final RealmResults<Author> authors = realm.where(Author.class).findAll();
        final RealmResults<Publisher> publishers = realm.where(Publisher.class).findAll();
        final RealmResults<User> users = realm.where(User.class).findAll();

// All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

// Delete all matches
                books.deleteAllFromRealm();
                authors.deleteAllFromRealm();
                publishers.deleteAllFromRealm();
                users.deleteAllFromRealm();
            }
        });


    }



}
