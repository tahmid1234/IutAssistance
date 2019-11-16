package com.example.iutassistant.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iutassistant.Attendance_detail;
import com.example.iutassistant.Post;
import com.example.iutassistant.R;
import com.example.iutassistant.ShowAttendance;

import java.util.ArrayList;
import java.util.List;

public class Attendance_Adapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Attendance_detail> attendance_details;

    public Attendance_Adapter(Context mContext, ArrayList<Attendance_detail> attendance_details) {
        this.attendance_details=attendance_details;
        this.mContext=mContext;
        System.out.println(attendance_details.size()+" ****atttttt  size 2*****");
    }

    @Override
    public int getCount() {
        return attendance_details.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_view_attendance_layout,viewGroup,false);}


        TextView crs = view.findViewById(R.id.crs);
        TextView date = view.findViewById(R.id.date);
        TextView count=view.findViewById(R.id.count);

        crs.setText(attendance_details.get(i).getCrs());
        date.setText(attendance_details.get(i).getDate());
        count.setText(attendance_details.get(i).getAttendace_count());




        return view;
    }
}
