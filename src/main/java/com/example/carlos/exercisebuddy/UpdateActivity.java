package com.example.carlos.exercisebuddy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class UpdateActivity extends ActionBarActivity {
    DBAdapterActivity db = new DBAdapterActivity(this);
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
            Log.i("Carlos", updateActivity.getActivity() + " " + updateActivity.getDayOfWeek() + updateActivity.getStartHour() + updateActivity.getStartMinute());
        } else {
            Log.i("DW", "no good");
        }


        int activityName = 0;

        Spinner dropdown = (Spinner) findViewById(R.id.ActivityName);
        String[] items = new String[]{"Nap", "Work", "Workout/Exercise", "Class", "Eating","Study/Homework","Meeting","Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        switch (updateActivity.getActivity()) {

            case "Nap":
                activityName = 0;
                break;
            case "Work":
                activityName = 1;
                break;
            case "Workout/Exercise":
                activityName = 2;
                break;
            case "Class":
                activityName = 3;
                break;
            case "Eating":
                activityName = 4;
                break;
            case "Study/Homework":
                activityName = 5;
                break;
            case "Meeting":
                activityName = 6;
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
        //dropdownWeek.setSelection(Integer.parseInt(passedvar));


        switch (updateActivity.getDayOfWeek()) {

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


        TimePicker tpStart= (TimePicker) findViewById(R.id.timePickerStart);
        TimePicker tpEnd = (TimePicker)  findViewById(R.id.timePickerEnd);

        tpStart.setCurrentHour(updateActivity.getStartHour());
        tpStart.setCurrentMinute(updateActivity.getStartMinute());
        tpEnd.setCurrentHour(updateActivity.getEndHour());
        tpEnd.setCurrentMinute(updateActivity.getEndMinute());

        EditText tvNotes = (EditText)findViewById(R.id.Notes);
        tvNotes.setText(updateActivity.getNotes());
/*
        EditText startText = (EditText) findViewById(R.id.startTime);
        //startText.setText(updateActivity.getStart());
        boolean activityAMStart = false;
        Spinner dropdownAM = (Spinner) findViewById(R.id.amOrpm);
        String[] itemsTime = new String[]{"AM", "PM"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsTime);
        dropdownAM.setAdapter(adapter3);

        switch(updateActivity.getStartAMorPM()){
        case "AM":
            activityAMStart = true;
            break;
        case "PM":
            activityAMStart = false;
            break;
        default:
            activityAMStart = false;
        }

        //dropdownAM.setSelection(activityAM);


        //EditText endText = (EditText)findViewById(R.id.endTime);
        //endText.setText(updateActivity.getEnd());
        boolean activityAMEnd = true;
        Spinner dropdownAM2 = (Spinner)findViewById(R.id.amOrpm2);
        String[] itemsTime2 = new String[]{"AM","PM"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, itemsTime2);
        dropdownAM2.setAdapter(adapter4);



        switch(updateActivity.getEndAMorPM()){
            case "AM":
                activityAMEnd = true;
                break;
            case "PM":
                activityAMEnd = false;
                break;
            default:
                activityAMEnd = false;
        }

        //dropdownAM2.setSelection(activityPM);*/
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
        TimePicker tpStart;
        TimePicker tpEnd;
        TextView tvNotes;
        boolean startAM;
        boolean endAM;
        String activityID = getIntent().getStringExtra(MainActivity.ACTIVITY_ID);

        intent = new Intent(this,MainActivity.class);
        //editText = (EditText) findViewById(R.id.startTime);
        //editText2 = (EditText) findViewById(R.id.endTime);
        mySpinner = (Spinner) findViewById(R.id.ActivityName);
        mySpinner2 = (Spinner) findViewById(R.id.dayOfWeek);
        tpStart = (TimePicker) findViewById(R.id.timePickerStart);
        tpEnd = (TimePicker) findViewById(R.id.timePickerEnd);
        tvNotes = (EditText)findViewById(R.id.Notes);



        //mySpinner3 = (Spinner) findViewById(R.id.amOrpm);
        //mySpinner4 = (Spinner) findViewById(R.id.amOrpm2);

        db.open();
        db.updateRecord(Long.valueOf(activityID),mySpinner.getSelectedItem().toString(),mySpinner2.getSelectedItem().toString(),tpStart.getCurrentHour(),tpStart.getCurrentMinute(),tpEnd.getCurrentHour(),tpEnd.getCurrentMinute(),false,false,tvNotes.getText().toString());
        // db.updateRecord(Long.valueOf(activityID), mySpinner.getSelectedItem().toString(), mySpinner2.getSelectedItem().toString(), editText.getText().toString(), editText2.getText().toString(),mySpinner3.getSelectedItem().toString(),mySpinner4.getSelectedItem().toString());
        db.close();
        startActivity(intent);
        Toast.makeText(this, "This activity has been updated", Toast.LENGTH_SHORT).show();

    }

    //Deletes the selected item
    public void DeleteActivity(View view){
        Intent intent;
        intent = new Intent(getBaseContext(),MainActivity.class);
        db.open();
        db.deleteContact(Long.valueOf(getIntent().getStringExtra(MainActivity.ACTIVITY_ID)));
        db.close();
        startActivity(intent);
        Toast.makeText(this,"This activity has been deleted",Toast.LENGTH_SHORT).show();

    }



}
