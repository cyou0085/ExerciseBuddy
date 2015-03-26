package com.example.carlos.exercisebuddy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
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

/**
 * Created by dwalter on 3/12/15.
 */
public class WeekView extends View implements View.OnClickListener {

DBAdapterActivity db = new DBAdapterActivity(getContext());

    private Paint mPaint;
    private Paint mActivityPaint;
    private Paint mActivityPaintBlue;
    private Paint mActivityPaintYellow;
    private Paint mActivityPaintGreen;
    private Paint mActivityPaintOrange;
    int mWidth;
    int mHeight;
    private float clickX;
    private float clickY;
    ArrayList<Activity> mActivities;
    ArrayList<RectF> mActivityRegions;
    ActivityListAdapter adapter;

    private TextView myText = null;

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mActivities = new ArrayList<Activity>();
        mActivityRegions = new ArrayList<RectF>();
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
        mActivityPaintBlue = new Paint(0);
        mActivityPaintBlue.setColor(0xff0000ff);
        mActivityPaintBlue.setAntiAlias(true);
        mActivityPaintBlue.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintGreen = new Paint(0);
        mActivityPaintGreen.setColor(0xff00ff00);
        mActivityPaintGreen.setAntiAlias(true);
        mActivityPaintGreen.setStyle(Paint.Style.FILL_AND_STROKE);
        mActivityPaintOrange = new Paint(0);
        mActivityPaintOrange.setColor(0xff333300);
        mActivityPaintOrange.setAntiAlias(true);
        mActivityPaintOrange.setStyle(Paint.Style.FILL_AND_STROKE);


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

    public void addActivities(ArrayList<Activity> activities) {
        mActivities.addAll(activities);
        for (Activity a : activities) {
            mActivityRegions.add(calculateActivityRegion(a));
        }
    }



    //Draw a Board that is split into 3 Columns(Morning,Afternoon,Night) and 7 Rows(WeekDays)
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the day dividing lines
        canvas.drawRect(new RectF(0, 0, mWidth, mHeight), mPaint);
        for (int i = 0; i < 7; i++) {
            canvas.drawLine(0, i * mHeight/7, mWidth, i * mHeight/7, mPaint);
        }

        for(int i = 1; i <= 3; i++){
            canvas.drawLine(i*mWidth/3,0,i*mWidth/3,mHeight,mPaint);
        }

        //loops through all activites creating a Rectangle
        //on the corresponding day
        for (int i = 0; i < mActivities.size(); i++) {
            Activity a = mActivities.get(i);
            RectF r = mActivityRegions.get(i);
            Log.i("WeekView", "Drawing activity " + a);
            Log.i("WeekView", "  Corresponding " + r);
            //Creates the block on the canvas with the appropriate
            //color corresponding to the activity thats being done
            switch (a.getActivity()){
                case "Sleep": canvas.drawRect(r,mActivityPaintYellow);
                    break;
                case "Work": canvas.drawRect(r,mActivityPaintGreen);
                    break;
                case "Workout": canvas.drawRect(r,mActivityPaintBlue);
                    break;
                case "Class": canvas.drawRect(r,mActivityPaint);
                    break;
                case "Eating": canvas.drawRect(r,mActivityPaintOrange);
                    break;
                default:
                   canvas.drawRect(r,mActivityPaint);
            }

        }


    }

    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        // When the screeen size changes (new orientation, etc.), recalcualte all
        // the regions corresponding to the activities.
        mActivityRegions = new ArrayList<RectF>();
        for (Activity a: mActivities) {
            mActivityRegions.add(calculateActivityRegion(a));
        }

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
        for (int i = 0; i < mActivityRegions.size(); i++) {
            if (mActivityRegions.get(i).contains(clickX, clickY)) {
                Log.i("WeekView", "User clicked on Activity " + mActivities.get(i));
                return;
            }
        }
        Log.i("WeekView", "Click detected but no activity clicked on");
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
