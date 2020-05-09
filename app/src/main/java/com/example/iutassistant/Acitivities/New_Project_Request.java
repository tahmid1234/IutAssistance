package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.iutassistant.R;

public class New_Project_Request extends AppCompatActivity {

    private TextView projectName,teamMate,courseName,description;
    private AutoCompleteTextView supervisor;
    private Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project_request);

        projectName = findViewById(R.id.projectNameId);
        teamMate = findViewById(R.id.teamMateId);
        courseName = findViewById(R.id.courseNameId);
        description = findViewById(R.id.descriptionId);

        supervisor = findViewById(R.id.superVisor_Id);

        request = findViewById(R.id.buttonRequestId);
    }
}
