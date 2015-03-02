package com.example.carlos.exercisebuddy;

/**
 * Created by carlosyoung on 2/12/15.
 */
public class Activity {

    private Long ID;
    private String activity;
    private String day;
    private String start;
    private String end;
    private String am1;
    private String am2;

    public Activity(){
        this.ID = 0L;
        this.activity = "Class";
        this.day = "Sunday";
        this.start = "00:00:00";
        this.end = "00:00:00";
        this.am1 = "AM";
        this.am2 = "AM";
    }

    public Activity(Long ID, String activity, String day, String start, String end, String am1, String am2){
        this.ID = ID;
        this.activity = activity;
        this.day = day;
        this.start = start;
        this.end = end;
        this.am1 = am1;
        this.am2 = am2;

    }

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

    public String getEnd() {
        return end;
    }

    public String getStartAM() { return am1;}

    public String getEndAM() { return am2;}

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


}
