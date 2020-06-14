package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.iutassistant.R;

public class New_Attendence_Teacher extends AppCompatActivity implements View.OnClickListener {

    CardView takeAttendence, attendenceList, details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__attendence__teacher);

        takeAttendence = findViewById(R.id.takeAttendenceId);
        attendenceList = findViewById(R.id.attendenceListId);
        details = findViewById(R.id.studentDetailsId);

        takeAttendence.setOnClickListener(this);
        attendenceList.setOnClickListener(this);
        details.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(view.getId() == R.id.takeAttendenceId){
            Intent intent = new Intent(getApplicationContext(),New_Take_Attendence.class);
            startActivity(intent);
        }

        else if(view.getId() == R.id.attendenceListId){
            Intent intent = new Intent(getApplicationContext(),New_AttendenceList.class);
            startActivity(intent);
        }

        else if(view.getId() == R.id.studentDetailsId){
            Intent intent = new Intent(getApplicationContext(),New_Attendence_Details.class);
            startActivity(intent);
        }

    }
}
