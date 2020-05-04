package com.example.iutassistant.AdapterClasses;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.iutassistant.Acitivities.AttendanceInPercentage;
import com.example.iutassistant.R;

import java.util.ArrayList;

public class AttendanceInPercentageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<AttendanceInPercentage> attendanceInPercentageArrayList;
    Double percentageAttendanceInDOuble;

    public AttendanceInPercentageAdapter(Context mContext, ArrayList<AttendanceInPercentage> attendanceInPercentageArrayList) {
        this.attendanceInPercentageArrayList=attendanceInPercentageArrayList;

        this.mContext=mContext;
        System.out.println(attendanceInPercentageArrayList.size()+" ****atttttt  size 2*****");
    }

    @Override
    public int getCount() {
        return attendanceInPercentageArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_view_attendance_percentage,viewGroup,false);}

        System.out.println("Adapter");

        TextView crs = view.findViewById(R.id.crs);
        TextView percentage = view.findViewById(R.id.percentage);
        TextView count=view.findViewById(R.id.count);



        percentageAttendanceInDOuble=attendanceInPercentageArrayList.get(i).getAttendanceInPercentage();
        if(percentageAttendanceInDOuble<75.0)
            view.setBackgroundColor(Color.argb((float) .6, 255, 0, 0));
        else
            view.setBackgroundColor(Color.argb((float) .4, 0, 128, 0));

        String attendance_percentage=Double.toString(percentageAttendanceInDOuble)+"%";
        crs.setText(attendanceInPercentageArrayList.get(i).getCrs());
        percentage.setText(attendance_percentage);





        return view;
    }
}
