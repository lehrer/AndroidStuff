package be.gershon_lehrer.mybooksathome.model;


import android.net.Uri;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volume;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.gershon_lehrer.mybooksathome.controller.LibraryManagement;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by gershonlehrer on 04/07/16.
 */
public class Book extends RealmObject implements Serializable {


    @PrimaryKey
    private String id;
    private String mISBN;
private String mSubject;
    private String mGoogleID;
    @Required
    private String mTitle;
    //private String mShortDescription;
    private String mDescription;

    private String mThumbnailURL;

    @Ignore
    private Volume.VolumeInfo.ImageLinks mImageLinks;


    private RealmList<Author> mAuthors = new RealmList<>();

    private RealmList<Publisher> mPublishers = new RealmList<>();

    private String mValueOfBookStatus;

    @Ignore
    private BookStatus mBookStatus = BookStatus.WANTED;
    private int mReadingProgress;
    private String mPreviewLink="";

    private String mValueOfBookType;
    @Ignore
    private BookType mBookType = BookType.Hardcover;


    public Book() {
        this.id = UUID.randomUUID().toString();
    }

    public Book(String ISBN,String title,String description,String author,String publisher){
        this();
        setISBN(ISBN);
        setTitle(title);
        setDescription(description);
        addAuthor(new Author(author));
        addPublisher(new Publisher(publisher));
    }

    public Book(String ISBN, String googleID,String title, ArrayList<Author> authors, String description, Volume.VolumeInfo.ImageLinks imageLinks,String previewLink) {
        this();
        setGoogleID(googleID);
        setISBN(ISBN);
        setTitle(title);
        for (Author author : authors) this.addAuthor(author);
setDescription(description);
        //setShortDescription(shortDescription);

        //setThumbnailURL
        if (imageLinks!=null) {
            if (imageLinks.getThumbnail()!=null)
            setThumbnailURL(imageLinks.getThumbnail());
        }
        else setThumbnailURL(""); //ik test later of er een thumbnail is door de testen op een lege string in de viewholder

        //setPreviewLink:
        this.setPreviewLink(previewLink);


    }

    // Book boek2 = new Book(book1); deze constructor zorgt er voor dat book2 de properties van book1 krijgt gekopieerd.
    //daarna maken we van book2, die we nog verder aan kunnen passen, een managed object voor Realm (via aanroep LibraryManagement.updateBook(updatedBook,this))
    public Book(Book update) {
        this();
        if (update!=null)
        importChanges(update);
    }

    public void importChanges(Book toCopy) {
        this.setId(toCopy.getId());
        this.setGoogleID(toCopy.getGoogleID());
        this.setISBN(toCopy.getISBN());
        this.setTitle(toCopy.getTitle());
//        this.setValueOfBookStatus(BookStatus.valueOf(toCopy.getValueOfBookStatus()));
        this.setValueOfBookType(toCopy.getValueOfBookType());
        this.setGoogleID(toCopy.getGoogleID());
        this.setReadingProgress(toCopy.getReadingProgress());
        this.setDescription(toCopy.getDescription());
        //this.setShortDescription(toCopy.getShortDescription());
        this.setThumbnailURL(toCopy.getThumbnailURL());
        this.setPreviewLink(toCopy.getPreviewLink());

        for (Author author : toCopy.getAuthors()) {
            this.addAuthor(author);
        }
        for (Publisher publisher : toCopy.getPublishers()) this.addPublisher(publisher);


        // TODO klopt dit?
    }

    //getters & setters


    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public String getISBN() {
        return mISBN;
    }

    public void setISBN(String ISBN) {
        mISBN = ISBN;
    }

    public String getGoogleID() {
        return mGoogleID;
    }

    public void setGoogleID(String googleID) {
        mGoogleID = googleID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public RealmList<Author> getAuthors() {
        return mAuthors;
    }

    public void addAuthor(Author author) {
        mAuthors.add(author);
    }

    public RealmList<Publisher> getPublishers() {
        return mPublishers;
    }

    public void addPublisher(Publisher publisher) {
        mPublishers.add(publisher);
    }


    public int getReadingProgress() {
        return mReadingProgress;
    }

    public void setReadingProgress(int readingProgress) {
        mReadingProgress = readingProgress;
    }

    public String getValueOfBookType() {
        return mBookType.name();
    }

    public void setValueOfBookType(String bookType) {
        mBookType = BookType.valueOf(bookType);
    }

    public String getValueOfBookStatus() {
        return mValueOfBookStatus;
    }

    public void setValueOfBookStatus(BookStatus bookStatus) {
        if (bookStatus!=null)
        mValueOfBookStatus = bookStatus.getBookStatusToString();
    }

//    public String getShortDescription() {
//        return mShortDescription;
//    }
//
//    public void setShortDescription(String shortDescription) {
//        mShortDescription = shortDescription;
//    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        if (id!=null)
        this.id = id;
    }


    public String getThumbnailURL() {
        return mThumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        if (thumbnailURL!=null)
        mThumbnailURL = thumbnailURL;
    }

    public Volume.VolumeInfo.ImageLinks getImageLinks() {
        if (mImageLinks!=null){
        return mImageLinks;}
        else return null;
    }

    public void setImageLinks(Volume.VolumeInfo.ImageLinks imageLinks) {
        if (imageLinks!=null)
        mImageLinks = imageLinks;
    }

    public void setPreviewLink(String previewLink) {
        if (previewLink!="" && previewLink!=null)
        mPreviewLink = previewLink;
    }

    public String getPreviewLink() {
        return mPreviewLink;
    }

    //TODO:Public Book Book.getInstance(uuid, booktitel, naam etc).
    //Als uuid niet null is (kan enkel als men heeft meegestuurd boog.getid) is return book met bestaande uuid, als uuid wél null is, moet men een nieuwe uuid setten op returned book.
         //   Reden: realm geeft exception als je managed book (=book is in db) buiten transactie set (schijnt ook bij andere orm libs geval te zijn). Dus moet men copy van managed boek maken (kopie is nieuw, staat niet in db (unmanaged)). Als men copy via methode createorupdate stuurt, wordt bestaande book overschreven (dankzij primary key=uuid). Nieuw book met ongekende uuid wordt dus toegevoegd.

    //Test het
//    public Book getInstance() {
//        if (this.getId()!=null){
//            return new Book()
//        }
//    }
}
