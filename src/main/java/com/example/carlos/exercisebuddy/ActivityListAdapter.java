package com.example.carlos.exercisebuddy;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by carlosyoung on 2/12/15.
 */
public class ActivityListAdapter extends BaseAdapter {
    private ArrayList<Activities> activityList = new ArrayList<Activities>();
    private LayoutInflater inflater;


    public ActivityListAdapter(ArrayList<Activities> activityList, Context context){
        this.activityList = activityList;
        this.inflater = inflater.from(context);
    }


    @Override
    public int getCount() {
        return activityList.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Activities activities = activityList.get(position);

        view = inflater.inflate(R.layout.activity_item_layout,null);
        //Button update = (Button)view.findViewById(R.id.updateButton);
        TextView tvActivity = (TextView) view.findViewById(R.id.tvActivity);
        TextView tvDay = (TextView) view.findViewById(R.id.tvDay);
        TextView tvStart = (TextView) view.findViewById(R.id.tvStart);
        TextView tvEnd = (TextView) view.findViewById(R.id.tvEnd);
        TextView tvID = (TextView) view.findViewById(R.id.tvID);

        tvActivity.setText(activities.getActivity());
        tvDay.setText(activities.getDay());
        tvStart.setText(activities.getStart());
        tvEnd.setText(activities.getEnd());
        tvID.setText(activities.getID());
        //tvRecordId.setText(activities.getId());
        //tvRecordId.setVisibility(View.INVISIBLE);
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
