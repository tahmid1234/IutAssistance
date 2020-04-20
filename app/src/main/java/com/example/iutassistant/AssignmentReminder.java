package com.example.iutassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.Model.AnnouncementInfo;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AssignmentReminder extends AppCompatActivity {

    DatabaseReference announcementInfoDB;
    public String sec, id, crs, path,syllabus,date,type;
    ListView listView;
    static  int i=0;
    ArrayList<AnnouncementInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_reminder);
    }



}
