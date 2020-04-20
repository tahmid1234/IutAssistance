package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iutassistant.Model.ResheduledInfo;
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

public class ClassResheduleDeclarationActivity extends AppCompatActivity {

    Spinner secSpinner,crsSpinner,typeSpinner;
    Button doneButton;
    EditText explanation_edit,day_edit,month_edit,year_edit;
    DatabaseReference dbList;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_reshedule);
        crsSpinner = (Spinner) findViewById(R.id.crs_value);
        secSpinner=findViewById(R.id.sec_value);
        typeSpinner=findViewById(R.id.type);
        explanation_edit=findViewById(R.id.explanation);
        day_edit=findViewById(R.id.day);
        month_edit=findViewById(R.id.month);
        year_edit=findViewById(R.id.year);
        doneButton=findViewById(R.id.done_btn);

        add_crs_list();
        add_sec_list();
        add_type_list();
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

    public void add_type_list() {


        List<String> list = new ArrayList<String>();
        list.add("Cancellation");
        list.add("Extra Class");
        list.add("");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapter);
    }

    public void doneButton_action(){

        //final String crs=inputName.getText().toString().trim();


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String crs=crsSpinner.getSelectedItem().toString();
                final  String sec=secSpinner.getSelectedItem().toString();
                final  String type=typeSpinner.getSelectedItem().toString();
                final String explanation,day,month,year;
                explanation=explanation_edit.getText().toString().trim();
                day=day_edit.getText().toString().trim();
                month=month_edit.getText().toString().trim();
                year=year_edit.getText().toString().trim();
                final String resheduled_date=day+"-"+month+"-"+year;
                if(TextUtils.isEmpty(crs) ||TextUtils.isEmpty(sec)||TextUtils.isEmpty(type)||TextUtils.isEmpty(explanation)||TextUtils.isEmpty(day)||TextUtils.isEmpty(month)||TextUtils.isEmpty(year))
                {    Toast.makeText(ClassResheduleDeclarationActivity.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
                    return;}
                ResheduledInfo resheduledInfo=new ResheduledInfo(uid,type,explanation,resheduled_date);
                FirebaseDatabase.getInstance().getReference("University/IUT").child("ClassTime").child(sec).child(crs).setValue(resheduledInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       // startActivity(new Intent(getApplicationContext(), TeachersHomePage.class));
                    }
                });
            }
        });

    }

}
