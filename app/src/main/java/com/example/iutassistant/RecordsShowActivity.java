package com.example.iutassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class RecordsShowActivity extends AppCompatActivity {

    static String id,sec,crs;

    public  void setValue(String id,String sec,String crs) {
        RecordsShowActivity.id = id;
        RecordsShowActivity.sec=sec;
        RecordsShowActivity.crs=crs;
    }
    AttendanceCalculation attendanceCalculation;
    TextView id_text;
    ListView recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_show);
        id_text=findViewById(R.id.id_text);
        id_text.setText(id);
        recordList=findViewById(R.id.recordList);
        attendanceCalculation=new AttendanceCalculation(crs,sec,id,"records",RecordsShowActivity.this,recordList);
    }
}
