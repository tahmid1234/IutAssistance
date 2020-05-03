package com.example.iutassistant.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iutassistant.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class UniversitySelectingClass extends AppCompatActivity {

    private Spinner university;
    private Button btnSubmit;
    private static String uni;
    private DatabaseReference databaseReference;
    RadioButton radioStudent,radioTeacher;
    RadioGroup RADIO_GROUP;
    private static String proffesion ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_selecting_class);
        RADIO_GROUP = findViewById(R.id.radioGroupId);
        radioStudent=(RadioButton)findViewById(R.id.student);
        radioTeacher=(RadioButton)findViewById(R.id.teacher);
        addItemsOnuniversity();
        addListenerOnButton();

    }

    public void addItemsOnuniversity() {

        university = (Spinner) findViewById(R.id.idUniversity);
        List<String> list = new ArrayList<String>();
        list.add("IUT");
        list.add("KUET");
        list.add("BUET");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        university.setAdapter(dataAdapter);
    }

    public void addListenerOnButton() {


        university = (Spinner) findViewById(R.id.idUniversity);
        btnSubmit = (Button) findViewById(R.id.submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 uni=university.getSelectedItem().toString();
                if(radioStudent.isChecked()){
                    proffesion="Students";

                }
                else {
                    proffesion = "Teachers";

                }
                if(TextUtils.isEmpty(uni)){
                    Toast.makeText(UniversitySelectingClass.this, "Enter the name of your university", Toast.LENGTH_SHORT).show();
                    return;
                }


                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }

        });
    }


    public void setUni(String uni) {
        this.uni = uni;
        System.out.println("Laal 1");
        System.out.println(uni);

    }

    public String getUid() {
        System.out.println("Laal2");
        System.out.println(uni);
        return uni;}

    public  String getProffesion() {
        return proffesion;
    }
}
