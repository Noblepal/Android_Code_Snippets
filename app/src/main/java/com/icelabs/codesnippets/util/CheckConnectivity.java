package com.icelabs.codesnippets.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

import com.icelabs.codesnippets.base.MainApplication;

import java.io.IOException;

public class CheckConnectivity {

    public static final int INTERNET_CONNECTED = 1;
    public static final int INTERNET_NOT_CONNECTED = 2;
    public static final int NETWORK_NOT_CONNECTED = 3;
    public static final int PING_FAILURE = 4;

    private static final String TAG = "CheckConnectivity";

    /*
     * TODO: Required permission android.permission.ACCESS_NETWORK_STATE
     * Check if network is connected: Mobile Data or WiFi
     * */

    private static boolean checkNetworkAvailability() {
        Context context = MainApplication.getInstance().getApplicationContext();
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }
        Log.d(TAG, "Wifi connected: " + isWifiConn);
        Log.d(TAG, "Mobile connected: " + isMobileConn);

        return isMobileConn || isWifiConn;

    }

    /*Check if can actually connect to the internet*/
    public static int isConnected() {

        if (!checkNetworkAvailability()) {
            return NETWORK_NOT_CONNECTED;
        }

        /*Send a ping to www.google.com*/

        final String command = "ping -i 5 -c 1 google.com";
        try {
            return Runtime.getRuntime().exec(command).waitFor() == 0 ? INTERNET_CONNECTED : INTERNET_NOT_CONNECTED;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return PING_FAILURE;
        }
    }
}
