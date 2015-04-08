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
    private Paint mActivityPaintBluelight;
    private Paint mActivityPaintYellow;
    private Paint mActivityPaintYellowlight;
    private Paint mActivityPaintGreen;
    private Paint mActivityPaintOrange;
    private Paint mWeekPaint;

    Context context = getContext();
    //Intent intent = new Intent(context,UpdateActivity.class);
    int mWidth;
    int mHeight;
    private float clickX;
    private float clickY;
    int weekdayStartH;
    int weekdayStartM;
    int weekdayEndH;
    int weekdayEndM;
    int weekendStartH;
    int weekendStartM;
    int weekendEndH;
    int weekendEndM;
    RectF weekdaySleepTimes;
    ArrayList<Activity> mActivities;
    ArrayList<RectF> mActivityRegions;
    RectF mActivityRegionsMidNight;
    Activity weekDaySleep = new Activity();
    Activity weekEndSleep = new Activity();
    ActivityListAdapter adapter;
    boolean overnight = false;
    RectF suggestedS;
    RectF suggestedM;
    RectF suggestedT;
    RectF suggestedW;
    RectF suggestedR;
    RectF suggestedF;
    RectF suggestedSa;
    Activity weekendActivity = new Activity();
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
        mActivityPaintBluelight = new Paint(0);
        mActivityPaintBluelight.setColor(0x330000ff);
        mActivityPaintBluelight.setAntiAlias(true);
        mActivityPaintBlue.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintGreen = new Paint(0);
        mActivityPaintGreen.setColor(0xff00ff00);
        mActivityPaintGreen.setAntiAlias(true);
        mActivityPaintGreen.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintOrange = new Paint(0);
        mActivityPaintOrange.setColor(0xffffa500);
        mActivityPaintOrange.setAntiAlias(true);
        mActivityPaintOrange.setStyle(Paint.Style.FILL_AND_STROKE);
        mWeekPaint = new Paint(0);
        mWeekPaint.setColor(0xff000000);
        mWeekPaint.setTextSize(44);


        //mPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));

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
                break;
        }
        return r;
    }

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
    public void addActivities(ArrayList<Activity> activities) {
        mActivities.addAll(activities);
        for (Activity a : activities) {
            mActivityRegions.add(calculateActivityRegion(a));
            //mActivityRegions.add(calculateActivityRegion(a).get(2));
            //mActivityRegionsMidNight.add(calculateActivityRegion(a).get(2));
        }
    }

    protected  RectF suggestWorkOutTuesday(String day,ArrayList <Activity> mActivities,ArrayList<RectF> mActivityRegions) {
        ArrayList<Activity> mTuesdayActivities = new ArrayList<Activity>();
        ArrayList<RectF> mTuesdayActivitiesRegions = new ArrayList<RectF>();
        boolean freespace = false;
        RectF rect = new RectF();
        int workoutTimeH = 9;
        int workoutTimeM = 00;
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
                    //mTuesdayActivitiesRegions.get(j-1) = mTuesdayActivitiesRegions.get(j);
                    mTuesdayActivitiesRegions.set(j,temp);
                }
            }
        }


        rect = suggestedDayHelper(day,hourWidth,workoutActivity);


        for (int count = 0; count < mTuesdayActivitiesRegions.size(); count++) {
            Log.i("cc","TRegion" + mTuesdayActivitiesRegions.get(count));
            Log.i("cc","RectRegion" + rect);
            while(rect.intersect(mTuesdayActivitiesRegions.get(count)) || rect.intersect(weekdaySleepTimes)){
                workoutTimeM+=5;
                if(workoutTimeM >= 60){
                    workoutTimeH+=1;
                    workoutTimeM -= 60;
                    workoutActivity.setStartHour(workoutTimeH);
                }
                workoutActivity.setStartMinute(workoutTimeM);
                Log.i("cc","Hour" + workoutActivity.getStartHour());
                Log.i("cc","Min" + workoutActivity.getStartMinute());
                rect.set(suggestedDayHelper(day,hourWidth,workoutActivity));

                //rect.set(hourWidth * workoutActivity.getStartTime(),2*7, (workoutActivity.getStartTime() + .75f) * hourWidth,  3* mHeight/7);

            }

        }
            return rect;
    }

    public RectF suggestedDayHelper(String day,float hourWidth,Activity workoutActivity){
        RectF rect = new RectF();
        switch (day){
            case "Sunday":
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
                break;
        }
        return rect;
    }


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
        canvas.drawText("S", 0, 50 + 0 * mHeight/7, mWeekPaint);
        canvas.drawText("M", 0, 50 + mHeight/7, mWeekPaint);
        canvas.drawText("T", 0, 50 + 2 * mHeight/7, mWeekPaint);
        canvas.drawText("W", 0, 50 + 3 * mHeight/7, mWeekPaint);
        canvas.drawText("Th",0, 50 + 4 * mHeight/7, mWeekPaint);
        canvas.drawText("F", 0, 50 + 5 * mHeight/7, mWeekPaint);
        canvas.drawText("S", 0, 50 + 6 * mHeight/7, mWeekPaint);

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
//            RectF r2 = mActivityRegionsMidNight.get(i);
            Log.i("WeekView", "Drawing activity " + a);
            Log.i("WeekView", "  Corresponding " + r);
            //Creates the block on the canvas with the appropriate
            //color corresponding to the activity thats being done
            switch (a.getActivity()){
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

            }

        }


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
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        // When the screeen size changes (new orientation, etc.), recalcualte all
        // the regions corresponding to the activities.
        mActivityRegions = new ArrayList<RectF>();
        mActivityRegionsMidNight = new RectF();

        for (Activity a: mActivities) {
            mActivityRegions.add(calculateActivityRegion(a));
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

    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.i("WeekView", "onTouchEvent called");
        int action = event.getAction();
        clickX = event.getX();
        clickY = event.getY();
        performClick();
        return true;
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, UpdateActivity.class);
        Intent notifyIntent = new Intent();
        float hourWidth = this.getWidth()/24.0f;

        for (int i = 0; i < mActivityRegions.size(); i++) {
            if (mActivityRegions.get(i).contains(clickX, clickY)) {
                String activityId = Long.toString(mActivities.get(i).getID());

                Log.i("onClick", "User clicked on Activity " + mActivities.get(i));
                intent.putExtra(ACTIVITY_ID, activityId);
                context.startActivity(intent);
                return;
            }
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
