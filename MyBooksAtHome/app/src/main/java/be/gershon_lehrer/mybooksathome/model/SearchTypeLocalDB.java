package be.gershon_lehrer.mybooksathome.model;

import be.gershon_lehrer.mybooksathome.R;

/**
 * Created by gershonlehrer on 12/07/16.
 */
public enum SearchTypeLocalDB {
    UID("id"),
    ISBN("mISBN"),
    GOOGLEID("mGoogleID"),
    PUBLISHER("mPublisher:"),
    AUTHOR("mAuthor");

    private String mType;

    private SearchTypeLocalDB(String type){
        mType=type;

    }

    public String toString(){
        return mType;
    }
}
