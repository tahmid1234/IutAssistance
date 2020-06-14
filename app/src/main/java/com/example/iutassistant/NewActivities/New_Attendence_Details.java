package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.example.iutassistant.R;

public class New_Attendence_Details extends AppCompatActivity {

    private AutoCompleteTextView studentId;
    private Button seeDetails;
    private ListView attendenceDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__attendence__details);

        studentId = findViewById(R.id.autoStudentId);
        seeDetails = findViewById(R.id.seeDetailsId);
        attendenceDetails = findViewById(R.id.detailsAttendenceListviewId);
    }
}
