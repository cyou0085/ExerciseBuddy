package com.example.carlos.exercisebuddy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.carlos.exercisebuddy.ActivityListAdapter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dwalter on 3/12/15.
 */
public class WeekView extends View implements View.OnClickListener {
    public final static String ACTIVITY_ID = "com.example.carlos.exercisebuddy.MainActivity.tvID";

DBAdapterActivity db = new DBAdapterActivity(getContext());

    private Paint mPaint;
    private Paint mActivityPaint;
    private Paint mActivityPaintBlue;
<<<<<<< Updated upstream
    private Paint mActivityPaintBluelight;
=======
    private Paint mActivityPaintAqua;
>>>>>>> Stashed changes
    private Paint mActivityPaintYellow;
    private Paint mActivityPaintYellowlight;
    private Paint mActivityPaintGreen;
    private Paint mActivityPaintOrange;
<<<<<<< Updated upstream
    private Paint mWeekPaint;

    Context context = getContext();
=======
    private Paint mActivityPaintPurple;
    private Paint mActivityPaintWhite;
    private Paint mActivityPaintBlack;
    private Paint mWeekPaint;

    Context context = getContext();
    Intent notifyIntent = new Intent();
    Calendar calendarS = Calendar.getInstance();
    Calendar calendarM = Calendar.getInstance();
    Calendar calendarT = Calendar.getInstance();
    Calendar calendarW = Calendar.getInstance();
    Calendar calendarR = Calendar.getInstance();
    Calendar calendarF = Calendar.getInstance();
    Calendar calendarSa = Calendar.getInstance();
    Calendar calendarNOW = Calendar.getInstance();

>>>>>>> Stashed changes
    //Intent intent = new Intent(context,UpdateActivity.class);
    int mWidth;
    int mHeight;
    private float clickX;
    private float clickY;
<<<<<<< Updated upstream
    int weekdayStartH;
    int weekdayStartM;
    int weekdayEndH;
    int weekdayEndM;
    int weekendStartH;
    int weekendStartM;
    int weekendEndH;
    int weekendEndM;
=======
>>>>>>> Stashed changes
    RectF weekdaySleepTimes;
    ArrayList<Activity> mActivities;
    ArrayList<RectF> mActivityRegions;
    RectF mActivityRegionsMidNight;
<<<<<<< Updated upstream
    Activity weekDaySleep = new Activity();
    Activity weekEndSleep = new Activity();
    ActivityListAdapter adapter;
    boolean overnight = false;
=======
>>>>>>> Stashed changes
    RectF suggestedS;
    RectF suggestedM;
    RectF suggestedT;
    RectF suggestedW;
    RectF suggestedR;
    RectF suggestedF;
    RectF suggestedSa;
<<<<<<< Updated upstream
    Activity weekendActivity = new Activity();
=======
>>>>>>> Stashed changes
    Activity weekdayActivity;
    Activity workoutActivity;

    private TextView myText = null;

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mActivities = new ArrayList<Activity>();
        mActivityRegions = new ArrayList<RectF>();
        suggestedS = new RectF();
        suggestedM = new RectF();
        suggestedT = new RectF();
        suggestedW = new RectF();
        suggestedR = new RectF();
        suggestedF = new RectF();
        suggestedSa = new RectF();
        weekdayActivity = new Activity();
        weekdaySleepTimes = new RectF();
        mActivityRegionsMidNight = new RectF();
        workoutActivity = new Activity();


        this.setOnClickListener(this);
    }

    //creates the Paint objects that will be used whenever the onDraw method is called.
    // They are constructed here for performance reasons to prevent unnecessarily
    // creating instances of these objects every time onDraw is called.
    private void init() {
        mPaint = new Paint(0);
        mPaint.setColor(0xff101010);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3.0f);
        mActivityPaint = new Paint(0);
        mActivityPaint.setColor(0xffff0000);
        mActivityPaint.setAntiAlias(true);
        mActivityPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintYellow = new Paint(0);
        mActivityPaintYellow.setColor(0xffffff33);
        mActivityPaintYellow.setAntiAlias(true);
        mActivityPaintYellow.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintYellowlight = new Paint(0);
        mActivityPaintYellowlight.setColor(0x33ffff33);
        mActivityPaintYellowlight.setAntiAlias(true);
        mActivityPaintYellow.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintBlue = new Paint(0);
        mActivityPaintBlue.setColor(0xff0000ff);
        mActivityPaintBlue.setAntiAlias(true);
        mActivityPaintBlue.setStyle(Paint.Style.FILL_AND_STROKE);
