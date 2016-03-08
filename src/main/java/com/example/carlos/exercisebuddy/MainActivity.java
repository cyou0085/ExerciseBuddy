package com.example.carlos.exercisebuddy;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
<<<<<<< Updated upstream
=======
import java.util.Calendar;
>>>>>>> Stashed changes




public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ActivityListAdapter adapter;
    ArrayList<Activity> activityList = new ArrayList <Activity>();
    RectF rect =  new RectF();
<<<<<<< Updated upstream
    Button setNotify;
=======
    //Button setNotify;
>>>>>>> Stashed changes

    public final static String ACTIVITY_ID = "com.example.carlos.exercisebuddy.MainActivity.tvID";
    //boolean alreadyBeen = false;

    //@Override
    protected void onCreate(Bundle savedInstanceState) {

<<<<<<< Updated upstream
        //String activityID = getIntent().getStringExtra(SleepActivity.ACTIVITY_TIMES);
        //String sleepTimes = activityID;
        //Intent numbers = getIntent();
       // String nums = numbers.getStringExtra("mSleep"," ");
        /*if (activityID != null) {
            Log.i("DREW", activityID);
            //Log.i("Carlos", updateActivity.getActivity() + " " + updateActivity.getDayOfWeek() + updateActivity.getStartHour() + updateActivity.getStartMinute());
        } else {
            activityID = "00 00 00 00 00 00 00 00";
            Log.i("DW", "no good");
        }*/
=======

>>>>>>> Stashed changes
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBAdapterActivity db = new DBAdapterActivity(this);
<<<<<<< Updated upstream
        setNotify = (Button) findViewById(R.id.button4);
        setNotify.setOnClickListener(this);
=======

>>>>>>> Stashed changes
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        // Get all the Activities from the db and tvs them in an ArrayList
        //Then Displays the List in the View
        db.open();
        ArrayList <Activity>ActivityList = db.getAllRecords();
        activityList = ActivityList;

        db.close();
        try {
            String destPath = "/data/data/" + getPackageName() + "/databases/ActivityDB";
            File f = new File(destPath);
            if (!f.exists()) {
                CopyDB( getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        // Populate the WeekView's activities with the items that were in the
        // database upon application startup.
        WeekView wv = (WeekView) findViewById(R.id.view2);
<<<<<<< Updated upstream
        //if (activityID != null)
          //  wv.addSleep(sleepTimes);
=======
>>>>>>> Stashed changes
        wv.addActivities(activityList);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.button4){
            intent.setClass(this,MyNotificationService.class);
            startService(intent);
        }

<<<<<<< Updated upstream
    }


    private class DBAdapterSleep extends BaseAdapter {
        private LayoutInflater mInflater;
        //private ArrayList<>

        @Override
        public int getCount() {

            return 0;
        }

        @Override
        public Object getItem(int arg0) {

            return null;
        }

        @Override
        public long getItemId(int arg0) {

            return 0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {

            return null;
        }

    }

=======
>>>>>>> Stashed changes
    //Adds an Activity to db
    public void AddActivity(View view){
        Intent intent;

            intent = new Intent(getBaseContext(), AddActivity.class);
            startActivity(intent);

    }

<<<<<<< Updated upstream
    //Adds an Activity to db
    public void AddSleep(View view){

/*
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);
        Intent intent = new Intent();
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(this.getResources().getString(R.string.title_activity_week))
                        .setContentText(this.getResources().getString(R.string.title_activity_update))
                        .setAutoCancel(true);

        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification noti = new Notification.Builder(this)
                .setTicker("Ticker Title")
                .setContentTitle("Content Title")
                .setContentText("Notification content.")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent).getNotification();
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());*/

    }
    //Takes you the Week View of your DataBase
    /*
    public void WeekActivity(View view){

        Intent intent;
        intent = new Intent(getBaseContext(), WeekView.class);

        startActivity(intent);

    }*/

/*
    public void FindRecord()
    {
       // ListView lv = (ListView)findViewById(R.id.listView1);

        adapter = new ActivityListAdapter(activityList, MainActivity.this);
        lv.setAdapter(adapter);
        lv.setClickable(true);
        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(),UpdateActivity.class);
                String activityId = Long.toString(activityList.get(position).getID());
                Log.i("DW", "Adding " + activityId + " to intent");
                i.putExtra(ACTIVITY_ID, activityId);
                startActivity(i);

            }
        });
    }
*/



//
//        public boolean onTouchEvent(MotionEvent event) {
//
//        int action = event.getAction() ;
//        float x = event.getX() ;
//        float y = event.getY() ;
//
//        //int width = this.getw;
//        //int height = this.getHeight() ;
//
//        switch(action) {
//            case MotionEvent.ACTION_DOWN:
//
//                if(rect.contains(x, y)) {
//                    // Something should happen
//                    Log.i("Carlos", "your x:" + x + "your y:" + y);
//                }
//
//        }
//            return true ;
//        }


        public void FindRecordView()
    {
        final WeekView lv = (WeekView)findViewById(R.id.view2);
        lv.addActivities(activityList);
        rect = lv.mActivityRegions.get(1);
        adapter = new ActivityListAdapter(activityList, MainActivity.this);

        lv.setClickable(true);
        lv.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),UpdateActivity.class);

                //for(int i = 0; i <= lv.mActivityRegions.size();i++) {
                    if (lv.mActivityRegions.get(0).contains(v.getX(),v.getY()))
                        startActivity(intent);
                //}

                Log.i("Carlos", "You Clicked " + activityList.get(1).getActivity() + " at zero");
                Log.i("Carlos", "You Clicked " + v.getX() + " " + v.getY() + " at zero");

                Log.i("Carlos", "You Clicked " + lv.mActivityRegions.get(0) + " at zero");


            }
        });
    }
=======
>>>>>>> Stashed changes

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                openSettings();
                return true;
            case R.id.action_search:
                openSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettings() {

        startActivity(new Intent(Settings.ACTION_SETTINGS));
    }

    private void openSearch(){
        startActivity(new Intent(SearchManager.INTENT_ACTION_GLOBAL_SEARCH));
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

}
