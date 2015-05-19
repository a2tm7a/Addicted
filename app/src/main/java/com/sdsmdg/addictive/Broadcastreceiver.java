package com.sdsmdg.addictive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by amit on 18/5/15.
 */
public class Broadcastreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String data=intent.getData().toString();
        Log.d("Broadcastreceiver","onReceive : "+data);
    }
}
