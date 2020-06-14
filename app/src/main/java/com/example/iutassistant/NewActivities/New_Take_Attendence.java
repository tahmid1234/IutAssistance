package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iutassistant.R;

public class New_Take_Attendence extends AppCompatActivity implements View.OnClickListener {

    private CardView present, absent, late;
    private ImageView goLeft, goRight;
    private TextView studentId, percenatge;
    private Button details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__take__attendence);

        present = findViewById(R.id.presentId);
        absent = findViewById(R.id.absentId);
        late = findViewById(R.id.lateId);

        goLeft = findViewById(R.id.goLeftId);
        goRight = findViewById(R.id.goRightId);

        studentId = findViewById(R.id.studentIdforAttendence);
        percenatge = findViewById(R.id.percentageId);

        details = findViewById(R.id.detailsChechId);

        details.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //nai kisu apatoto
    }
}
