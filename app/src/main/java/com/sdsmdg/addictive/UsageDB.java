package com.sdsmdg.addictive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by amit on 19/5/15.
 */
public class UsageDB {
    private Context context;
    private SQLiteDatabase db;

    private UsageDBHelper dbHelper;

    public UsageDB(Context context)
    {
        this.context=context;
        dbHelper=new UsageDBHelper(context);

    }
    public void open_write()
    {
        db=dbHelper.getWritableDatabase();

    }

    public void open_read()
    {
        db=UsageDBHelper.getInstance(context).getReadableDatabase();
    }
    public void close()
    {
        dbHelper.close();
    }


    // not sure if correct way or not

    public void putInformation(String package_name, String app_name)
    {
        ContentValues values = new ContentValues();
        values.put(CommonUtilities.KEY_PACKAGE_NAME,package_name);
        values.put(CommonUtilities.KEY_APP_NAME,app_name);
        Log.d(app_name,package_name);
        db.insert(CommonUtilities.DATABASE_TABLE, null, values);

    }

    public Cursor getInfo(String... args)
    {

        Cursor cursor=db.query(CommonUtilities.DATABASE_TABLE,args, null, null, null, null, null);
        return cursor;
    }
    public void Drop_Table()
    {
        dbHelper.onUpgrade(db,2,3);
    }
}
