package be.gershon_lehrer.mybooksathome.controller;


import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class BarCodeScannerActivity extends AppCompatActivity {
    private final static int BOOKSCAN_INTENT=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent output = new Intent();
                output.putExtra("result", result.getContents());
                setResult(BOOKSCAN_INTENT, output);
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}





