package be.gershon_lehrer.gettingcarassistance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //see https://github.com/cstew/Splash/blob/master/app/src/main/java/com/bignerdranch/android/splash/SplashActivity.java
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
