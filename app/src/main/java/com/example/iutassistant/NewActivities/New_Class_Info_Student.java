package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.R;

public class New_Class_Info_Student extends AppCompatActivity {

    private ListView allCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__class__info__student);


        allCourses = findViewById(R.id.allCoursesId_Student);
    }
}