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
DBAdapterSleep db = new DBAdapterSleep(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

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
        //Checks id to determine which button was clicked
        Intent intent;
        EditText editText;
        EditText editText2;
        Spinner mySpinner;
        Spinner mySpinner2;
        Spinner mySpinner3;
        Spinner mySpinner4;
        String message;
        //Cursor c = db.getAllRecords();

        intent = new Intent(this,AddActivity.class);
        editText = (EditText) findViewById(R.id.startTime);
        editText2 = (EditText) findViewById(R.id.endTime);
        mySpinner = (Spinner) findViewById(R.id.ActivityName);
        mySpinner2 = (Spinner) findViewById(R.id.dayOfWeek);
        mySpinner3 = (Spinner) findViewById(R.id.amOrpm);
        mySpinner4 = (Spinner) findViewById(R.id.amOrpm2);
        //String Text = mySpinner.getSelectedItem().toString();
        db.open();
        db.insertRecord(mySpinner.getSelectedItem().toString(),mySpinner2.getSelectedItem().toString(), editText.getText().toString(), editText2.getText().toString(),mySpinner3.getSelectedItem().toString(),mySpinner4.getSelectedItem().toString());
        db.close();
        editText.setText("");
        editText2.setText("");
        Toast.makeText(this, "Activity has been Added", Toast.LENGTH_LONG).show();
        startActivity(intent);

    }

    public void ViewActivity(View view){
        Intent intent;
        intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);

    }
}
