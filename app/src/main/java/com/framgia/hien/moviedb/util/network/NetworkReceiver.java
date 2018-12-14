package com.framgia.hien.moviedb.util.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkReceiver extends BroadcastReceiver {
    private NetworkStateListener mListener;

    public NetworkReceiver(NetworkStateListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo;
        if (connectivityManager == null) {
            networkInfo = null;
        } else {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            mListener.onNetworkConnected();
        } else {
            mListener.onNetworkDisconnected();
        }
    }

    public interface NetworkStateListener {
        void onNetworkConnected();

        void onNetworkDisconnected();
    }
}
