package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.iutassistant.R;

public class New_Project_List extends AppCompatActivity implements View.OnClickListener{

    private ListView projectList;

    private Button newProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project__list);

        newProject = findViewById(R.id.buttonCreateProjectId);
        projectList = findViewById(R.id.projctListViewId);

        newProject.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonCreateProjectId){
            Intent intent = new Intent(getApplicationContext(),New_Project_Request.class);
            startActivity(intent);
        }
    }
}