<<<<<<< Updated upstream
        mActivityPaintBluelight = new Paint(0);
        mActivityPaintBluelight.setColor(0x330000ff);
        mActivityPaintBluelight.setAntiAlias(true);
        mActivityPaintBlue.setStyle(Paint.Style.FILL_AND_STROKE);
=======
>>>>>>> Stashed changes
        mActivityPaintGreen = new Paint(0);
        mActivityPaintGreen.setColor(0xff00ff00);
        mActivityPaintGreen.setAntiAlias(true);
        mActivityPaintGreen.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintOrange = new Paint(0);
        mActivityPaintOrange.setColor(0xffffa500);
        mActivityPaintOrange.setAntiAlias(true);
        mActivityPaintOrange.setStyle(Paint.Style.FILL_AND_STROKE);
<<<<<<< Updated upstream
=======
        mActivityPaintPurple = new Paint(0);
        mActivityPaintPurple.setColor(0xffaa33aa);
        mActivityPaintPurple.setAntiAlias(true);
        mActivityPaintPurple.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintAqua = new Paint(0);
        mActivityPaintAqua.setColor(0xffff33ff);
        mActivityPaintAqua.setAntiAlias(true);
        mActivityPaintAqua.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintWhite = new Paint(0);
        mActivityPaintWhite.setColor(0xffffffff);
        mActivityPaintWhite.setAntiAlias(true);
        mActivityPaintWhite.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintBlack = new Paint(0);
        mActivityPaintBlack.setColor(0xff000000);
        mActivityPaintBlack.setAntiAlias(true);
        mActivityPaintBlack.setStyle(Paint.Style.FILL_AND_STROKE);
>>>>>>> Stashed changes
        mWeekPaint = new Paint(0);
        mWeekPaint.setColor(0xff000000);
        mWeekPaint.setTextSize(44);


<<<<<<< Updated upstream
        //mPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
=======
>>>>>>> Stashed changes

    }



    //Store Activity Items Within the the ArrayList
    public void addNewActivity(Activity a) {
        mActivities.add(a);
        mActivityRegions.add(calculateActivityRegion(a));
    }


    private RectF calculateActivityRegion(Activity a) {
        float hourWidth = this.getWidth()/24.0f;
        RectF r = null;
        switch (a.getDayOfWeek()) {
            case "Sunday":
<<<<<<< Updated upstream
                    r = new RectF(hourWidth * a.getStartTime(), 0, hourWidth * a.getEndTime(), mHeight / 7);
                    break;
            case "Monday":
                r = new RectF(hourWidth * a.getStartTime(), mHeight / 7, hourWidth * a.getEndTime(), 2 * mHeight / 7);
                break;
            case "Tuesday":
                r = new RectF(hourWidth * a.getStartTime(), 2 * mHeight / 7, hourWidth * a.getEndTime(), 3 * mHeight / 7);
                break;
            case "Wednesday":
                r = new RectF(hourWidth * a.getStartTime(), 3 * mHeight / 7, hourWidth * a.getEndTime(), 4 * mHeight / 7);
                break;
            case "Thursday":
                r = new RectF(hourWidth * a.getStartTime(), 4 * mHeight / 7, hourWidth * a.getEndTime(), 5 * mHeight / 7);
                break;
            case "Friday":
                r = new RectF(hourWidth * a.getStartTime(), 5 * mHeight / 7, hourWidth * a.getEndTime(), 6 * mHeight / 7);
                break;
            case "Saturday":
                r = new RectF(hourWidth * a.getStartTime(), 6 * mHeight / 7, hourWidth * a.getEndTime(), 7 * mHeight / 7);
=======
                    r = new RectF(50 + hourWidth * a.getStartTime(), 0, 50 + hourWidth * a.getEndTime(), mHeight / 7);
                    break;
            case "Monday":
                r = new RectF(50 + hourWidth * a.getStartTime(), mHeight / 7, 50 + hourWidth * a.getEndTime(), 2 * mHeight / 7);
                break;
            case "Tuesday":
                r = new RectF(50 + hourWidth * a.getStartTime(), 2 * mHeight / 7, 50 + hourWidth * a.getEndTime(), 3 * mHeight / 7);
                break;
            case "Wednesday":
                r = new RectF(50 + hourWidth * a.getStartTime(), 3 * mHeight / 7, 50 + hourWidth * a.getEndTime(), 4 * mHeight / 7);
                break;
            case "Thursday":
                r = new RectF(50 + hourWidth * a.getStartTime(), 4 * mHeight / 7, 50 + hourWidth * a.getEndTime(), 5 * mHeight / 7);
                break;
            case "Friday":
                r = new RectF(50 + hourWidth * a.getStartTime(), 5 * mHeight / 7, 50 + hourWidth * a.getEndTime(), 6 * mHeight / 7);
                break;
            case "Saturday":
                r = new RectF(50 + hourWidth * a.getStartTime(), 6 * mHeight / 7, 50 + hourWidth * a.getEndTime(), 7 * mHeight / 7);
>>>>>>> Stashed changes
                break;
        }
        return r;
    }

