package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {
    Spinner secSpinner,crsSpinner,quizNo_spinner;
    Button doneButton;
    EditText syllabus_edit,day_edit,month_edit,year_edit;
    DatabaseReference dbList;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        crsSpinner = (Spinner) findViewById(R.id.crs_value);
        secSpinner=findViewById(R.id.sec_value);
        syllabus_edit=findViewById(R.id.syllabus);
        day_edit=findViewById(R.id.day);
        month_edit=findViewById(R.id.month);
        year_edit=findViewById(R.id.year);
        doneButton=findViewById(R.id.done_btn);
        quizNo_spinner=findViewById(R.id.quiz_no);



        add_crs_list();
        add_sec_list();
        add_quizNo_list();
        doneButton_action();


    }

    public void add_sec_list(){


        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbList= FirebaseDatabase.getInstance().getReference("University/IUT/Section_assigned_Teacher");
        final List<String> list = new ArrayList<String>();

        dbList.addValueEventListener(new ValueEventListener() {
            {System.out.println("HOI ns krno");}
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                System.out.println("HOI  krno");
                for(DataSnapshot secSnapshot : dataSnapshot.child(uid).getChildren()){
                    String seckey = secSnapshot.getKey();
                   // String secValue = String.valueOf(crsSnapshot.getValue());
                    list.add(seckey);

                    System.out.println(seckey+ "  ^^^^^^ ");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.add("");

        ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secSpinner.setAdapter(dataAdapter);
    }
    public void add_crs_list(){


        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbList= FirebaseDatabase.getInstance().getReference("University/IUT/Courses_assigned_Teacher");
        final List<String> list = new ArrayList<String>();

        dbList.addValueEventListener(new ValueEventListener() {
            {System.out.println("HOI ns krno");}
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                System.out.println("HOI  krno");
                for(DataSnapshot secSnapshot : dataSnapshot.child(uid).getChildren()){
                    String crskey = secSnapshot.getKey();
                    // String secValue = String.valueOf(crsSnapshot.getValue());
                    list.add(crskey);

                    System.out.println(crskey+ "  ^^^^^^ ");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.add("");

        ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crsSpinner.setAdapter(dataAdapter);
    }

    public void add_quizNo_list() {


        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quizNo_spinner.setAdapter(dataAdapter);
    }


    public void doneButton_action(){

        //final String crs=inputName.getText().toString().trim();


       doneButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String crs=crsSpinner.getSelectedItem().toString();
               final  String sec=secSpinner.getSelectedItem().toString();
               final  String quizNo=quizNo_spinner.getSelectedItem().toString();
               final String syllabus,day,month,year;
               syllabus=syllabus_edit.getText().toString().trim();
               day=day_edit.getText().toString().trim();
               month=month_edit.getText().toString().trim();
               year=year_edit.getText().toString().trim();
               final String quiz_date=day+"-"+month+"-"+year;
               if(TextUtils.isEmpty(crs) ||TextUtils.isEmpty(sec)||TextUtils.isEmpty(quizNo)||TextUtils.isEmpty(syllabus)||TextUtils.isEmpty(day)||TextUtils.isEmpty(month)||TextUtils.isEmpty(year))
               {    Toast.makeText(Quiz.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
               return;}
               QuizInfo quizInfo=new QuizInfo(uid,syllabus,quiz_date,quizNo);
               FirebaseDatabase.getInstance().getReference("University/IUT").child("Quiz").child(sec).child(crs).setValue(quizInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                      // startActivity(new Intent(getApplicationContext(), TeachersHomePage.class));
                   }
               });
           }
       });

    }


}
