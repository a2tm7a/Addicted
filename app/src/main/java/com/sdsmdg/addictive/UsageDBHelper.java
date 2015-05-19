package com.sdsmdg.addictive;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by amit on 17/5/15.
 */
public class UsageDBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="Addictive.db";
    private static final int DATABASE_VERSION=3;

    private static final String TEXT_TYPE="text";
    private static final String INT_TYPE="integer";
    private static final String KEY_TIME = "time";


    private static final String DATABASE_CREATE="create table "+ CommonUtilities.DATABASE_TABLE+ " ("+
            CommonUtilities.KEY_PACKAGE_NAME + " "+ TEXT_TYPE +" not null, "+
            CommonUtilities.KEY_APP_NAME +  " "+TEXT_TYPE +" not null, "+
            CommonUtilities.KEY_TIME +  " "+INT_TYPE +" default 0);";

    private static final String DROP_DATABASE="DROP TABLE IF EXISTS " + CommonUtilities.DATABASE_TABLE;

    private static UsageDBHelper instance;

    public UsageDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // synchronized used to prevent multiple threading
    public static synchronized UsageDBHelper getInstance(Context context)
    {
        if(instance == null)
            instance = new UsageDBHelper(context);
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
        Log.d("database created", "true");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_DATABASE);
        onCreate(db);
    }

}
