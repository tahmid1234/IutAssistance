package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.iutassistant.R;

public class New_Project_Details_After_Click_To_ListView extends AppCompatActivity {

    private CardView accept, reject;
    private TextView projectName, courseName, studentId, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project__details__after__click__to__list_view);

        accept = findViewById(R.id.acceptProjectId);
        reject = findViewById(R.id.rejectProjectId);

        projectName = findViewById(R.id.prjectNameID);
        courseName  = findViewById(R.id.courseNameId);
        studentId  = findViewById(R.id.studentIdforProject);
        description  = findViewById(R.id.descriptionOfProjectId);
    }
}
