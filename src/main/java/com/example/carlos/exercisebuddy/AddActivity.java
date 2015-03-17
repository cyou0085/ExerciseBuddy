package com.example.carlos.exercisebuddy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddActivity extends ActionBarActivity {
DBAdapterActivity db = new DBAdapterActivity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //Creates Spinner for Activity Name,Week Day,AM/PM for Start
        //and a AM/PM for Stop time(dropdownAM2)
        Spinner dropdown = (Spinner)findViewById(R.id.ActivityName);
        String[] items = new String[]{"Sleep", "Work", "Workout","Class","Eating"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdownWeek = (Spinner)findViewById(R.id.dayOfWeek);
        String[] itemsWeek = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, itemsWeek);
        dropdownWeek.setAdapter(adapter2);

        Spinner dropdownAM = (Spinner)findViewById(R.id.amOrpm);
        String[] itemsTime = new String[]{"AM","PM"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, itemsTime);
        dropdownAM.setAdapter(adapter3);

        Spinner dropdownAM2 = (Spinner)findViewById(R.id.amOrpm2);
        String[] itemsTime2 = new String[]{"AM","PM"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, itemsTime2);
        dropdownAM2.setAdapter(adapter4);

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

    public void AddActivity(View view) {
        //Create Local Variables to store new db values
        Intent intent;
        EditText editText;
        EditText editText2;
        Spinner spinnerActivity;
        Spinner spinnerWeekDay;
        Spinner spinnerAMorPMStart;
        Spinner spinnerAMorPMEnd;

        //assign them to the corresponding xml element
        intent = new Intent(this,MainActivity.class);
        editText = (EditText) findViewById(R.id.startTime);
        editText2 = (EditText) findViewById(R.id.endTime);
        spinnerActivity = (Spinner) findViewById(R.id.ActivityName);
        spinnerWeekDay = (Spinner) findViewById(R.id.dayOfWeek);
        spinnerAMorPMStart = (Spinner) findViewById(R.id.amOrpm);
        spinnerAMorPMEnd = (Spinner) findViewById(R.id.amOrpm2);

        //Opens db and inserts values into the table
        db.open();
        db.insertRecord(spinnerActivity.getSelectedItem().toString(), spinnerWeekDay.getSelectedItem().toString(), editText.getText().toString(), editText2.getText().toString(), spinnerAMorPMStart.getSelectedItem().toString(), spinnerAMorPMEnd.getSelectedItem().toString());
        db.close();
        //Once inserted set elements back to blank and create a TOAST
        editText.setText("");
        editText2.setText("");
        startActivity(intent);
        Toast.makeText(this, "Your activity has been added", Toast.LENGTH_SHORT).show();

    }

    public void ViewActivity(View view){
        Intent intent;
        intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);

    }
}
