package com.example.iutassistant.Acitivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iutassistant.R;
import com.example.iutassistant.SingleTone.StudentsAttendanceListSingleTone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class AttendanceActivity extends AppCompatActivity {
    DatabaseReference attendanceRef;
    String path = "University/IUT/Attendance";
    public String student_id;
    int i = 0,check=0;
    Button present, late, absent, record;
    EditText id;
    TextView sec_text, crs_text, teacher_text, attendanceInPercentage;
    EditText date_text;
    StudentsAttendanceListSingleTone studentsAttendanceListSingleTone = StudentsAttendanceListSingleTone.getInstance();
    String sec = studentsAttendanceListSingleTone.getSec();
    String crs = studentsAttendanceListSingleTone.getCrs();


    AttendanceCalculation attendanceCalculation;
    private LinkedHashMap<String, String> student_list = studentsAttendanceListSingleTone.getStudent_list();

    private int size=student_list.size();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        System.out.println(i+"IIIIIIIIIIIIIIIIIIIIIIIII");
        String sid = getSID(i);
        System.out.println(sid + " sid dekhi************************************************");
        attendanceInPercentage = findViewById(R.id.attendanceInpercentage);
        date_text = findViewById(R.id.dateATT);
        record = findViewById(R.id.record);
        id = findViewById(R.id.Attendence_id);
        sec_text = findViewById(R.id.sec_name);
        crs_text = findViewById(R.id.crs_name);
        teacher_text = findViewById(R.id.teacher_name);
        sec_text.setText(studentsAttendanceListSingleTone.getSec());
        crs_text.setText(studentsAttendanceListSingleTone.getCrs());
        teacher_text.setText(studentsAttendanceListSingleTone.getTeacher_name());
        showRecords();
        if (sid.equals("End")) {
            Toast.makeText(getApplicationContext(), "No more students left, Go back or Edit", Toast.LENGTH_LONG).show();
            i = 0;
        }
        //lable set
        else {

            checkSpottedStudents(sid);

            System.out.println(getSID(0) + "check  attendance class " + sid);

            path = path + "/" + studentsAttendanceListSingleTone.getStudentATTpath();
            System.out.println(path + " path printed*%%%%%%%%%%%%%%%%%%%%%%%");
            attendanceRef = FirebaseDatabase.getInstance().getReference(path);
            createAttendanceList(id.getText().toString().trim());

            attendanceCalculation = new AttendanceCalculation(crs, sec, sid, "percentage_text", attendanceInPercentage);

        }

    }

    public void createAttendanceList(String sid) {
        present = (Button) findViewById(R.id.present_btn);
        absent = (Button) findViewById(R.id.absent_btn);
        late = (Button) findViewById(R.id.late_id);

        student_id = sid;
        //id = findViewById(R.id.Attendence_id);

        //System.out.println("Laal1 ");

        final String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        date_text.setText(timeStamp);
        {
            System.out.println("***studentId***" + student_id);
        }
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student_id = id.getText().toString().trim();

                i =Integer.parseInt(student_id)+1;
                System.out.println(i+"present a dhukse" + student_id + " " + timeStamp);
                attendanceRef.child("SpottedStudents").child(student_id).child(date_text.getText().toString().trim()).setValue("1");

                attendanceRef.child( "green/"+date_text.getText()).child(student_id).setValue(1);
                attendanceRef.child(  "red/"+date_text.getText()).child(student_id).removeValue();
                size-=1;

                student_id = getSID(i);
                setColor(student_id);
                if (student_id.equals("End")) {
                    Toast.makeText(getApplicationContext(), "No more students left, Go back or Edit", Toast.LENGTH_LONG).show();
                    i = -1;
                } else {
                    id.setText(student_id);
                    date_text.setText(timeStamp);
                    System.out.println("calculation a jabe $$" + student_id + " " + timeStamp);
                    attendanceCalculation = new AttendanceCalculation(crs, sec, student_id, "percentage_text", attendanceInPercentage);
                }


            }
        });

        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student_id = id.getText().toString().trim();

                i =Integer.parseInt(student_id)+1;
                System.out.println("LAAAALALALALA" + student_id);

                attendanceRef.child("SpottedStudents").child(student_id).child(date_text.getText().toString().trim()).setValue("1");

                attendanceRef.child( "green/"+date_text.getText()).child(student_id).setValue(1);
                attendanceRef.child(  "red/"+date_text.getText()).child(student_id).removeValue();

                size-=1;

                student_id = getSID(i);
                setColor(student_id);
                if (student_id.equals("End")) {
                    Toast.makeText(getApplicationContext(), "No more students left, Go back or Edit", Toast.LENGTH_LONG).show();
                    i = -1;
                } else {
                    date_text.setText(timeStamp);
                    id.setText(student_id);
                    attendanceCalculation = new AttendanceCalculation(crs, sec, student_id, "percentage_text", attendanceInPercentage);
                }
            }
        });

        late.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student_id = id.getText().toString().trim();

                attendanceRef.child("SpottedStudents").child(student_id).child(date_text.getText().toString().trim()).setValue("1");

                attendanceRef.child( "green/"+date_text.getText()).child(student_id).setValue(1);
                attendanceRef.child(  "red/"+date_text.getText()).child(student_id).removeValue();
                i =Integer.parseInt(student_id)+1;
                size-=1;

                student_id = getSID(i);
                setColor(student_id);
                if (student_id.equals("End")) {
                    Toast.makeText(getApplicationContext(), "No more students left, Go back or Edit", Toast.LENGTH_LONG).show();
                    i = -1;
                } else {
                    date_text.setText(timeStamp);
                    id.setText(student_id);
                    attendanceCalculation = new AttendanceCalculation(crs, sec, student_id, "percentage_text", attendanceInPercentage);
                }
            }
        });

    }

    public void showRecords() {

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attendanceCalculation = new AttendanceCalculation();
                attendanceCalculation.clearRecordLists();
                RecordsShowActivity recordsShowActivity = new RecordsShowActivity();
                recordsShowActivity.setValue(id.getText().toString().trim(), sec, crs);
                startActivity(new Intent(getApplicationContext(), RecordsShowActivity.class));
            }
        });

    }

    public String getSID(int i) {
        if(size==1)
            return "End";

        System.out.println(size+"   checking!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+student_list);
        if (i == 0) {
            Map.Entry<String, String> entry = student_list.entrySet().iterator().next();
           // System.out.println(entry.getKey()+"   entry!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+student_list);
            return entry.getKey();
        } else {
            String id = i + "";
            if (student_list.get(id) == null)
                return getSID(i + 1);
            return id;

        }
    }
    public void setColor(String student_id){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String formatedDate=formatter.format(date);

        if(student_list.get(student_id).equals("red"))
            id.setTextColor(Color.RED);
        else
            id.setTextColor(Color.GREEN);
        System.out.println(student_id+" studentssssssssssssssssssssssss "+student_list.get(student_id));

    }
    public void checkSpottedStudents(String student_id){
        FirebaseDatabase.getInstance().getReference("University/IUT/Attendance/"+sec+"/"+crs+"/green/"+studentsAttendanceListSingleTone.getFormatedDate()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot datas:dataSnapshot.getChildren()){
                        String key=datas.getKey().toString();
                        student_list.put(key,"green");
                        System.out.println(key+"KEYSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");

                    }


                }
                if(check==0) {
                    setColor(student_id);
                    id.setText(student_id);
                    check=100;
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
