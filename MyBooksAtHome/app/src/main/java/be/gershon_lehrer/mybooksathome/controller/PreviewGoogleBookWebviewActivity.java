package be.gershon_lehrer.mybooksathome.controller;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.api.services.books.model.Geolayerdata;

import java.util.Locale;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;


/**
 * Created by gershonlehrer on 13/07/16.
 */
public class PreviewGoogleBookWebviewActivity extends AppCompatActivity{
    private static final String PREVIEWBOOKISBN="be.gershon-lehrer.mybooksathome.book_detail_google_book_preview_ISBN";
    private WebView mPreviewLinkWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_googlebooks_webview_activity_layout);
        Bundle data=getIntent().getExtras();
        String googleBooksPreviewISBN=data.getString(PREVIEWBOOKISBN);
        String googleBooksPreviewURL=getString(R.string.GooglePreviewLink_HTML_CODE);

        //calculate heigth and width in pixels
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String width=String.valueOf(metrics.widthPixels);
        String heigth=String.valueOf(metrics.heightPixels);

        googleBooksPreviewURL = googleBooksPreviewURL.replace("{{languagePlaceHolder}}", Locale.getDefault().getLanguage());
        googleBooksPreviewURL = googleBooksPreviewURL.replace("{{ISBNPlaceHolder}}",googleBooksPreviewISBN);
        googleBooksPreviewURL=googleBooksPreviewURL.replace("{{height}}",heigth);
        googleBooksPreviewURL=googleBooksPreviewURL.replace("{{width}}",width);
        Log.d(App.getTAG(),"LOADURL: "+googleBooksPreviewURL);

        loadWebViewContent(googleBooksPreviewURL);

    }


    private void loadWebViewContent(String googleBooksPreviewURL) {
        //als geen internetconnectie:
        if (!App.isNetworkAvailableAndConnected()) {
           Toast.makeText(this, getString(R.string.noInternetConnection), Toast.LENGTH_SHORT).show();

        }
        mPreviewLinkWebView = (WebView) findViewById(R.id.preview_google_book_webView);
        mPreviewLinkWebView.getSettings().setJavaScriptEnabled(true);
        mPreviewLinkWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mPreviewLinkWebView.getSettings().setLoadWithOverviewMode(true);
        mPreviewLinkWebView.getSettings().setUseWideViewPort(true);

        mPreviewLinkWebView.loadData(googleBooksPreviewURL, "text/html", null);
        mPreviewLinkWebView.setWebViewClient(new WebViewClient() {

            /**
             * Report web resource loading error to the host application. These errors usually indicate
             * inability to connect to the server. Note that unlike the deprecated version of the callback,
             * the new version will be called for any resource (iframe, image, etc), not just for the main
             * page. Thus, it is recommended to perform minimum required work in this callback.
             *
             * @param view    The WebView that is initiating the callback.
             * @param request The originating request.
             * @param error   Information about the error occured.
             */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(App.getContext(), "Oh no! " + error.toString(), Toast.LENGTH_SHORT).show();
            }

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (isNetworkAvailableAndConnected()) {
//                    if (URLUtil.isValidUrl(url)) {
//
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setDataAndType(Uri.parse(url), "application/pdf");
//                        try {
//                            view.getContext().startActivity(intent);
//                        } catch (ActivityNotFoundException e) {
//                            //user does not have a pdf viewer installed
//                        }
//                    } else {
//                        Log.d(App.getTAG(),"LOADURL: "+url);
//                        mPreviewLinkWebView.loadUrl(url);
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), R.string.noInternetForPDF, Toast.LENGTH_SHORT).show();
//
//                }
//                return true;
//            }
        });

    }


}
