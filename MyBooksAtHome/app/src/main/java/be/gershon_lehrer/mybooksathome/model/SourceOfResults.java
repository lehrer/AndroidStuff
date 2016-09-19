package be.gershon_lehrer.mybooksathome.model;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;

/**
 * Created by gershonlehrer on 10/07/16.
 */
public enum SourceOfResults {
    GOOGLE(R.string.Google),
    AMAZON(R.string.Amazon),
    LOCALDB(R.string.localDB);

    private final int mResourceID;

    private SourceOfResults(int resourceID) {
        mResourceID=resourceID;
    }

    public int getResourceID(){
        return this.mResourceID;
    }

    public String getSourceToUserFriendlyString(){
        return App.getContext().getString(mResourceID);
    }

}
