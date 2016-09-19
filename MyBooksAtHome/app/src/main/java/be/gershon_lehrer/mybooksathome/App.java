package be.gershon_lehrer.mybooksathome;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import be.gershon_lehrer.mybooksathome.controller.LibraryManagement;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.SearchTypeLocalDB;

/**
 * Created by gershonlehrer on 08/07/16.
 * Source: http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context/4391811#4391811
*
 * Create a subclass of Application, for instance public class App extends Application {
 Set the android:name attribute of your <application> tag in the AndroidManifest.xml to point to your new class, e.g. android:name=".App"
 In the onCreate() method of your app instance, save your context (e.g. this) to a static field named app and create a static method that returns this field, e.g. getApp():
 This is how it should look:

 public class App extends Application{

 private static Context mContext;

 @Override
 public void onCreate() {
 super.onCreate();
 mContext = this;
 }

 public static Context getContext(){
 return mContext;
 }
 }
 Now you can use: App.getContext() whenever you want to get a context, and then getResources() (or App.getContext().getResources()).
 */


public class App extends Application {
    private List<Book> mBookList=new ArrayList<>();
    private static List<OnBookListChangedListener> listeners = new ArrayList<>();
    private static App mContext;
    private static ImageLoader mImageLoader;
    private static final String mTAG = "MYBOOKStag";

    public void clearBooks() {
        mBookList.clear();
        notifyOnBookListChangedListeners();
    }

    public void removeBook(String bookId) {
        for (int i = 0; i < mBookList.size();i++) {
            if(mBookList.get(i).getId().equals(bookId)) {
                mBookList.remove(i);
                notifyOnBookItemRemoved(i);
                break;
            }
        }
    }

    public interface OnBookListChangedListener {
        void onBookListChanged();
        void onBookItemRemoved(int position);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(mContext)
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileCount(100)
                .memoryCacheSize(2 * 1024 * 1024)
                .build();
        mImageLoader= ImageLoader.getInstance();
        mImageLoader.init(config);

    }

    public static boolean isInLocalDB(String bookId){
        boolean isInLoalDB=(
                LibraryManagement.getExactSameBook(bookId, SearchTypeLocalDB.UID) != null);
        return isInLoalDB;
    }


public static boolean isInLocalDB(Book book){
    boolean isInLoalDB=(
            LibraryManagement.getExactSameBook(book.getISBN(), SearchTypeLocalDB.ISBN) != null
                    ||
                    LibraryManagement.getExactSameBook(book.getGoogleID(), SearchTypeLocalDB.GOOGLEID) != null
    );
    return isInLoalDB;
}

    public static App getInstance() {
        return mContext;
    }

    public List<Book> getBookList() {
        return mBookList;
    }

    public void setBookList(List<Book> bookList) {
        this.mBookList.clear();
        this.mBookList.addAll(bookList);
        //App.mBookList = mBookList;
        notifyOnBookListChangedListeners();
    }

    public void notifyOnBookListChangedListeners() {
        for (OnBookListChangedListener listener:listeners) {
            listener.onBookListChanged();
        }
    }

    public void notifyOnBookItemRemoved(int position){
        for(OnBookListChangedListener listener:listeners) {
            listener.onBookItemRemoved(position);
        }
    }

    public void addOnBookListChangedListener(OnBookListChangedListener l) {
        this.listeners.add(l);
    }

    public void removeOnBookListChangedListener(OnBookListChangedListener l) {
        this.listeners.remove(l);
    }

    public static Context getContext(){
        return mContext;
    }
    public static ImageLoader getImageLoader() {return mImageLoader;}
    public static String getTAG(){return mTAG;}

    public static boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }

}
