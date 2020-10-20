package com.icelabs.codesnippets.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.icelabs.codesnippets.R;
import com.icelabs.codesnippets.databinding.ActivityCheckOnlineStatusBinding;
import com.icelabs.codesnippets.util.AppExecutors;
import com.icelabs.codesnippets.util.CheckConnectivity;

import static com.icelabs.codesnippets.util.CheckConnectivity.INTERNET_CONNECTED;
import static com.icelabs.codesnippets.util.CheckConnectivity.INTERNET_NOT_CONNECTED;
import static com.icelabs.codesnippets.util.CheckConnectivity.NETWORK_NOT_CONNECTED;

public class CheckOnlineStatusActivity extends AppCompatActivity {

    private ActivityCheckOnlineStatusBinding b;
    private AppExecutors appExecutors;
    private String netStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_check_online_status);

        appExecutors = AppExecutors.getInstance();

        pingGoogle();

        b.btnRetryOnline.setOnClickListener(v -> {
            pingGoogle();
        });

    }

    private void pingGoogle() {
        netStat = "Online status: ";
        b.tvOnlineStatus.setText("Checking...");
        appExecutors.networkIO().execute(() -> {
            switch (CheckConnectivity.isConnected()) {
                case INTERNET_CONNECTED:
                    netStat += "Internet connected";
                    break;
                case INTERNET_NOT_CONNECTED:
                    netStat += "Internet not connected";
                    break;
                case NETWORK_NOT_CONNECTED:
                    netStat += "Network not connected";
                    break;
            }

            appExecutors.mainThread().execute(() -> {
                b.tvOnlineStatus.setText("");
                b.tvOnlineStatus.setText(netStat);
            });

        });
        b.tvOnlineStatus.setText(netStat);
    }
}