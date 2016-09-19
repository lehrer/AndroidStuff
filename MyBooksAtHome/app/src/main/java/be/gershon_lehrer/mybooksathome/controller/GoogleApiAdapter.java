package be.gershon_lehrer.mybooksathome.controller;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.model.Author;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.SearchMethodGoogleApi;

/**
 * Created by gershonlehrer on 05/07/16.
 */
public class GoogleApiAdapter {
    private final static String TAG = "GoogleAPI";
    private static final String APIKEY = "AIzaSyCYj5l_YVEJUAZyWAnK5GN6T_TzUXPplzc";
    private static final String APPLICATION_NAME = "MyBookAtHomeAndroidApp";
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
    private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();
    //intitle: Returns results where the text following this keyword is found in the title.
    private final static String BYTITLE = "intitle";
    //inauthor: Returns results where the text following this keyword is found in the author.
    private final static String BYAUTHOR = "inauthor";
    //inpublisher: Returns results where the text following this keyword is found in the publisher.
    private final static String BYPUBLISHER = "inpublisher";
    //subject: Returns results where the text following this keyword is listed in the category list of the volume.
    private final static String BYSUBJECT = "subject";
    //isbn: Returns results where the text following this keyword is the ISBN number.
    private final static String BYISBN = "isbn";
    //lccn: Returns results where the text following this keyword is the Library of Congress Control Number.
    private final static String BYLIBRARYCONGRESCONTROLNUMBER_LCCN = "lccn";
    //oclc: Returns results where the text following this keyword is the Online Computer Library Center number.
    private final static String BYONLINECOMPUTERLIBRARYCENTERNR = "oclc";


    //QueryGoogleBooksHelper qb = new QueryGoogleBooksHelper();


    public Books getBooksByAuthor(Author author) throws IOException {
        Books books = QueryGoogleBooksHelper.setUpClient();


        Books.Volumes.List volumesList = books.volumes().list(BYAUTHOR + " " + author.getName());

        Book bookToReturn = new Book();

        return books;

    }


