package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.Project_Details_Retrival;
import com.example.iutassistant.R;

public class Teacher_Supervising_ProjectList_Activity extends AppCompatActivity {
    Project_Details_Retrival project_details_retrival;
    ListView existingProjectList;
    SharedPreferences userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project_list__teacher);
        userInfo=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        existingProjectList = findViewById(R.id.existingProjectListId);
        project_details_retrival=new Project_Details_Retrival(userInfo,existingProjectList,Teacher_Supervising_ProjectList_Activity.this,Constant.Project_Status_Accepted_Node);
        project_details_retrival.getRequested_Projects();
    }
}
