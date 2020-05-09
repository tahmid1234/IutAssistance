package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.iutassistant.R;

public class NewHomePageStudent extends AppCompatActivity {

    private CardView classInfo,projects,invitation,request;

    private TextView numOfRequest;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home_page_student);

        //card view id
        classInfo = findViewById(R.id.classInfoId);
        projects = findViewById(R.id.projectId);
        invitation = findViewById(R.id.invitationId);
        request = findViewById(R.id.requestId);

        //textview er id
        numOfRequest = findViewById(R.id.requestNumberId);


    }
}
