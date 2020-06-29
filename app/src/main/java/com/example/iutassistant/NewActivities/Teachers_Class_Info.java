package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.example.iutassistant.R;

public class Teachers_Class_Info extends AppCompatActivity {

    public AutoCompleteTextView selectCourse, selectSection;
    private Button invite;
    private ListView invitedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers__class__info);


        selectCourse = findViewById(R.id.courseSelectId);
        selectSection = findViewById(R.id.sectionSelectId);

        invite = findViewById(R.id.inviteButtonId);

        invitedList = findViewById(R.id.invitedListId);
    }
}
