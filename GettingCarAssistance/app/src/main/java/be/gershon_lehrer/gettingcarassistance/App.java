package be.gershon_lehrer.gettingcarassistance;

import android.app.Application;
import android.content.Context;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

/**
 * Created by gershonlehrer on 24/04/2017.
 */

public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // Don't do this! This is just so cold launches take some time
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static App getInstance(){
        return mInstance;
    }
}
