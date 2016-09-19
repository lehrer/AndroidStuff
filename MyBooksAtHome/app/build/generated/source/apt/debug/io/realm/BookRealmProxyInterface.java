package io.realm;


public interface BookRealmProxyInterface {
    public String realmGet$id();
    public void realmSet$id(String value);
    public String realmGet$mISBN();
    public void realmSet$mISBN(String value);
    public String realmGet$mSubject();
    public void realmSet$mSubject(String value);
    public String realmGet$mGoogleID();
    public void realmSet$mGoogleID(String value);
    public String realmGet$mTitle();
    public void realmSet$mTitle(String value);
    public String realmGet$mDescription();
    public void realmSet$mDescription(String value);
    public String realmGet$mThumbnailURL();
    public void realmSet$mThumbnailURL(String value);
    public RealmList<be.gershon_lehrer.mybooksathome.model.Author> realmGet$mAuthors();
    public void realmSet$mAuthors(RealmList<be.gershon_lehrer.mybooksathome.model.Author> value);
    public RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> realmGet$mPublishers();
    public void realmSet$mPublishers(RealmList<be.gershon_lehrer.mybooksathome.model.Publisher> value);
    public String realmGet$mValueOfBookStatus();
    public void realmSet$mValueOfBookStatus(String value);
    public int realmGet$mReadingProgress();
    public void realmSet$mReadingProgress(int value);
    public String realmGet$mPreviewLink();
    public void realmSet$mPreviewLink(String value);
    public String realmGet$mValueOfBookType();
    public void realmSet$mValueOfBookType(String value);
}
