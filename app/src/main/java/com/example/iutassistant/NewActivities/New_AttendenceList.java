package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.R;

public class New_AttendenceList extends AppCompatActivity {

    private ListView todaysAttendence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__attendence_list);

        todaysAttendence = findViewById(R.id.attendenceThatDatListId);
    }
}