<<<<<<< Updated upstream
    private RectF calculateActivityRegion2(Activity a) {
        float hourWidth = this.getWidth()/24.0f;
        RectF r = null;

        switch (a.getDayOfWeek()) {
            case "Sunday":
                r = new RectF(50 + hourWidth * a.getStartTime(), 0, hourWidth * a.getEndTime(), mHeight / 7);
                break;
            case "Monday":
                if (a.getStartTime() > a.getEndTime()){
                    overnight = true;
                    r = new RectF(hourWidth * a.getStartTime(), mHeight / 7, 50 + mWidth, 6 * mHeight / 7);
                }
                else
                r = new RectF(50 + hourWidth * a.getStartTime(), mHeight / 7, 50 + (hourWidth * a.getEndTime()), 6 * mHeight / 7);
                break;
            case "Tuesday":

                r = new RectF(50 + hourWidth * a.getStartTime(), mHeight / 7, hourWidth * a.getEndTime(), 6 * mHeight / 7);
                break;
            case "Wednesday":
                r = new RectF(50 + hourWidth * a.getStartTime(), mHeight / 7, hourWidth * a.getEndTime(), 6 * mHeight / 7);
                break;
            case "Thursday":
                r = new RectF(50 + hourWidth * a.getStartTime(),mHeight / 7, hourWidth * a.getEndTime(), 6 * mHeight / 7);
                break;
            case "Friday":
                r = new RectF(50 + hourWidth * a.getStartTime(), mHeight, hourWidth * a.getEndTime(), 6 * mHeight / 7);
                break;
            case "Saturday":
                r = new RectF(50 + hourWidth * a.getStartTime(), 6 * mHeight / 7, hourWidth * a.getEndTime(), 7 * mHeight / 7);
                break;
        }
        return r;
    }
=======

