package com.example.carlos.exercisebuddy;

/**
 * Created by carlosyoung on 2/12/15.
 */
public class Activities {

    private String ID;
    private String activity;
    private String day;
    private String start;
    private String end;

    public Activities(String ID,String activity, String day, String start, String end){
        this.ID = ID;
        this.activity = activity;
        this.day = day;
        this.start = start;
        this.end = end;

    }

    public String getID() { return ID; }

    public String getActivity() {
        return activity;
    }

    public String getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    //public void setId(String id) { this.id = id; }

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
}
