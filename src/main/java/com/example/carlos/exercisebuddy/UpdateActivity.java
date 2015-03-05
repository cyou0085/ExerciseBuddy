package com.example.carlos.exercisebuddy;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends ActionBarActivity {
    DBAdapterSleep db = new DBAdapterSleep(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Activity updateActivity = new Activity();
        String activityID = getIntent().getStringExtra(MainActivity.ACTIVITY_ID);
        if (activityID != null) {
            Log.i("DAVID", activityID);
            db.open();
            updateActivity = db.getRecord(Long.valueOf(activityID));
            db.close();
            Log.i("Carlos", updateActivity.getActivity());
        } else {
            Log.i("DW", "no good");
        }


        int activityName = 0;

        Spinner dropdown = (Spinner) findViewById(R.id.ActivityName);
        String[] items = new String[]{"Sleep", "Work", "Workout", "Class", "Eating"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        switch (updateActivity.getActivity()) {

            case "Sleep":
                activityName = 0;
                break;
            case "Work":
                activityName = 1;
                break;
            case "Workout":
                activityName = 2;
                break;
            case "Class":
                activityName = 3;
                break;
            case "Eating":
                activityName = 4;
                break;
            default:
                activityName = -1;
        }

        dropdown.setSelection(activityName);


        int activityWeek = 0;
        Spinner dropdownWeek = (Spinner) findViewById(R.id.dayOfWeek);
        String[] itemsWeek = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsWeek);
        dropdownWeek.setAdapter(adapter2);
//        String activityID = getIntent().getStringExtra(MainActivity.ACTIVITY_ID);
//        dropdownWeek.setSelection(Integer.parseInt(passedvar));


        switch (updateActivity.getDay()) {

            case "Sunday":
                activityWeek = 0;
                break;
            case "Monday":
                activityWeek = 1;
                break;
            case "Tuesday":
                activityWeek = 2;
                break;
            case "Wednesday":
                activityWeek = 3;
                break;
            case "Thursday":
                activityWeek = 4;
                break;
            case "Friday":
                activityWeek = 5;
                break;
            case "Saturday":
                activityWeek = 6;
                break;
            default:
                activityWeek = -1;
        }

        dropdownWeek.setSelection(activityWeek);

        EditText startText = (EditText) findViewById(R.id.startTime);
        startText.setText(updateActivity.getStart());
        int activityAM = 0;
        Spinner dropdownAM = (Spinner) findViewById(R.id.amOrpm);
        String[] itemsTime = new String[]{"AM", "PM"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsTime);
        dropdownAM.setAdapter(adapter3);

        switch(updateActivity.getStartAM()){
        case "AM":
            activityAM = 0;
            break;
        case "PM":
            activityAM = 1;
            break;
        default:
            activityAM = -1;
        }

        dropdownAM.setSelection(activityAM);


        EditText endText = (EditText)findViewById(R.id.endTime);
        endText.setText(updateActivity.getEnd());
        int activityPM = 0;
        Spinner dropdownAM2 = (Spinner)findViewById(R.id.amOrpm2);
        String[] itemsTime2 = new String[]{"AM","PM"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, itemsTime2);
        dropdownAM2.setAdapter(adapter4);



        switch(updateActivity.getEndAM()){
            case "AM":
                activityPM = 0;
                break;
            case "PM":
                activityPM = 1;
                break;
            default:
                activityPM = -1;
        }

        dropdownAM2.setSelection(activityPM);
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

    public void UpdateActivity(View view) {
        Intent intent;
        EditText editText;
        EditText editText2;
        Spinner mySpinner;
        Spinner mySpinner2;
        Spinner mySpinner3;
        Spinner mySpinner4;
        String activityID = getIntent().getStringExtra(MainActivity.ACTIVITY_ID);

        intent = new Intent(this,MainActivity.class);
        editText = (EditText) findViewById(R.id.startTime);
        editText2 = (EditText) findViewById(R.id.endTime);
        mySpinner = (Spinner) findViewById(R.id.ActivityName);
        mySpinner2 = (Spinner) findViewById(R.id.dayOfWeek);
        mySpinner3 = (Spinner) findViewById(R.id.amOrpm);
        mySpinner4 = (Spinner) findViewById(R.id.amOrpm2);

        db.open();
        db.updateRecord(Long.valueOf(activityID), mySpinner.getSelectedItem().toString(), mySpinner2.getSelectedItem().toString(), editText.getText().toString(), editText2.getText().toString(),mySpinner3.getSelectedItem().toString(),mySpinner4.getSelectedItem().toString());
        db.close();
        startActivity(intent);
        Toast.makeText(this, "Activity has been Updated", Toast.LENGTH_SHORT).show();

    }
    public void ViewActivity(View view){
        Intent intent;
        intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);

    }



}