>>>>>>> Stashed changes
    public void addActivities(ArrayList<Activity> activities) {
        mActivities.addAll(activities);
        for (Activity a : activities) {
            mActivityRegions.add(calculateActivityRegion(a));
<<<<<<< Updated upstream
            //mActivityRegions.add(calculateActivityRegion(a).get(2));
            //mActivityRegionsMidNight.add(calculateActivityRegion(a).get(2));
        }
    }

    protected  RectF suggestWorkOutTuesday(String day,ArrayList <Activity> mActivities,ArrayList<RectF> mActivityRegions) {
=======

        }
    }

    protected  RectF suggestWorkOutTime(String day,ArrayList <Activity> mActivities,ArrayList<RectF> mActivityRegions) {
>>>>>>> Stashed changes
        ArrayList<Activity> mTuesdayActivities = new ArrayList<Activity>();
        ArrayList<RectF> mTuesdayActivitiesRegions = new ArrayList<RectF>();
        boolean freespace = false;
        RectF rect = new RectF();
        int workoutTimeH = 9;
<<<<<<< Updated upstream
        int workoutTimeM = 00;
=======
        int workoutTimeM = 30;
>>>>>>> Stashed changes
        RectF temp = new RectF();
        workoutActivity.setStartHour(workoutTimeH);
        workoutActivity.setStartMinute(workoutTimeM);


        float hourWidth = this.getWidth() / 24.0f;

        for (Activity a : mActivities) {
            if (a.getDayOfWeek().equals(day)) {
                mTuesdayActivities.add(a);
            }
        }
        for (int i = 0; i < mTuesdayActivities.size(); i++) {
            mTuesdayActivitiesRegions.add(calculateActivityRegion(mTuesdayActivities.get(i)));
        }
        Log.i("suggestedWorkout",weekdayActivity.toString());


        for(int i = 0;i < mTuesdayActivitiesRegions.size();i++) {
            for (int j = 1; j < mTuesdayActivitiesRegions.size(); j++) {
                if (mTuesdayActivitiesRegions.get(j-1).left > mTuesdayActivitiesRegions.get(j).left) {
                    temp = mTuesdayActivitiesRegions.get(j-1);
                    mTuesdayActivitiesRegions.set(j-1,mTuesdayActivitiesRegions.get(j));
<<<<<<< Updated upstream
                    //mTuesdayActivitiesRegions.get(j-1) = mTuesdayActivitiesRegions.get(j);
=======
>>>>>>> Stashed changes
                    mTuesdayActivitiesRegions.set(j,temp);
                }
            }
        }


        rect = suggestedDayHelper(day,hourWidth,workoutActivity);


        for (int count = 0; count < mTuesdayActivitiesRegions.size(); count++) {
<<<<<<< Updated upstream
            Log.i("cc","TRegion" + mTuesdayActivitiesRegions.get(count));
            Log.i("cc","RectRegion" + rect);
            while(rect.intersect(mTuesdayActivitiesRegions.get(count)) || rect.intersect(weekdaySleepTimes)){
=======
            while(rect.intersect(mTuesdayActivitiesRegions.get(count))){
>>>>>>> Stashed changes
                workoutTimeM+=5;
                if(workoutTimeM >= 60){
                    workoutTimeH+=1;
                    workoutTimeM -= 60;
                    workoutActivity.setStartHour(workoutTimeH);
                }
                workoutActivity.setStartMinute(workoutTimeM);
<<<<<<< Updated upstream
                Log.i("cc","Hour" + workoutActivity.getStartHour());
                Log.i("cc","Min" + workoutActivity.getStartMinute());
                rect.set(suggestedDayHelper(day,hourWidth,workoutActivity));

                //rect.set(hourWidth * workoutActivity.getStartTime(),2*7, (workoutActivity.getStartTime() + .75f) * hourWidth,  3* mHeight/7);

            }

        }
=======
                rect.set(suggestedDayHelper(day,hourWidth,workoutActivity));
            }
        }


        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        notifyIntent = new Intent(getContext(), MyNotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(getContext(),0,notifyIntent,0);
        switch(day){
            case "Sunday":
                    calendarS.set(Calendar.HOUR, workoutActivity.getStartHour());
                    calendarS.set(Calendar.MINUTE, workoutActivity.getStartMinute());
                    calendarS.set(Calendar.SECOND,0);
                calendarS.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

                if(calendarS.before(calendarNOW)) {

                    calendarS.add(Calendar.DAY_OF_YEAR, +7);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarS.getTimeInMillis(),pendingIntent);
                }
                else{
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendarS.getTimeInMillis(),pendingIntent);

                }
                break;
            case "Monday":

                    calendarM.set(Calendar.HOUR, workoutActivity.getStartHour());
                    calendarM.set(Calendar.MINUTE, workoutActivity.getStartMinute());
                    calendarM.set(Calendar.SECOND,0);
                    calendarM.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

                if(calendarM.before(calendarNOW)) {

                    calendarM.add(Calendar.DAY_OF_YEAR, +7);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarM.getTimeInMillis(),pendingIntent);
                }
                else{

                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarM.getTimeInMillis(),pendingIntent);

                }
                break;
            case "Tuesday":

                calendarT.set(Calendar.HOUR, workoutActivity.getStartHour());
                calendarT.set(Calendar.MINUTE, workoutActivity.getStartMinute());
                calendarT.set(Calendar.SECOND,0);
                calendarT.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

                if(calendarT.before(calendarNOW)) {

                    calendarT.add(Calendar.DAY_OF_YEAR, +7);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarT.getTimeInMillis(),pendingIntent);
                }
                else{

                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarT.getTimeInMillis(),pendingIntent);

                }
                break;
            case "Wednesday":
                calendarW.set(Calendar.HOUR, workoutActivity.getStartHour());
                calendarW.set(Calendar.MINUTE, workoutActivity.getStartMinute());
                calendarW.set(Calendar.SECOND,0);
                calendarW.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

                if(calendarW.before(calendarNOW)) {

                    calendarW.add(Calendar.DAY_OF_YEAR, +7);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarW.getTimeInMillis(),pendingIntent);
                }
                else{

                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarW.getTimeInMillis(),pendingIntent);

                }
                break;
            case "Thursday":
                calendarR.set(Calendar.HOUR, workoutActivity.getStartHour());
                calendarR.set(Calendar.MINUTE, workoutActivity.getStartMinute());
                calendarR.set(Calendar.SECOND,0);
                calendarR.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);

                if(calendarR.before(calendarNOW)) {

                    calendarR.add(Calendar.DAY_OF_YEAR, +7);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarR.getTimeInMillis(),pendingIntent);
                }
                else{

                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarR.getTimeInMillis(),pendingIntent);

                }
                break;
            case "Friday":
                calendarF.set(Calendar.HOUR, workoutActivity.getStartHour());
                calendarF.set(Calendar.MINUTE, workoutActivity.getStartMinute());
                calendarF.set(Calendar.SECOND,0);
                calendarF.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

                if(calendarF.before(calendarNOW)) {

                    calendarF.add(Calendar.DAY_OF_YEAR, +7);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarF.getTimeInMillis(),pendingIntent);
                }
                else{

                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarF.getTimeInMillis(),pendingIntent);

                }
                break;
            case "Saturday":
                calendarSa.set(Calendar.HOUR, workoutActivity.getStartHour());
                calendarSa.set(Calendar.MINUTE, workoutActivity.getStartMinute());
                calendarSa.set(Calendar.SECOND,0);
                calendarSa.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

                if(calendarSa.before(calendarNOW)) {

                    calendarSa.add(Calendar.DAY_OF_YEAR, +7);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarSa.getTimeInMillis(),pendingIntent);
                }
                else{

                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendarSa.getTimeInMillis(),pendingIntent);

                }
                break;
        }

        Log.i("CalendarTimeAdjusted", " " + calendarS.getTime());
        Log.i("CalendarTimeAdjusted", " " + calendarM.getTime());
        Log.i("CalendarTimeAdjusted", " " + calendarT.getTime());
        Log.i("CalendarTimeAdjusted", " " + calendarW.getTime());
        Log.i("CalendarTimeAdjusted", " " + calendarR.getTime());
        Log.i("CalendarTimeAdjusted", " " + calendarF.getTime());
        Log.i("CalendarTimeAdjusted", " " + calendarSa.getTime());