    //gebaseerd op https://github.com/google/google-api-java-client-samples/blob/master/books-cmdline-sample/src/main/java/com/google/api/services/samples/books/cmdline/BooksSample.java
    public static void genericQueryCreator(String... args) {
//Parse command line parameters into a query.
        // Query format: "[<author|isbn|intitle>:]<query>"
        String prefix = "";
        String query = "";
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            String[] split = arg.split(":");
            if (split.length % 2 != 0) {
                throw new IllegalArgumentException("invalid parameters");
            }


            //voor de parameters op de even plaatsen
            if (i % 2 == 0) {
                if ("".equals(arg) || BYTITLE.equals(arg) || BYAUTHOR.equals(arg) || BYISBN.equals(arg) || BYSUBJECT.equals(arg) || BYPUBLISHER.equals(arg) || BYLIBRARYCONGRESCONTROLNUMBER_LCCN.equals(arg) || BYONLINECOMPUTERLIBRARYCENTERNR.equals(arg)) {
                    prefix = arg.equals("") ? arg : arg + ":"; //bij een ingevulde parameter moet er ":" bijkomen
                } else {
                    //een ongeldige parameter mee gestuurd
                    throw new IllegalArgumentException("invalid parameters");
                }
            } else { //niet op een even positie
                query = arg;
            }

            if (prefix != null) {
                query += prefix + query;
            }
        }
    }


    private static class QueryGoogleBooksHelper {
        private static JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();


        public static Books setUpClient() {
            // Set up Books client.
            return new Books.Builder(AndroidHttp.newCompatibleTransport()
                    , jsonFactory, null)
                    .setApplicationName(APPLICATION_NAME)
                    .setGoogleClientRequestInitializer(new BooksRequestInitializer(APIKEY))
                    .build();
        }


        public void queryGoogleExample(JsonFactory jsonFactory, String query)

        {
            try {
                // Set up Books client.
                final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                        .setApplicationName(APPLICATION_NAME)
                        .setGoogleClientRequestInitializer(new BooksRequestInitializer(APIKEY))
                        .build();


                // Set query string and (filter only Google eBooks.)
                Books.Volumes.List volumesList = books.volumes().list(query);
                //volumesList.setFilter("ebooks");

                // Execute the query.
                Volumes volumes = volumesList.execute();
                if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
                    System.out.println("No matches found.");
                    return;
                }

                // Output results.
                for (Volume volume : volumes.getItems()) {
                    Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
                    Volume.SaleInfo saleInfo = volume.getSaleInfo();
                    System.out.println("==========");
                    // Title.
                    System.out.println("Title: " + volumeInfo.getTitle());
                    // Author(s).
                    java.util.List<String> authors = volumeInfo.getAuthors();
                    if (authors != null && !authors.isEmpty()) {
                        System.out.print("Author(s): ");
                        for (int i = 0; i < authors.size(); ++i) {
                            System.out.print(authors.get(i));
                            if (i < authors.size() - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println();
                    }

//                // Description (if any).
//                if (volumeInfo.getDescription() != null && volumeInfo.getDescription().length() > 0) {
//                    System.out.println("Description: " + volumeInfo.getDescription());
//                }
//                // Ratings (if any).
//                if (volumeInfo.getRatingsCount() != null && volumeInfo.getRatingsCount() > 0) {
//                    int fullRating = (int) Math.round(volumeInfo.getAverageRating().doubleValue());
//                    System.out.print("User Rating: ");
//                    for (int i = 0; i < fullRating; ++i) {
//                        System.out.print("*");
//                    }
//                    System.out.println(" (" + volumeInfo.getRatingsCount() + " rating(s))");
//                }
//                // Price (if any).
//                if (saleInfo != null && "FOR_SALE".equals(saleInfo.getSaleability())) {
//                    double save = saleInfo.getListPrice().getAmount() - saleInfo.getRetailPrice().getAmount();
//                    if (save > 0.0) {
//                        System.out.print("List: " + CURRENCY_FORMATTER.format(saleInfo.getListPrice().getAmount())
//                                + "  ");
//                    }
//                    System.out.print("Google eBooks Price: "
//                            + CURRENCY_FORMATTER.format(saleInfo.getRetailPrice().getAmount()));
//                    if (save > 0.0) {
//                        System.out.print("  You Save: " + CURRENCY_FORMATTER.format(save) + " ("
//                                + PERCENT_FORMATTER.format(save / saleInfo.getListPrice().getAmount()) + ")");
//                    }
//                    System.out.println();
//                }
//                // Access status.
//                String accessViewStatus = volume.getAccessInfo().getAccessViewStatus();
//                String message = "Additional information about this book is available from Google eBooks at:";
//                if ("FULL_PUBLIC_DOMAIN".equals(accessViewStatus)) {
//                    message = "This public domain book is available for free from Google eBooks at:";
//                } else if ("SAMPLE".equals(accessViewStatus)) {
//                    message = "A preview of this book is available from Google eBooks at:";
//                }
//                System.out.println(message);
//                // Link to Google eBooks.
//                System.out.println(volumeInfo.getInfoLink());
                }
//            System.out.println("==========");
//            System.out.println(
//                    volumes.getTotalItems() + " total results at http://books.google.com/ebooks?q="
//                            + URLEncoder.encode(query, "UTF-8"));
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();

            }

        }


    }

//    public static class GetBooksByISBN extends AsyncTask<String, Void, List<Book>> {
//
//        private OnTaskFinishedListener mCallback;
//
//        public interface OnTaskFinishedListener {
//            void finished(List<Book> books);
//        }
//
//        public GetBooksByISBN(OnTaskFinishedListener callback) {
//            mCallback = callback;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //Loading...
//        }
//
//        @Override
//        protected List<Book> doInBackground(String... params) {
//            List<Book> bookList = new ArrayList<>();
//            Volume volume = null;
//            Volumes volumes = null;
//            Books books = QueryGoogleBooksHelper.setUpClient();
//
//
//            Books.Volumes.List volumesList = null;
//            try {
//                volumesList = books.volumes().list(BYISBN + " " + params[0]).setMaxResults(1L);
//
//                // Execute the query.
//                volumes = volumesList.execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (volumes != null) {
//                for (Volume vol : volumes.getItems()) {
//                    Log.v(TAG + "Book found", vol != null ? vol.toString() : "");
//                    Volume.VolumeInfo volInfo = vol.getVolumeInfo();
//
//                    //get authors in List
//                    List<String> authorStringList = volInfo.getAuthors();
//                    //copy contents of list to array
//                    ArrayList<Author> authorArrayList = new ArrayList<>();
//                    for (String author : authorStringList)
//                        authorArrayList.add(new Author("author"));
//
//                    //create new Book and edit its info with the info from Google
//                    Book book = new Book(volInfo.getIndustryIdentifiers().get(1).getIdentifier(), volInfo.getTitle(), authorArrayList);
//                    bookList.add(book);
//                    Log.v(TAG, bookList.size() + "Book added");
//                }
//            }
//
//            return bookList;
//        }
//
//        @Override
//        protected void onPostExecute(List<Book> bookList) {
//            super.onPostExecute(bookList);
//            mCallback.finished(bookList);
//        }
//    }

    public static class GetBooks extends AsyncTask<String, Void, List<Book>> {


        private final Context context;
        private ProgressDialog mProgressDialog;

        public GetBooks(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setMessage(context.getString(R.string.text_while_google_books_loading));
            mProgressDialog.show();


        }

        public Boolean isStringInArray(String str, String[] array) {
            for (String item : array) {
                if (item.trim().toLowerCase().contains(str.toLowerCase()))
                    return true;
            }
            return false;

        }

        @Override
        protected List<Book> doInBackground(String... params) {
            //gebaseerd op https://github.com/google/google-api-java-client-samples/blob/master/books-cmdline-sample/src/main/java/com/google/api/services/samples/books/cmdline/BooksSample.java
//Parse command line parameters into a searchWord.
            // Query format: "[<author|isbn|intitle>:]<searchWord>"

            String importedQuery[] = params[0].split("#");
            String fullQuery = "";

            for (int i = 0; i < importedQuery.length; i++) {
                if (!fullQuery.equals("")) fullQuery+='&';
                //vanaf hier moet de lus komen voor meerdere gekoppelde queries
                String prefix = "";
                String searchWord = "";

                int tmp = 0;
                for (String s : importedQuery) Log.d(TAG, "params[]" + tmp++ + s);

                String args[] = importedQuery[i].split("\\|");  //todo params[i]
                prefix = args[0].trim();
                searchWord = args[1].trim();
                searchWord = searchWord.replace(" ", "+");


                Log.d(TAG, "searchWord:" + searchWord);
                Log.d(TAG, "Prefix" + prefix);

                String[] options = SearchMethodGoogleApi.getPrefixesForQuery();
//            for (String s:options)Log.v(TAG+"options",s);
                if (!isStringInArray(prefix, options)) {
                    //een ongeldige parameter mee gestuurd
                    throw new IllegalArgumentException("invalid parameters");
                }
                fullQuery += prefix + searchWord;

                //tot hier moet de lus komen voor meerdere gekoppelde queries

            }




                List<Book> bookList = new ArrayList<>();
                Volume volume = null;
                Volumes volumes = null;
                Books books = QueryGoogleBooksHelper.setUpClient();


                Books.Volumes.List volumesList = null;
                try {
                    volumesList = books.volumes().list(fullQuery).setMaxResults(40L);

                    Log.d(App.getTAG(), "queryFullString:" + fullQuery);


                    // Execute the searchWord.
                    volumes = volumesList.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (volumes != null && volumes.getItems() != null) {
                    //vanaf hier halen we de details op:
                    for (Volume vol : volumes.getItems()) {
                        Log.v(TAG + "Book (genAll)", vol != null ? vol.toString() : "");

                        Volume.VolumeInfo volInfo = vol.getVolumeInfo();
                        Volume.SaleInfo salesInfo = vol.getSaleInfo();
                        Volume.SearchInfo searchInfo = vol.getSearchInfo();


                        //get authors in List
                        List<String> authorStringList = volInfo.getAuthors();
                        ArrayList<Author> authorArrayList = new ArrayList<>();
                        //copy contents of list to array
                        if (volInfo.getAuthors() != null) {
                            for (String author : authorStringList)
                                authorArrayList.add(new Author(author));
                        } else {
                            authorArrayList.add(new Author(App.getContext().getString(R.string.no_returned_authors_google)));
                        }

                        //getGoogleID
                        String googleID = vol.getId();

                        //get ISBN:
                        String ISBN = "";
                        if (volInfo.getIndustryIdentifiers() != null) {
                            if (volInfo.getIndustryIdentifiers().size() > 1)
                                ISBN = volInfo.getIndustryIdentifiers().get(1).getIdentifier();
                        }

                        //get Title:
                        String title = "";
                        if (volInfo.getTitle() != null) {
                            title = volInfo.getTitle();
                        }
                        //get description
                        String description = "";
                        if (volInfo.getDescription() != null) {
                            description = volInfo.getDescription();
                        }

                        //get ImageLinks:
                        Volume.VolumeInfo.ImageLinks imageLinks = null;
                        if (volInfo.getImageLinks() != null) {
                            imageLinks = volInfo.getImageLinks();
                        }

                        //get links to book (pre)views:
                        String previewLink = "";
                        if (volInfo.getPreviewLink() != null) {
                            previewLink = volInfo.getPreviewLink();
                        }

                        //String shortDescription=searchInfo.getTextSnippet()+App.getContext().getString(R.string.etc_after_book_description);
                        Double averageRating = volInfo.getAverageRating();

                        //TODO:etc verder doen

                        //create new Book and edit its info with the info from Google
                        Book book = new Book(ISBN, googleID, title, authorArrayList, description, imageLinks, previewLink);
                        bookList.add(book);
                        Log.v(TAG, bookList.size() + "Book added");
                    }
                }


                return bookList;


        }

        @Override
        protected void onPostExecute(List<Book> bookList) {
            super.onPostExecute(bookList);
            mProgressDialog.cancel();
            App.getInstance().setBookList(bookList);


        }
    }
}
