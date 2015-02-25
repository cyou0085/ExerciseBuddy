package com.example.carlos.exercisebuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by carlosyoung on 2/3/15.
 */
public class DBAdapterSleep {
    public static final String KEY_ROWID = "id";
    public static final String KEY_ACTIVITY = "activity";
    public static final String KEY_DAY = "day";
    public static final String KEY_SLEEP = "sleep";
    public static final String KEY_WAKE = "wake";
    //public static final String KEY_NOTES = "notes";
    private static final String TAG = "DBAdapterSleep";

    private static final String DATABASE_NAME = "AssignmentsDB";
    private static final String DATABASE_TABLE = "assignments";
    private static final int DATABASE_VERSION = 5;

    private static final String DATABASE_CREATE =
            "create table if not exists " + DATABASE_TABLE + " (id integer primary key autoincrement, "
                    + KEY_DAY + " VARCHAR not null," + KEY_ACTIVITY + " VARCHAR not null," + KEY_SLEEP + " time, " + KEY_WAKE + " time);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapterSleep(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapterSleep open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a record into the database---
    public long insertRecord(String activity, String day, String sleep, String awake)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ACTIVITY,activity);
        initialValues.put(KEY_DAY,day);
        initialValues.put(KEY_SLEEP, sleep);
        initialValues.put(KEY_WAKE, awake);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_ACTIVITY, KEY_DAY,
                KEY_SLEEP, KEY_WAKE}, null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getRecord(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_ACTIVITY,
                                KEY_DAY, KEY_SLEEP, KEY_WAKE},
                        KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateRecord(int rowId, String activity,String day, String sleep, String awake)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_ROWID,rowId);
        args.put(KEY_ACTIVITY,activity);
        args.put(KEY_DAY, day);
        args.put(KEY_SLEEP, sleep);
        args.put(KEY_WAKE, awake);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

}
