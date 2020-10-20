package com.icelabs.codesnippets.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class MainApplication extends Application {

    private static MainApplication mInstance;
    private static final String TAG = "MainApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MainApplication getInstance() {
        if (mInstance == null) {
            Log.e(TAG, "getInstance: mInstance == null");
            mInstance = new MainApplication();
        }
        return mInstance;
    }

    /*Check network connectivity*/
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
