package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.iutassistant.R;

public class New_HomePage_Teacher extends AppCompatActivity implements View.OnClickListener {

    CardView classInfo,projects,announcement,attendence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__home_page__teacher);

        classInfo = findViewById(R.id.classInfoId_teacher);
        projects = findViewById(R.id.projectId_teacher);
        attendence = findViewById(R.id.attendenceId_teacher);
        announcement = findViewById(R.id.announcementId_teacher);

        projects.setOnClickListener(this);
        attendence.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.projectId_teacher){
            Intent intent = new Intent(getApplicationContext(),New_Project_Teacher.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
//        else if(view.getId() == R.id.attendenceId_teacher){
//        }

    }
}
