package com.example.carlos.exercisebuddy;

import android.util.Log;

import java.util.Enumeration;

/**
 * Created by carlosyoung on 2/12/15.
 */
public class Activity {

    //Private Variable Members for Activity class
    private enum  Day{
        Sunday,Monday,Tuesday,Wednesday,Thursday,
        Saturday;
    }
    private Long ID;
    private Day dayOfWeek;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private boolean startAMorPM;
    private boolean endAMorPM;
    public static final int AM = 0;
    public static final int PM = 1;

    private String activity;
    private String day;
    private String start;
    private String end;
    private String am1;
    private String am2;

    // alternative representation:
    // Long ID
    // String dayOfWeek -> Enumeration instead??
    // int startHour   // Military Time
    // int startMinute // Military Time
    // int endHour     // Military Time
    // int endMinute   // Military Time
    // public static final int AM = 0;  // enumeration
    // public static final int PM = 1;

    // public Activity (Long ID, String dayOfWeek, it startHour, int startMinute, startAMorPM, int endHour, int endMinute, int endAMorPM) {
       /// Creates the activity in military time converting from a time that is specified in AM/PM
    // }


    //Default Constructor sets everything to default values
   public Activity(){
        this.ID = 0L;
        this.activity = "Class";
        this.day = "Sunday";
        this.start = "00:00:00";
        this.end = "00:00:00";
        this.am1 = "AM";
        this.am2 = "AM";


    }
    public Activity(Long ID, String activity,String dayofWeek,String start,String end ,String startAMorPM,String endAMorPM){
        this.ID = ID;
        this.activity = activity;
        this.day = dayofWeek;
        this.start = start;
        this.end = end;
        this.am1 = startAMorPM;
        this.am2 = endAMorPM;

    }/*

    public Activity(){
        this.ID = 0L;
        this.activity = "Sleep";
        this.dayOfWeek = Day.Sunday;
        this.startHour = 12;
        this.startMinute = 99;
        this.endHour = 12;
        this.endMinute = 99;
    }
    //Assigns values to a new Activity Item
    public Activity(Long ID, String activity,Day dayofWeek,int startH, int startM,int endH,int endM,boolean startAMorPM,boolean endAMorPM) {
        this.ID = ID;
        this.activity = activity;
        this.dayOfWeek = dayofWeek;
        this.startHour = startH;
        this.startMinute = startM;
        this.endHour = endH;
        this.endMinute = endM;
        this.startAMorPM = startAMorPM;
        this.endAMorPM = endAMorPM;

    }*/

    //accessor
    public Long getID() { return ID; }

    public String getActivity() {
        return activity;
    }

    public String getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    // Number of hours from midnight.
    public float getStartTime() {
        String strHour = start.substring(0, start.indexOf(":"));
        int hour = Integer.parseInt(strHour);
        if (this.am1.equals("PM")) {
            hour += 12;
        }
        String strMinute = start.substring(start.indexOf(":")+1, start.indexOf(":")+3);
        Log.i("getStartTime", start + " Minute portion " + strMinute);
        int minute = Integer.parseInt(strMinute);
        return hour + minute/60.0f;
    }

    public float getEndTime() {
        String strHour = end.substring(0, end.indexOf(":"));
        int hour = Integer.parseInt(strHour);
        if (this.am2.equals("PM")) {
            hour += 12;
        }
        String strMinute = end.substring(end.indexOf(":")+1, end.lastIndexOf(":"));
        int minute = Integer.parseInt(strMinute);
        return hour + minute/60.0f;
    }

    public String getEnd() {
        return end;
    }

    public String getStartAM() { return am1;}

    public String getEndAM() { return am2;}

    //Mutators
    public void setId(Long id) { this.ID = id; }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setAm1(String am1) { this.am1 = am1; }

    public void setAm2(String am2) { this.am2 = am2; }

    public String toString() {
        return ID + ": " + activity + " on " + day + " from " + start + am1 + "(" + this.getStartTime() + ") until " + end + am2 + "(" + this.getEndTime() + ")";
    }

}
