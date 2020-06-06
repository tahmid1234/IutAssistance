package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.iutassistant.R;

public class New_Project_Teacher extends AppCompatActivity implements View.OnClickListener {

    CardView projectList,requestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project__teacher);

        projectList = findViewById(R.id.projectListCardViewId);
        requestList = findViewById(R.id.projectRequestListCardViewId);

        projectList.setOnClickListener(this);
        requestList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.projectListCardViewId){
            Intent intent = new Intent(getApplicationContext(),New_ProjectList_Teacher.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.projectRequestListCardViewId){
            Intent intent = new Intent(getApplicationContext(),New_project__Request_List_Teacher.class);
            startActivity(intent);
        }
    }
}
