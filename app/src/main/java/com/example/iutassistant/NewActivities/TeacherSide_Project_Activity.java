package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.Project_Details_Retrival;
import com.example.iutassistant.R;

public class TeacherSide_Project_Activity extends AppCompatActivity implements View.OnClickListener {

    CardView projectList,requestList;
    Project_Details_Retrival project_details_retrival;
    SharedPreferences userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project__teacher);
        userInfo=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);

        projectList = findViewById(R.id.projectListCardViewId);
        requestList = findViewById(R.id.projectRequestListCardViewId);

        projectList.setOnClickListener(this);
        requestList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.projectListCardViewId){
            Intent intent = new Intent(getApplicationContext(), Teacher_Supervising_ProjectList_Activity.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.projectRequestListCardViewId){
            Intent intent = new Intent(getApplicationContext(), Teacher_Requested__ProjectList_Activity.class);
            startActivity(intent);

        }
    }
}
