package com.example.iutassistant.AdapterClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.ProjectInfo;
import com.example.iutassistant.NewActivities.Teachers_Requested_Project_Details_Activity;
import com.example.iutassistant.NewActivities.Teachers_Supervising_Project_Details_Activity;
import com.example.iutassistant.R;

import java.util.ArrayList;

public class Project_List_Adapter extends BaseAdapter {

    ArrayList<ProjectInfo> project_list_infos = new ArrayList<>();
    private Context mContext;

    TextView project_name, crs_name, prog_batch;
    String project_status;

    public Project_List_Adapter(ArrayList<ProjectInfo> project_list_infos, Context mContext,String project_status) {
        this.project_list_infos = project_list_infos;
        this.mContext = mContext;
        this.project_status=project_status;
    }

    @Override
    public int getCount() {
        return project_list_infos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.teacher_side_project_listview_item, parent, false);
        }

        project_name = convertView.findViewById(R.id.ProjectName);
        crs_name = convertView.findViewById(R.id.CourseName);
        prog_batch = convertView.findViewById(R.id.secBatchId);

        project_name.setText(project_list_infos.get(position).getProjectName());
        crs_name.setText(project_list_infos.get(position).getCourse());
        prog_batch.setText(project_list_infos.get(position).getBatch_prog());

        cliclItem(convertView, position);

        return convertView;
    }

    public void cliclItem(View view, int i) {
        System.out.println(project_list_infos.get(i) + " upre  " + i);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(project_list_infos.get(i).getProjectName() + " niche" + i);


                sendingInfo(project_list_infos.get(i));

            }
        });

    }
    public void sendingInfo(ProjectInfo project_list_info){
        if(project_status.equals(Constant.Project_Status_Requested_Node)) {
            Teachers_Requested_Project_Details_Activity teachers_requested_project_details_activity = new Teachers_Requested_Project_Details_Activity();
            teachers_requested_project_details_activity.setProject_list_info(project_list_info);
            mContext.startActivity(new Intent(mContext.getApplicationContext(), Teachers_Requested_Project_Details_Activity.class));
        }
        else{
                Teachers_Supervising_Project_Details_Activity teachers_supervising_project_details_activity=new Teachers_Supervising_Project_Details_Activity();
                teachers_supervising_project_details_activity.setProject_list_info(project_list_info);
            mContext.startActivity(new Intent(mContext.getApplicationContext(), Teachers_Supervising_Project_Details_Activity.class));

        }
    }

}

