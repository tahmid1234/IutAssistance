package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.iutassistant.R;

import java.util.ArrayList;
import java.util.List;

public class AttendanceSelection extends AppCompatActivity {

    Spinner inputUni,inputCrs,inputSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_selection);
    }


}
