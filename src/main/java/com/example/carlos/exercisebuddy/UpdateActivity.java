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

        Spinner dropdown = (Spinner)findViewById(R.id.ActivityName);
        String[] items = new String[]{"Sleep", "Work", "Workout","Class","Eating"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setSelection(3);

        Spinner dropdownWeek = (Spinner)findViewById(R.id.dayOfWeek);
        String[] itemsWeek = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, itemsWeek);
        dropdownWeek.setAdapter(adapter2);
        String passedvar = getIntent().getStringExtra(MainActivity.Extra_Link);
        dropdownWeek.setSelection(Integer.parseInt(passedvar));

        Spinner dropdownAM = (Spinner)findViewById(R.id.spinner3);
        String[] itemsTime = new String[]{"AM","PM"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, itemsTime);
        dropdownAM.setAdapter(adapter3);

        Spinner dropdownAM2 = (Spinner)findViewById(R.id.spinner4);
        //String[] itemsTime2 = new String[]{"AM","PM"};
        //ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, itemsTime);
        dropdownAM2.setAdapter(adapter3);

        EditText startText = (EditText)findViewById((R.id.startTime));
        startText.setText(passedvar);
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
        String message;
        int matchingID = 0;
        //Cursor c = db.getAllRecords();




        intent = new Intent(this,MainActivity.class);
        editText = (EditText) findViewById(R.id.startTime);
        editText2 = (EditText) findViewById(R.id.endTime);
        mySpinner = (Spinner) findViewById(R.id.ActivityName);
        mySpinner2 = (Spinner) findViewById(R.id.dayOfWeek);
        String Text = mySpinner.getSelectedItem().toString();
        db.open();
        db.updateRecord(matchingID, mySpinner.getSelectedItem().toString(), mySpinner2.getSelectedItem().toString(), editText.getText().toString(), editText2.getText().toString());
        db.close();
        editText.setText("");
        editText2.setText("");
        startActivity(intent);
        Toast.makeText(this, "Activity has been Updated", Toast.LENGTH_LONG).show();

    }
    public void ViewActivity(View view){
        Intent intent;
        intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);

    }



}
