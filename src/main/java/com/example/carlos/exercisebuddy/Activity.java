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
        Friday,Saturday;
    }
    private Long ID;
    private Day dayOfWeek;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private String activity;
    private String notes;
    private boolean startAM;
    private boolean endAM;
    public static final boolean AM = true;
    public static final boolean PM = false;

    private String day;
    private String start;
    private String end;
    private String am1;
    private String am2;

<<<<<<< Updated upstream
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
   /*public Activity(){
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

    }*/
=======
>>>>>>> Stashed changes

    public Activity(){
        this.ID = 0L;
        this.activity = "Sleep";
        this.dayOfWeek = Day.Sunday;
        this.startHour = 00;
        this.startMinute = 00;
        this.endHour = 00;
        this.endMinute = 50;
        this.startAM = true;
        this.endAM = true;
        this.notes = "";
    }
    //Assigns values to a new Activity Item
    public Activity(Long ID, String activity,Day dayofWeek,int startH, int startM,int endH,int endM,boolean startAM,boolean endAM,String notes) {
        this.ID = ID;
        this.activity = activity;
        this.dayOfWeek = dayofWeek;
        this.startHour = startH;
        this.startMinute = startM;
        this.endHour = endH;
        this.endMinute = endM;
        this.startAM = startAM;
        this.endAM = endAM;
        this.notes = notes;
    }

    //accessor
    public Long getID() { return ID; }

    public String getActivity() {
        return activity;
    }

<<<<<<< Updated upstream
    //public String getDay() {
      //  return day;
    //}
=======
>>>>>>> Stashed changes

    public String getDayOfWeek(){ return dayOfWeek.name(); }

    public int getStartHour(){
        return startHour;
    }

    public int getStartMinute(){
        return startMinute;
    }

    public int getEndHour(){
        return endHour;
    }

    public int getEndMinute(){
        return endMinute;
    }

    public String getStartAMorPM(){
        if(startAM == AM)
            return "AM";
        else if(startAM == PM)
            return "PM";
        return "QM";
    }

    public String getEndAMorPM(){
        if(endAM == AM)
            return "AM";
        else if (endAM == PM)
            return "PM";
        return "QM";
    }

<<<<<<< Updated upstream
    //public String getStart() {
      //  return start;
    //}
=======
>>>>>>> Stashed changes

    // Number of hours from midnight.
    public float getStartTime() {

        int tempStartHour = startHour;
<<<<<<< Updated upstream
        /*String strHour = start.substring(0, start.indexOf(":"));
        int hour = Integer.parseInt(strHour);
        if (this.am1.equals("PM")) {
            hour += 12;
        }*/
       // if(this.getStartAMorPM().equals("PM")){

         //   tempStartHour += 12;
        //}
       // String strMinute = start.substring(start.indexOf(":")+1, start.indexOf(":")+3);
        Log.i("getStartTime", tempStartHour + " Minute portion " + startMinute);
        //int minute = Integer.parseInt(strMinute);
        //return hour + minute/60.0f;
=======

>>>>>>> Stashed changes
        return tempStartHour+ startMinute/60.0f;

    }

    public float getEndTime() {
        int tempEndHour = endHour;
<<<<<<< Updated upstream
        //String strHour = end.substring(0, end.indexOf(":"));
        //int hour = Integer.parseInt(strHour);
     //   if (this.am2.equals("PM")) {
       //     hour += 12;
        //}

       // if(this.getEndAMorPM().equals("PM")){
         //   tempEndHour +=12;
        //}
        //String strMinute = end.substring(end.indexOf(":")+1, end.lastIndexOf(":"));
        //int minute = Integer.parseInt(strMinute);
        return tempEndHour + endMinute/60.0f;
    }
/*
    public String getEnd() {
        return end;
    }

    public String getStartAM() { return am1;}

    public String getEndAM() { return am2;}*/
=======

        return tempEndHour + endMinute/60.0f;
    }

>>>>>>> Stashed changes

    public String getNotes (){
        return notes;
    }

    //Mutators
    public void setId(Long id) { this.ID = id; }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDayOfWeek(String dayOfWeek){
        switch (dayOfWeek){
            case "Sunday": this.dayOfWeek = Day.Sunday;
                break;
            case "Monday": this.dayOfWeek = Day.Monday;
                break;
            case "Tuesday": this.dayOfWeek = Day.Tuesday;
                break;
            case "Wednesday": this.dayOfWeek = Day.Wednesday;
                break;
            case "Thursday": this.dayOfWeek = Day.Thursday;
                break;
            case "Friday": this.dayOfWeek = Day.Friday;
                break;
            case "Saturday": this.dayOfWeek = Day.Saturday;
                break;
            default: this.dayOfWeek = Day.Sunday;

        }
    }

    public void setStartHour(int startHour){
        this.startHour = startHour;
    }

    public void setStartMinute(int startMinute){
        this.startMinute = startMinute;
    }

    public void setEndHour(int endHour){
        this.endHour = endHour;
    }

    public void setEndMinute(int endMinute){
        this.endMinute = endMinute;
    }

    public void setStartAMorPM(boolean startAM){
        this.startAM = startAM;
    }

    public void setEndAMorPM(boolean endAM){
        this.endAM = endAM;
    }

    public void setNotes(String notes) {this.notes = notes;}

<<<<<<< Updated upstream
/*
    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setAm1(String am1) { this.am1 = am1; }

    public void setAm2(String am2) { this.am2 = am2; }
*/
=======

>>>>>>> Stashed changes
    public String toString() {
        return ID + ": " + activity + " on " + dayOfWeek + " from " + startHour + ":" + String.format("%02d",startMinute) + getStartAMorPM() + "(" + this.getStartTime() + ") until " + endHour + ":" + endMinute + getEndAMorPM() + "(" + this.getEndTime() + ")" + " P.S " + notes;
    }

}
