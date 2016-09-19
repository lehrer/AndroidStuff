package be.gershon_lehrer.mybooksathome.model;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;

/**
 * Created by gershonlehrer on 04/07/16.
 */
public enum BookStatus {
    WANTED(R.string.wanted),
    OWNING(R.string.owning),
    SOLD(R.string.sold),
    LOST(R.string.lost);

    private final int status;


    private BookStatus(int s) {
        status = s;
    }

    public boolean equalsStatus(int otherStatus) {
        return (otherStatus == status);
    }

    public Integer toStringInteger() {
        return this.status;
    }

    public String getBookStatusToString(){
        return App.getContext().getString(status);
    }
}

