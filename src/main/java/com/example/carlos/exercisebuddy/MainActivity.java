package com.example.carlos.exercisebuddy;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.RectF;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ActivityListAdapter adapter;
    ArrayList<Activity> activityList = new ArrayList <Activity>();
    RectF rect =  new RectF();

    public final static String ACTIVITY_ID = "com.example.carlos.exercisebuddy.MainActivity.tvID";


    //@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        DBAdapterActivity db = new DBAdapterActivity(this);

        // Get all the Activities from the db and puts them in an ArrayList
        //Then Displays the List in the View
        db.open();
        ArrayList <Activity>ActivityList = db.getAllRecords();
        activityList = ActivityList;
        FindRecord();
        FindRecordView();
        db.close();

        // Populate the WeekView's activities with the items that were in the
        // database upon application startup.
        WeekView wv = (WeekView) findViewById(R.id.view2);
        wv.addActivities(activityList);
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

    //Adds an Activity to db
    public void AddActivity(View view){
        Intent intent;

            intent = new Intent(getBaseContext(), AddActivity.class);
            startActivity(intent);

    }

    //Takes you the Week View of your DataBase
    /*
    public void WeekActivity(View view){

        Intent intent;
        intent = new Intent(getBaseContext(), WeekView.class);

        startActivity(intent);

    }*/


    public void FindRecord()
    {
        ListView lv = (ListView)findViewById(R.id.listView1);

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

                for(int i = 0; i <= lv.mActivityRegions.size();i++) {
                    if (lv.mActivityRegions.get(i).equals(v))
                        startActivity(intent);
                }

                Log.i("Carlos", "You Clicked " + activityList.get(1).getActivity() + " at zero");

                Log.i("Carlos", "You Clicked " + lv.mActivityRegions.get(1) + " at zero");


            }
        });
    }

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
