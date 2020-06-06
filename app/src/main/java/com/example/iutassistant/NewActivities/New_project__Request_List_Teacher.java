package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.R;

public class New_project__Request_List_Teacher extends AppCompatActivity {

    ListView ProjectRequsetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project___request__list__teacher);


        ProjectRequsetList = findViewById(R.id.projectRequestListId);
    }
}
