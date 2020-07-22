package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.iutassistant.R;

public class Course_Details_Student extends AppCompatActivity {

    private TextView teacherName, teacherMail, enrolledStudent, percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course__details__student);

        teacherName = findViewById(R.id.courseTeacherName);
        teacherMail = findViewById(R.id.courseTeacher_Mail);
        enrolledStudent = findViewById(R.id.attendence_Percentage);
        percentage = findViewById(R.id.enrolledStudent);

    }
}