package be.gershon_lehrer.mybooksathome.model;

import android.content.Context;

import java.lang.reflect.Array;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;

/**
 * Created by gershonlehrer on 04/07/16.
 */
public enum SearchMethodGoogleApi {
    EMPTY("",R.string.empty_text),
    AUTHOR("inauthor:",R.string.author),
    ISBN("isbn:",R.string.isbn),
    TITLE("intitle:",R.string.title),
    PUBLISHER("inpublisher:",R.string.publisher),
    SUBJECT("subject:",R.string.subject);

    private final String mPrefixForQuery;
    private final int mResourceID;



    private SearchMethodGoogleApi(String s,int resourceID) {
        mPrefixForQuery = s;
        mResourceID=resourceID;
    }

    public boolean equalsStatus(String otherValueForQuery) {
        return (otherValueForQuery == mPrefixForQuery);
    }

    public static String[] getUserFriendlyValues() {
        SearchMethodGoogleApi[] methods = values();
        String[] userFriendlynames = new String[values().length];

        for (int i = 0; i < values().length; i++) {
            userFriendlynames[i] = methods[i].getUserFriendlyName();
        }
        return userFriendlynames;
    }

    public static String[] getPrefixesForQuery() {
        SearchMethodGoogleApi[] values = values();
        String[] prefixes = new String[values().length];

        for (int i = 0; i < values().length; i++) {
            prefixes[i] = values[i].getPrefixForQuery();
        }
        return prefixes;
    }

    public String getUserFriendlyName(){
        return App.getContext().getString(mResourceID);
    }
    
    public String getPrefixForQuery(){
        return this.mPrefixForQuery;
    }
    

//    public static String[] toArray(){
//        String[] toReturn={EMPTY.name(),AUTHOR.name(),ISBN.toStringInteger(),TITLE.toStringInteger(),PUBLISHER.toStringInteger(),SUBJECT.toStringInteger()};
//    return toReturn;}


//    public static String[] toArrayString(Context context){
//        String[] toReturn={context.getString(AUTHOR.toStringInteger()),context.getString(ISBN.toStringInteger()),context.getString(TITLE.toStringInteger()),context.getString(PUBLISHER.toStringInteger()),context.getString(SUBJECT.toStringInteger())};
//        return toReturn;}

    public static SearchMethodGoogleApi getByOrdinal(int ordinal){
        for(SearchMethodGoogleApi value : SearchMethodGoogleApi.values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid ordinal selected");
    }
}
