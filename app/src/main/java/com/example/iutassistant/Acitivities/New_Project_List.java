package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iutassistant.R;

public class New_Project_List extends AppCompatActivity {

    private ListView projectList;

    private Button newProject;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project__list);

        newProject = findViewById(R.id.createNewProject);
        projectList = findViewById(R.id.allprojects);
        myDialog = new Dialog(this);
    }

    public void showPopUp(View v){

        Button request;

        myDialog.setContentView(R.layout.activity_new__project_request);

        myDialog.show();


    }
}
