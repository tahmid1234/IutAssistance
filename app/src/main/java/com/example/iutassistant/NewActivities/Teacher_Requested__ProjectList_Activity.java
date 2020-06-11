package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.Project_Details_Retrival;
import com.example.iutassistant.R;

import java.util.ArrayList;

public class Teacher_Requested__ProjectList_Activity extends AppCompatActivity {

    ListView ProjectRequsetList;

    SharedPreferences userInfo;
    String supervisorMail;
    Project_Details_Retrival project_details_retrival;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project___request__list__teacher);


        ProjectRequsetList = findViewById(R.id.projectRequestListId);

        userInfo=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);

        project_details_retrival=new Project_Details_Retrival(userInfo,ProjectRequsetList,Teacher_Requested__ProjectList_Activity.this,Constant.Project_Status_Requested_Node);

        project_details_retrival.getRequested_Projects();


    }




}