>>>>>>> Stashed changes
            return rect;
    }

    public RectF suggestedDayHelper(String day,float hourWidth,Activity workoutActivity){
        RectF rect = new RectF();
        switch (day){
            case "Sunday":
<<<<<<< Updated upstream
                //
                rect.set(hourWidth * workoutActivity.getStartTime(),0, (workoutActivity.getStartTime() + .75f) * hourWidth, mHeight/7 );
                break;
            case "Monday":
                rect.set(hourWidth * workoutActivity.getStartTime(), mHeight/7, (workoutActivity.getStartTime() + .75f) * hourWidth, 2*mHeight/7 );
                break;
            case "Tuesday":
                rect.set(50 + (hourWidth * workoutActivity.getStartTime()),2 * mHeight/7, 50 + (workoutActivity.getStartTime() + .75f) * hourWidth, 3 * mHeight/7 );

                break;
            case "Wednesday":
                rect.set(hourWidth * workoutActivity.getStartTime(),3 * mHeight/7, (workoutActivity.getStartTime() + .75f) * hourWidth, 4*mHeight/7 );
                break;
            case "Thursday":
                rect.set(hourWidth * workoutActivity.getStartTime(),4 * mHeight/7, (workoutActivity.getStartTime() + .75f) * hourWidth, 5*mHeight/7 );
                break;
            case "Friday":
                rect.set(hourWidth * workoutActivity.getStartTime(),5 * mHeight/7, (workoutActivity.getStartTime() + .75f) * hourWidth, 6*mHeight/7 );
                break;
            case "Saturday":
                rect.set(hourWidth * workoutActivity.getStartTime(),6 * mHeight/7, (workoutActivity.getStartTime() + .75f) * hourWidth, mHeight );
=======

                rect.set(50+hourWidth * workoutActivity.getStartTime(),0, 50+ (workoutActivity.getStartTime() + .5f) * hourWidth, mHeight/7 );
                break;
            case "Monday":
                rect.set(50+ hourWidth * workoutActivity.getStartTime(), mHeight/7, 50+ (workoutActivity.getStartTime() + .5f) * hourWidth, 2*mHeight/7 );
                break;
            case "Tuesday":
                rect.set(50 + (hourWidth * workoutActivity.getStartTime()),2 * mHeight/7, 50 + (workoutActivity.getStartTime() + .5f) * hourWidth, 3 * mHeight/7 );

                break;
            case "Wednesday":
                rect.set(50 + hourWidth * workoutActivity.getStartTime(),3 * mHeight/7, 50 + (workoutActivity.getStartTime() + .5f) * hourWidth, 4*mHeight/7 );
                break;
            case "Thursday":
                rect.set(50 + hourWidth * workoutActivity.getStartTime(),4 * mHeight/7, 50+ (workoutActivity.getStartTime() + .5f) * hourWidth, 5*mHeight/7 );
                break;
            case "Friday":
                rect.set(50 + hourWidth * workoutActivity.getStartTime(),5 * mHeight/7, 50 + (workoutActivity.getStartTime() + .5f) * hourWidth, 6*mHeight/7 );
                break;
            case "Saturday":
                rect.set(50 + hourWidth * workoutActivity.getStartTime(),6 * mHeight/7, 50 + (workoutActivity.getStartTime() + .5f) * hourWidth, mHeight );
>>>>>>> Stashed changes
                break;
        }
        return rect;
    }


