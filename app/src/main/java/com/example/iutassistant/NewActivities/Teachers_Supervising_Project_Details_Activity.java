package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.iutassistant.Model.ProjectInfo;
import com.example.iutassistant.R;

public class Teachers_Supervising_Project_Details_Activity extends AppCompatActivity {

    private CardView task;
    private TextView existingProjectName, existingCourseName, existingStudentId, existingdescription;

    private  static ProjectInfo project_list_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__existing__project__details);

        task = findViewById(R.id.taskId);

        existingProjectName = findViewById(R.id.existingProjectNameID);
        existingCourseName  = findViewById(R.id.existingCourseNameId);
        existingStudentId  = findViewById(R.id.existingStudentId);
        existingdescription  = findViewById(R.id.existingDescriptionId);

        existingProjectName.setText(project_list_info.getProjectName());
        existingCourseName.setText(project_list_info.getCourse());
        existingdescription.setText(project_list_info.getDescription());
        existingdescription.setText(project_list_info.getAllSid());
    }

    public static void setProject_list_info(ProjectInfo project_list_info) {
        Teachers_Supervising_Project_Details_Activity.project_list_info = project_list_info;
    }
}
