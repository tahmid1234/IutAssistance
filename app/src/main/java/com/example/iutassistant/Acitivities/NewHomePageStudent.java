package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.iutassistant.R;

public class NewHomePageStudent extends AppCompatActivity implements View.OnClickListener{

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


        classInfo.setOnClickListener(this);
        projects.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

//        if(view.getId()==R.id.classInfoId){
//            Intent intent = new Intent(getApplicationContext(), NewClassInfo.class);
//            startActivity(intent)/
//        }
        if(view.getId()==R.id.projectId){
            Intent intent = new Intent(getApplicationContext(), New_Project_List.class);
            startActivity(intent);
        }

    }
}
