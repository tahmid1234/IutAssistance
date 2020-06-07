package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.iutassistant.R;

public class New_Existing_Project_Details extends AppCompatActivity {

    private CardView task;
    private TextView existingProjectName, existingCourseName, existingStudentId, existingdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__existing__project__details);

        task = findViewById(R.id.taskId);

        existingProjectName = findViewById(R.id.existingProjectNameID);
        existingCourseName  = findViewById(R.id.existingCourseNameId);
        existingStudentId  = findViewById(R.id.existingStudentId);
        existingdescription  = findViewById(R.id.existingDescriptionId);
    }
}
