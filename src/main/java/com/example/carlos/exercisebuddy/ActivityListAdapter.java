package com.example.carlos.exercisebuddy;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by carlosyoung on 2/12/15.
 */
public class ActivityListAdapter extends BaseAdapter {
    private ArrayList<Activity> activityList = new ArrayList<Activity>();
    private LayoutInflater inflater;

//Passes an activity list to an adapter to inflate the List
    public ActivityListAdapter(ArrayList<Activity> activityList, Context context){
        this.activityList = activityList;
        this.inflater = inflater.from(context);
    }


    @Override
    public int getCount() {
        return activityList.size();
    }

    @Override
    //override method that set the View and all elements in the xml file to local variables
    public View getView(int position, View view, ViewGroup parent) {
        Activity activities = activityList.get(position);

        view = inflater.inflate(R.layout.activity_item_layout,null);

        //Assigns TextViews so the ArrayAdapter can be displayed
        TextView tvActivity = (TextView) view.findViewById(R.id.tvActivity);
        TextView tvDay = (TextView) view.findViewById(R.id.tvDay);
        TextView tvStart = (TextView) view.findViewById(R.id.tvStart);
        TextView tvEnd = (TextView) view.findViewById(R.id.tvEnd);
        TextView tvAm1 = (TextView) view.findViewById(R.id.tvAmOrPm);
        TextView tvAm2 = (TextView) view.findViewById(R.id.tvAmOrPm2);

        //Set these Textviews with the Proper String Value
        tvActivity.setText(activities.getActivity());
        tvDay.setText(activities.getDay());
        tvStart.setText(activities.getStart());
        tvEnd.setText(activities.getEnd());
        tvAm1.setText(activities.getStartAM());
        tvAm2.setText(activities.getEndAM());

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {

        return activityList.get(position);
    }
}
