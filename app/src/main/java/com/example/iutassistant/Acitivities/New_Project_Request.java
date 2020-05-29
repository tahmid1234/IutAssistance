package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iutassistant.R;

public class New_Project_Request extends AppCompatActivity {

    private EditText projectName,teamMateId,courseName,description;
    private AutoCompleteTextView supervisor;
    private Button addId,request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project__list);
    }

}
