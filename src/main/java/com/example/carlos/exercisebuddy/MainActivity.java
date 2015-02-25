package com.example.carlos.exercisebuddy;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
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
import android.database.Cursor;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    ActivityListAdapter adapter;
    ArrayList<Activities> activityList = new ArrayList <Activities>();
    public final static String Extra_Link = "com.example.carlos.exercisebuddy.MainActivity.tvID";
    //@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String destPath = "/data/data/" + getPackageName() + "/databases/AssignmentDB";
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

        com.example.carlos.exercisebuddy.DBAdapterSleep db = new com.example.carlos.exercisebuddy.DBAdapterSleep(this);


        // Get the message from the intent
        db.open();
        Cursor c = db.getAllRecords();
        DisplayRecord(c);
        db.close();



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

    public void AddActivity(View view){
        Intent intent;

            intent = new Intent(getBaseContext(), DisplayActivity.class);
            startActivity(intent);

    }

    public void EditActivity(View view){

        Intent intent;
        intent = new Intent(getBaseContext(), UpdateActivity.class);

        startActivity(intent);

    }


    public void DisplayRecord(Cursor c)
    {

        Activities activities;
        ListView lv = (ListView)findViewById(R.id.listView1);

        if(c.moveToFirst()){
            do{

                activities = new Activities(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                activityList.add(activities);
                adapter = new ActivityListAdapter(activityList,MainActivity.this);
                lv.setAdapter(adapter);

            }while(c.moveToNext());
        }
        lv.setOnItemClickListener(onListClick);
    }


    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(getBaseContext(),UpdateActivity.class);
            i.putExtra(Extra_Link,String.valueOf(R.id.tvID));
            startActivity(i);
        }
    };


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
