package be.gershon_lehrer.mybooksathome.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.zip.Inflater;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.model.Book;
import be.gershon_lehrer.mybooksathome.model.Publisher;
import be.gershon_lehrer.mybooksathome.model.SearchMethodGoogleApi;

/**
 * Created by gershonlehrer on 14/07/16.
 */
public class AddNewBookActivity extends AppCompatActivity{
private ImageButton mScannerImageButton;
    private EditText mISBNeditText;
    private EditText mAuthorEditText;
    private EditText mDescriptionEditText;
    private EditText mPublisherEditText;
    private EditText mTitleEditText;
    private Button mSubmit;
    private Button mCancel;



    private String mScannedBarcode="";
    private final static int BOOKSCAN_INTENT=0;
    private String mTitle;
    String mDescription;String mPublisher;String mAuthor;String mISBN;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_book_activity_layout);

mISBNeditText=(EditText)findViewById(R.id.ISBN_add_new_book_activity_layout_editText);
        mTitleEditText=(EditText)findViewById(R.id.title_add_new_book_activity_layout_editText);
        mAuthorEditText=(EditText)findViewById(R.id.author_add_new_book_layout_editText);
        mPublisherEditText=(EditText)findViewById(R.id.publisher_add_new_book_activity_editText);
        mDescriptionEditText=(EditText)findViewById(R.id.description_add_new_book_activity_editText);

        mCancel=(Button)findViewById(R.id.cancel_button_in_add_new_book_activity_layout);
        mSubmit=(Button)findViewById(R.id.ok_submit_in_add_new_book_activity_layout);

        mTitle=mTitleEditText.getText().toString();

        mAuthor = mAuthorEditText.getText().toString();
        mPublisher = mPublisherEditText.getText().toString();
        mDescription = mDescriptionEditText.getText().toString();

        mScannerImageButton=(ImageButton)findViewById(R.id.scan_add_book_layout_imageButtonimageButton);
        mScannerImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewBookActivity.this, BarCodeScannerActivity.class);
                startActivityForResult(intent, BOOKSCAN_INTENT);
            }
        });

        //listeners buttons:
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mISBN=mISBNeditText.getText().toString();
                mTitle=mTitleEditText.getText().toString();
                mDescription=mDescriptionEditText.getText().toString();
                mAuthor=mAuthorEditText.getText().toString();
                mPublisher=mPublisherEditText.getText().toString();

                Book book = new Book(mISBN, mTitle, mDescription, mAuthor, mPublisher);
                Log.d(App.getTAG(),"BOOK ADDED"+book.getId()+" "+book.getTitle());
                LibraryManagement.addOrUpdateBook(book);
                finish();
            }
        });



        //get text from edittext





    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==BOOKSCAN_INTENT){
            mScannedBarcode=(String)data.getExtras().get("result");
            mISBNeditText.setText(mScannedBarcode);
            Log.v(App.getTAG(),"ISBN gescand: "+mScannedBarcode);

        }
    }

}
