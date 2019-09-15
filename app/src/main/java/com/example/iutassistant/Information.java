package com.example.iutassistant;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Information extends AppCompatActivity {
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        MainActivity mainActivity=new MainActivity();
        t1=(TextView)findViewById(R.id.textView1);
        t1.setText(mainActivity.getUid());

    }
}
