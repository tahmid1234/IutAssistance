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

import com.example.iutassistant.Model.AnnouncementInfo;
import com.example.iutassistant.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Quiz_Announcement_Adapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<AnnouncementInfo> announcementInfos;

    public Quiz_Announcement_Adapter(Context mContext, ArrayList<AnnouncementInfo> announcementInfos) {
        this.announcementInfos = announcementInfos;
        this.mContext=mContext;
        System.out.println(announcementInfos.size()+" ****atttttt okay  size 2*****");
    }

    @Override
    public int getCount() {
        return announcementInfos.size();
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
            view = layoutInflater.inflate(R.layout.list_view_all_announcement_list,viewGroup,false);}
            System.out.println("Dhukse naki tw k jane!!!!!!!!!!!!!11");

        TextView crs = view.findViewById(R.id.crs);
        TextView date = view.findViewById(R.id.date);
        TextView syllabus=view.findViewById(R.id.syllabu_quiz);
        TextView type=view.findViewById(R.id.quizCount);
        TextView date_text_var=view.findViewById(R.id.date_text);
        TextView info_text=view.findViewById(R.id.info);
        TextView type_text=view.findViewById(R.id.type_text);
        TextView crs_text=view.findViewById(R.id.crs_name_text);
        date_text_var.setText("Quiz Date");
        info_text.setText("Syllabus");
        type_text.setText("Quiz No");
        crs.setText(announcementInfos.get(i).getCrs());
        date.setText(announcementInfos.get(i).getGiven_date());
        type.setText(announcementInfos.get(i).getType_or_no());
        syllabus.setText(announcementInfos.get(i).getDescriptive_part());
        String timeStamp =new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp+" "+ announcementInfos.get(i).getGiven_date());
        if(timeStamp.equals(announcementInfos.get(i).getGiven_date())){
            view.setBackgroundColor(Color.argb((float) .3, 128, 0, 0));
            crs.setTextColor(Color.WHITE);
            date.setTextColor(Color.WHITE);
            type.setTextColor(Color.WHITE);
            syllabus.setTextColor(Color.WHITE);
            date_text_var.setTextColor(Color.WHITE);
            info_text.setTextColor(Color.WHITE);
            date_text_var.setTextColor(Color.WHITE);
            crs_text.setTextColor(Color.WHITE);


        }
        else if(timeStamp.compareTo(announcementInfos.get(i).getGiven_date())<0) {
            view.setBackgroundColor(Color.argb((float) .3, 0, 0, 255));

        }
        else {
            view.setBackgroundColor(Color.argb((float) .3, 128, 128, 0));

        }


        return view;
    }
}
