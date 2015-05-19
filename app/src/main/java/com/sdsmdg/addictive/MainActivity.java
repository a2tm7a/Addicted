package com.sdsmdg.addictive;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends Activity {

    private static final String KEY_PACKAGE_NAME = "package_name";
    private static final String KEY_APP_NAME = "app_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity.class", ": OnCreate");


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int LOGIN_IN = preferences.getInt("Login_In", 0);



        //Login for the first time
        if(LOGIN_IN==0)
        {
            LOGIN_IN=1;
            SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit() ;
            editor.putInt("Login_In", LOGIN_IN);
            editor.commit();

            SharedPreferences preferences12 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            LOGIN_IN = preferences12.getInt("Login_In", 0);
            Log.d("Login_in",LOGIN_IN+"");
            allApps();
            startService(new Intent(MainActivity.this, BackgroundService.class));

        }


        //Every time the person logs in we need the information to be shown
        UsageDB read = new UsageDB(getApplicationContext());
        read.open_read();
        Cursor c = read.getInfo(KEY_PACKAGE_NAME, KEY_APP_NAME);
        c.moveToFirst();
        while(c.moveToNext())
        {

            Log.d("Read From Database",c.getString(c.getColumnIndex(KEY_PACKAGE_NAME))+" : "+c.getString(c.getColumnIndex(KEY_APP_NAME)));

        }

    }

    /* allApps function is finding all the running apps and then sending the data to a DBHelper function to insert all the data in
    the DB.
   */
    public void allApps()
    {

        UsageDB write=new UsageDB(getApplicationContext());
        write.open_write();

        Log.d("MainActivity : ", "allApps"  );
        String appname,pname;
        List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
        for(int i=0;i<apps.size();i++) {
            PackageInfo p = apps.get(i);

            appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
            pname = p.packageName;
            Log.d("Installed Apps",appname+" : "+pname);

            write.putInformation(pname,appname);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
