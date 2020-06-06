package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.R;

public class New_ProjectList_Teacher extends AppCompatActivity {

    ListView existingProjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project_list__teacher);

        existingProjectList = findViewById(R.id.existingProjectListId);
    }
}
