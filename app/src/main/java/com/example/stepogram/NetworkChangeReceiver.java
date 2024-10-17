// NetworkChangeReceiver.java
package com.example.stepogram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            // Подключение к сети
            Toast.makeText(context, "Подключено к сети", Toast.LENGTH_SHORT).show();
        } else {
            // Отключение от сети
            Toast.makeText(context, "Отключено от сети", Toast.LENGTH_SHORT).show();
        }
    }
}