<<<<<<< Updated upstream
    public void addSleep(String times){
        weekdayStartH =  Integer.parseInt(times.substring(0,2));
        weekdayStartM = Integer.parseInt(times.substring(3,5));
        weekdayEndH = Integer.parseInt(times.substring(6,8));
        weekdayEndM = Integer.parseInt(times.substring(9,11));
        weekendStartH = Integer.parseInt(times.substring(12,14));
        weekendStartM = Integer.parseInt(times.substring(15,17));
        weekendEndH = Integer.parseInt(times.substring(18,20));
        weekendEndM = Integer.parseInt(times.substring(21,23));

        weekdayActivity.setStartHour(weekdayStartH);
        weekdayActivity.setStartMinute(weekdayStartM);
        weekdayActivity.setEndHour(weekdayEndH);
        weekdayActivity.setEndMinute(weekdayEndM);
        weekdayActivity.setDayOfWeek("Monday");
        Log.i("timeless",weekdayActivity.toString());

        Log.i("WeekDay", "" + weekdayStartH + " " + weekdayStartM + " " + weekdayEndH + " " + weekdayEndM);
        Log.i("WeekEnd", "" + weekendStartH + " " + weekendStartM + " " + weekendEndH + " " + weekendEndM);
    }

    //Draw a Board that is split into 3 Columns(Morning,Afternoon,Night) and 7 Rows(WeekDays)
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float hourWidth = this.getWidth()/24.0f;


        // Draw the day dividing lines
        canvas.drawRect(new RectF(50, 0, mWidth, mHeight), mPaint);
=======
    //Draw a Board that is split into 3 Columns(Morning,Afternoon,Night) and 7 Rows(WeekDays)
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the day dividing lines
>>>>>>> Stashed changes
        canvas.drawText("S", 0, 50 + 0 * mHeight/7, mWeekPaint);
        canvas.drawText("M", 0, 50 + mHeight/7, mWeekPaint);
        canvas.drawText("T", 0, 50 + 2 * mHeight/7, mWeekPaint);
        canvas.drawText("W", 0, 50 + 3 * mHeight/7, mWeekPaint);
        canvas.drawText("Th",0, 50 + 4 * mHeight/7, mWeekPaint);
        canvas.drawText("F", 0, 50 + 5 * mHeight/7, mWeekPaint);
        canvas.drawText("S", 0, 50 + 6 * mHeight/7, mWeekPaint);

<<<<<<< Updated upstream
        for (int i = 1; i < 7; i++) {
            canvas.drawLine(45, i * mHeight / 7, mWidth, i * mHeight / 7, mPaint);
        }

        for(int i = 1; i <= 3; i++){
            canvas.drawLine(i*mWidth/3,0,i*mWidth/3,mHeight,mPaint);
        }
        //String activityTimes = intent.getExtras().getSerializable(SleepActivity.ACTIVITY_TIMES).toString();
        //Log.i("Playing"," " + activityTimes);

        //Sleep


        //canvas.drawRect(calculateActivityRegion2(weekdayActivity),mActivityPaintBluelight);
        Log.i("onDraw","" + weekdaySleepTimes);

        canvas.drawRect(weekdaySleepTimes,mActivityPaintBluelight);
        //canvas.drawRect(calculateActivityRegion2(weekdayActivity),mActivityPaintBluelight);

        //if(overnight = true)
          //  canvas.drawRect(new RectF(50, mHeight / 7, hourWidth * weekdayActivity.getEndTime(), 6 * mHeight / 7),mActivityPaintBluelight);


        //WeekDay
        //canvas.drawRect(50, mHeight / 7, mWidth / 3, 6 * mHeight / 7, mActivityPaintBluelight);

        /*Weekend
        canvas.drawRect(50,6*mHeight/7,mWidth/3,mHeight,mActivityPaintOrange);
        canvas.drawRect(50+ 1*mWidth/24,0,mWidth/3,mHeight/7,mActivityPaintOrange);*/
