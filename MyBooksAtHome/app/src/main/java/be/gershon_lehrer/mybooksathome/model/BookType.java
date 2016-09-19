package be.gershon_lehrer.mybooksathome.model;

import be.gershon_lehrer.mybooksathome.R;

/**
 * Created by gershonlehrer on 04/07/16.
 */
public enum BookType {
    Ebook(R.string.ebook),
    Paperback(R.string.paperback),
    Hardcover(R.string.hardcover);


    private final int status;


    private BookType(int s) {
        status = s;
    }

    public boolean equalsStatus(int otherStatus) {
        return (otherStatus == status);
    }

    public Integer toStringInteger() {
        return this.status;
    }
}
