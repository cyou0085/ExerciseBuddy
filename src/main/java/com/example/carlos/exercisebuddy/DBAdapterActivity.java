package com.example.carlos.exercisebuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by carlosyoung on 2/3/15.
 */
public class DBAdapterActivity {

    //Column Names In DB
    public static final String KEY_ROWID = "id";
    public static final String KEY_ACTIVITY = "activity";
    public static final String KEY_DAY = "day";
    public static final String KEY_STARTH = "startH";
    public static final String KEY_STARTM = "startM";
    public static final String KEY_ENDH = "endH";
    public static final String KEY_ENDM = "endM";
    public static final String KEY_START_AM = "startAMorPM";
    public static final String KEY_END_AM = "endAMorPM";
    public static final String KEY_NOTES = "notes";
    private static final String TAG = "DBAdapterActivity";
    //Table Name and version
    private static final String DATABASE_NAME = "mydbExerciseBuddy";
    private static  String DATABASE_TABLE = " myactivity";
    private static final int DATABASE_VERSION = 2;

    //Creates Table
    /*private static final String DATABASE_CREATE =
            "CREATE TABLE " + DATABASE_TABLE + " (id integer primary key autoincrement, "
                    + KEY_DAY + " VARCHAR not null," + KEY_ACTIVITY + " VARCHAR not null," + KEY_SLEEP + " VARCHAR," + KEY_WAKE + " VARCHAR," + KEY_START_AM + " VARCHAR," + KEY_END_AM + " VARCHAR)";*/
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + DATABASE_TABLE + " (id integer primary key autoincrement, "
                    + KEY_DAY + " VARCHAR not null," + KEY_ACTIVITY + " VARCHAR not null," + KEY_STARTH + " INTEGER," + KEY_STARTM + " INTEGER,"+ KEY_ENDH + " INTEGER," + KEY_ENDM + " INTEGER," + KEY_START_AM + " BOOLEAN," + KEY_END_AM + " BOOLEAN," + KEY_NOTES + " VARCHAR)";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapterActivity(Context ctx)
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
        //Deletes Old Table and upgrades to a higher version
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE" + DATABASE_TABLE);
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapterActivity open() throws SQLException
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
   /* public long insertRecord(String activity, String day, String sleep, String awake,String am1, String am2)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ACTIVITY,activity);
        initialValues.put(KEY_DAY,day);
        initialValues.put(KEY_SLEEP,sleep);
        initialValues.put(KEY_WAKE,awake);
        initialValues.put(KEY_START_AM,am1);
        initialValues.put(KEY_END_AM,am2);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }*/
    public long insertRecord(String activity, String day,int startH, int startM, int endH, int endM,boolean startAM,boolean endAM,String notes)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ACTIVITY,activity);
        initialValues.put(KEY_DAY,day);
        initialValues.put(KEY_STARTH,startH);
        initialValues.put(KEY_STARTM,startM);
        initialValues.put(KEY_ENDH,endH);
        initialValues.put(KEY_ENDM,endM);
        initialValues.put(KEY_START_AM,startAM);
        initialValues.put(KEY_END_AM,endAM);
        initialValues.put(KEY_NOTES,notes);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public ArrayList<Activity> getAllRecords()
    {
        ArrayList<Activity> Activity_aList = new ArrayList<Activity>();
        Cursor c = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_ACTIVITY, KEY_DAY,KEY_STARTH,KEY_STARTM,KEY_ENDH,KEY_ENDM,KEY_START_AM,KEY_END_AM,KEY_NOTES}, null, null, null, null,null);
        if(c != null){
            if(c.moveToFirst()){
                do{

                    Activity Activities = new Activity();
                    Activities.setId(c.getLong(c.getColumnIndex(DBAdapterActivity.KEY_ROWID)));
                    Activities.setActivity(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_ACTIVITY)));
                    Activities.setDayOfWeek(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_DAY)));
                    Activities.setStartHour(c.getInt(c.getColumnIndex(DBAdapterActivity.KEY_STARTH)));
                    Activities.setStartMinute(c.getInt(c.getColumnIndex(DBAdapterActivity.KEY_STARTM)));
                    Activities.setEndHour(c.getInt(c.getColumnIndex(DBAdapterActivity.KEY_ENDH)));
                    Activities.setEndMinute(c.getInt(c.getColumnIndex(DBAdapterActivity.KEY_ENDM)));
                    Activities.setStartAMorPM(Boolean.parseBoolean(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_START_AM))));
                    Activities.setEndAMorPM(Boolean.parseBoolean(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_END_AM))));
                    Activities.setNotes(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_NOTES)));
                    Activity_aList.add(Activities);
                }while(c.moveToNext());
            }
        }
        return Activity_aList;/*
        ArrayList<Activity> Activity_aList = new ArrayList<Activity>();
        Cursor c = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_ACTIVITY, KEY_DAY,KEY_SLEEP, KEY_WAKE,KEY_START_AM,KEY_END_AM}, null, null, null, null,null);
        if(c != null){
            if(c.moveToFirst()){
                do{
                    Activity Activities = new Activity();
                    Activities.setId(c.getLong(c.getColumnIndex(DBAdapterActivity.KEY_ROWID)));
                    Activities.setActivity(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_ACTIVITY)));
                    Activities.setDay(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_DAY)));
                    Activities.setStart(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_SLEEP)));
                    Activities.setEnd(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_WAKE)));
                    Activities.setAm1(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_START_AM)));
                    Activities.setAm2(c.getString(c.getColumnIndex(DBAdapterActivity.KEY_END_AM)));
                Activity_aList.add(Activities);
                }while(c.moveToNext());
            }
        }
        return Activity_aList;*/
    }

    //---retrieves a particular record---
    public Activity getRecord(long rowId) throws SQLException
    {
        Activity indexActivity = new Activity();

        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_ACTIVITY,
                                KEY_DAY, KEY_STARTH,KEY_STARTM,KEY_ENDH,KEY_ENDM,KEY_START_AM,KEY_END_AM,KEY_NOTES},
                        KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        indexActivity.setId(mCursor.getLong(mCursor.getColumnIndex(DBAdapterActivity.KEY_ROWID)));
        indexActivity.setActivity(mCursor.getString(mCursor.getColumnIndex(DBAdapterActivity.KEY_ACTIVITY)));
        indexActivity.setDayOfWeek(mCursor.getString(mCursor.getColumnIndex(DBAdapterActivity.KEY_DAY)));
        indexActivity.setStartHour(mCursor.getInt(mCursor.getColumnIndex(DBAdapterActivity.KEY_STARTH)));
        indexActivity.setStartMinute(mCursor.getInt(mCursor.getColumnIndex(DBAdapterActivity.KEY_STARTM)));
        indexActivity.setEndHour(mCursor.getInt(mCursor.getColumnIndex(DBAdapterActivity.KEY_ENDH)));
        indexActivity.setEndMinute(mCursor.getInt(mCursor.getColumnIndex(DBAdapterActivity.KEY_ENDM)));
        indexActivity.setStartAMorPM(Boolean.parseBoolean(mCursor.getString(mCursor.getColumnIndex(DBAdapterActivity.KEY_START_AM))));
        indexActivity.setEndAMorPM(Boolean.parseBoolean(mCursor.getString(mCursor.getColumnIndex(DBAdapterActivity.KEY_END_AM))));
        indexActivity.setNotes(mCursor.getString(mCursor.getColumnIndex(DBAdapterActivity.KEY_NOTES)));
        return indexActivity;
    }

    //---updates a record---
    /*public boolean updateRecord(long rowId, String activity,String day, String sleep, String awake,String am1, String am2)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_ROWID,rowId);
        args.put(KEY_ACTIVITY,activity);
        args.put(KEY_DAY, day);
        args.put(KEY_SLEEP, sleep);
        args.put(KEY_WAKE, awake);
        args.put(KEY_START_AM,am1);
        args.put(KEY_END_AM,am2);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }*/
    public boolean updateRecord(long rowId, String activity,String day,int startH, int startM, int endH, int endM,boolean startAM,boolean endAM,String notes)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_ACTIVITY, activity);
        args.put(KEY_DAY, day);
        args.put(KEY_STARTH, startH);
        args.put(KEY_STARTM, startM);
        args.put(KEY_ENDH, endH);
        args.put(KEY_ENDM, endM);
        args.put(KEY_START_AM, startAM);
        args.put(KEY_END_AM, endAM);
        args.put(KEY_NOTES,notes);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