=======
>>>>>>> Stashed changes

        canvas.drawRect(suggestedS,mActivityPaintYellowlight);
        canvas.drawRect(suggestedM,mActivityPaintYellowlight);
        canvas.drawRect(suggestedT,mActivityPaintYellowlight);
        canvas.drawRect(suggestedW,mActivityPaintYellowlight);
        canvas.drawRect(suggestedR,mActivityPaintYellowlight);
        canvas.drawRect(suggestedF,mActivityPaintYellowlight);
        canvas.drawRect(suggestedSa,mActivityPaintYellowlight);

        //loops through all activites creating a Rectangle
        //on the corresponding day
        for (int i = 0; i < mActivities.size(); i++) {
            Activity a = mActivities.get(i);
            RectF r = mActivityRegions.get(i);
<<<<<<< Updated upstream
//            RectF r2 = mActivityRegionsMidNight.get(i);
=======
>>>>>>> Stashed changes
            Log.i("WeekView", "Drawing activity " + a);
            Log.i("WeekView", "  Corresponding " + r);
            //Creates the block on the canvas with the appropriate
            //color corresponding to the activity thats being done
            switch (a.getActivity()){
<<<<<<< Updated upstream
                case "Sleep": canvas.drawRect(r,mActivityPaintYellow);
                    //canvas.drawRect(r2,mActivityPaintYellow);
                    break;
                case "Work": canvas.drawRect(r,mActivityPaintGreen);
                    //canvas.drawRect(r2,mActivityPaintGreen);

                    break;
                case "Workout": canvas.drawRect(r,mActivityPaintBlue);
                    //canvas.drawRect(r2,mActivityPaintBlue);

                    break;
                case "Class": canvas.drawRect(r,mActivityPaint);
                    //canvas.drawRect(r2,mActivityPaint);

                    break;
                case "Eating": canvas.drawRect(r,mActivityPaintOrange);
                    //canvas.drawRect(r2,mActivityPaintOrange);

                    break;
                default:
                   canvas.drawRect(r,mActivityPaint);
                    //canvas.drawRect(r2,mActivityPaint);
=======
                case "Nap": canvas.drawRect(r,mActivityPaintYellow);
                    break;
                case "Work": canvas.drawRect(r,mActivityPaintGreen);

                    break;
                case "Workout/Exercise": canvas.drawRect(r,mActivityPaintBlue);

                    break;
                case "Class": canvas.drawRect(r,mActivityPaint);

                    break;
                case "Eating": canvas.drawRect(r,mActivityPaintOrange);

                    break;
                case "Study/Homework": canvas.drawRect(r,mActivityPaintPurple);

                break;
                case "Meeting": canvas.drawRect(r,mActivityPaintAqua);

                    break;

                case "Others": canvas.drawRect(r,mActivityPaintWhite);

                    break;
                default:
                   canvas.drawRect(r,mActivityPaintBlack);
>>>>>>> Stashed changes

            }

        }

<<<<<<< Updated upstream

    }

    /*protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        // When the screeen size changes (new orientation, etc.), recalcualte all
        // the regions corresponding to the activities.
        mActivityRegions = new ArrayList<RectF>();
        for (Activity a: mActivities) {
            mActivityRegions.add(calculateActivityRegion(a));
        }

    }*/
=======
    }


>>>>>>> Stashed changes
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        // When the screeen size changes (new orientation, etc.), recalcualte all
        // the regions corresponding to the activities.
        mActivityRegions = new ArrayList<RectF>();
        mActivityRegionsMidNight = new RectF();

        for (Activity a: mActivities) {
            mActivityRegions.add(calculateActivityRegion(a));
<<<<<<< Updated upstream
            //mActivityRegionsMidNight.add(calculateActivityRegion(a).get(1));
        }
        suggestedS = suggestWorkOutTuesday("Sunday",mActivities,mActivityRegions);
        suggestedM = suggestWorkOutTuesday("Monday",mActivities,mActivityRegions);
        suggestedT = suggestWorkOutTuesday("Tuesday",mActivities,mActivityRegions);
        suggestedW = suggestWorkOutTuesday("Wednesday",mActivities,mActivityRegions);
        suggestedR = suggestWorkOutTuesday("Thursday",mActivities,mActivityRegions);
        suggestedF = suggestWorkOutTuesday("Friday",mActivities,mActivityRegions);
        suggestedSa = suggestWorkOutTuesday("Saturday",mActivities,mActivityRegions);
        //weekdaySleepTimes = calculateActivityRegion2(weekdayActivity);
