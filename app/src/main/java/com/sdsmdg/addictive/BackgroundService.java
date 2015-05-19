package com.sdsmdg.addictive;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

/**
 * Created by amit on 17/5/15.
 */
public class BackgroundService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Background Service : ", "onDestroy");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Background Service : ", "onCreate"  );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // TODO Auto-generated method stub

        Log.d("Background Service : ", "onStartCommand"  );

        //runningApps();
        //allApps();

        return START_STICKY;                         // to start the service again if it is being destroyed due to low memory when there is sufficient memory.


    }


    public void runningApps()
    {
        Log.d("Background Service : ", "runningApps"  );
        ActivityManager activityManager = (ActivityManager) getSystemService(this.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (int i = 0; i < recentTasks.size(); i++)
        {
            Log.d("Executed app", "Application executed : " + recentTasks.get(i).baseActivity.toShortString() + "\t\t ID: " + recentTasks.get(i).id + "");
        }

    }


}