=======
        }
        suggestedS = suggestWorkOutTime("Sunday",mActivities,mActivityRegions);
        suggestedM = suggestWorkOutTime("Monday",mActivities,mActivityRegions);
        suggestedT = suggestWorkOutTime("Tuesday",mActivities,mActivityRegions);
        suggestedW = suggestWorkOutTime("Wednesday",mActivities,mActivityRegions);
        suggestedR = suggestWorkOutTime("Thursday",mActivities,mActivityRegions);
        suggestedF = suggestWorkOutTime("Friday",mActivities,mActivityRegions);
        suggestedSa = suggestWorkOutTime("Saturday",mActivities,mActivityRegions);
>>>>>>> Stashed changes

    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.i("WeekView", "onTouchEvent called");
        int action = event.getAction();
<<<<<<< Updated upstream
        clickX = event.getX();
        clickY = event.getY();
        performClick();
        return true;
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, UpdateActivity.class);
        Intent notifyIntent = new Intent();
=======
        if (action == MotionEvent.ACTION_UP) {
            clickX = event.getX();
            clickY = event.getY();
            performClick();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, UpdateActivity.class);

>>>>>>> Stashed changes
        float hourWidth = this.getWidth()/24.0f;

        for (int i = 0; i < mActivityRegions.size(); i++) {
            if (mActivityRegions.get(i).contains(clickX, clickY)) {
                String activityId = Long.toString(mActivities.get(i).getID());

                Log.i("onClick", "User clicked on Activity " + mActivities.get(i));
                intent.putExtra(ACTIVITY_ID, activityId);
                context.startActivity(intent);
                return;
            }
<<<<<<< Updated upstream
            if (suggestWorkOutTuesday("Sunday",mActivities,mActivityRegions).contains(clickX, clickY) ||
                    suggestWorkOutTuesday("Monday",mActivities,mActivityRegions).contains(clickX, clickY) ||
                    suggestWorkOutTuesday("Tuesday",mActivities,mActivityRegions).contains(clickX, clickY)
                    ||suggestWorkOutTuesday("Wednesday",mActivities,mActivityRegions).contains(clickX, clickY) ||
                    suggestWorkOutTuesday("Thursday",mActivities,mActivityRegions).contains(clickX, clickY) ||
                    suggestWorkOutTuesday("Friday",mActivities,mActivityRegions).contains(clickX, clickY) ||
                    suggestWorkOutTuesday("Saturday",mActivities,mActivityRegions).contains(clickX, clickY)) {
                RectF notifyTime = new RectF();
                Calendar calendar = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                Log.i("TestRun"," " + calendar.getTime());

                String clickedDay = "";
                float startTimeH;
                if(clickY > 0 && clickY < mHeight/7)
                    clickedDay = "Sunday";

                if(clickY > mHeight/7 && clickY < 2 * mHeight/7)
                    clickedDay = "Monday";
                if(clickY > 2 * mHeight/7 && clickY < 3 * mHeight/7)
                    clickedDay = "Tuesday";
                if(clickY > 3 * mHeight/7 && clickY < 4 * mHeight/7)
                    clickedDay = "Wednesday";
                if(clickY > 4 * mHeight/7 && clickY < 5 * mHeight/7)
                    clickedDay = "Thursday";
                if(clickY > 5 * mHeight/7 && clickY < 6 * mHeight/7)
                    clickedDay = "Friday";
                if(clickY > 6 * mHeight/7 && clickY < mHeight/7)
                    clickedDay = "Saturday";
                Log.i("onClick", "User clicked on  " + calendar.getTimeInMillis());

                calendar.set(Calendar.HOUR,workoutActivity.getStartHour());
                calendar.set(Calendar.MINUTE, workoutActivity.getStartMinute());
                Log.i("TestRun", " " + calendar.getTime());

                AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                Log.i("onClick", "User clicked on suggested activity " + calendar.getTimeInMillis());
                notifyIntent.setClass(context,MyNotificationService.class);
                PendingIntent pendingIntent = PendingIntent.getService(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                //context.startService(notifyIntent);
            }


        }
        Log.i("onClick", "Click detected but no activity clicked on");
    }


/*
    public void FindRecord()
    {
        WeekView lv = (WeekView)findViewById(R.id.view2);

        adapter = new ActivityListAdapter(mActivities, getContext());
        lv.setAdapter(adapter);
        lv.setClickable(true);
        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(),UpdateActivity.class);
                String activityId = Long.toString(activityList.get(position).getID());
                Log.i("DW", "Adding " + activityId + " to intent");
                i.putExtra(ACTIVITY_ID, activityId);
                startActivity(i);
            }
        });
    }*/
}
=======
        }

    }

}
>>>>>>> Stashed changes
