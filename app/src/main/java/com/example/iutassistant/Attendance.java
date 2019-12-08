package com.example.iutassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Attendance extends AppCompatActivity {
    DatabaseReference attendanceRef;
    String path="University/IUT/Attendance";
    public  String student_id;
     int i=0;
    Button present,late,absent,record;
    EditText id;
    TextView sec_text,crs_text,teacher_text,attendanceInPercentage;
    EditText date_text;
    StudentsAttendanceList studentsAttendanceList=new StudentsAttendanceList();
    String sec=studentsAttendanceList.getSec();
    String crs=studentsAttendanceList.getCrs();
    AttendanceCalculation attendanceCalculation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);




        String sid=studentsAttendanceList.getSID(i);
        attendanceInPercentage=findViewById(R.id.attendanceInpercentage);
        date_text=findViewById(R.id.dateATT);
        record=findViewById(R.id.record);
        id=findViewById(R.id.Attendence_id);
        sec_text=findViewById(R.id.sec_name);
        crs_text=findViewById(R.id.crs_name);
        teacher_text=findViewById(R.id.teacher_name);
        sec_text.setText(studentsAttendanceList.getSec());
        crs_text.setText(studentsAttendanceList.getCrs());
        teacher_text.setText(studentsAttendanceList.getTeacher_name());
        showRecords();
        if(sid.equals("End")) {
            Toast.makeText(getApplicationContext(), "No more students left, Go back or Edit", Toast.LENGTH_LONG).show();
            i=0;
        }
            //lable set
        else{
            id.setText(sid );
            System.out.println(studentsAttendanceList.getSID(0)+"check  attendance class "+sid);

            path=path+"/"+studentsAttendanceList.getStudentATTpath();
            System.out.println(path+" path printed*%%%%%%%%%%%%%%%%%%%%%%%");
            attendanceRef= FirebaseDatabase.getInstance().getReference(path);
            createAttendanceList(id.getText().toString().trim());

            attendanceCalculation=new AttendanceCalculation(crs,sec,sid,"percentage_text",attendanceInPercentage);

        }

    }

    public void createAttendanceList(String sid){
        present=(Button)findViewById(R.id.present_btn);
        absent=(Button)findViewById(R.id.absent_btn);
        late=(Button)findViewById(R.id.late_id);

         student_id=sid;
        id=findViewById(R.id.Attendence_id);

        System.out.println("Laal1 ");

        final String timeStamp =new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        date_text.setText(timeStamp);
        {System.out.println("***studentId***"+student_id);}
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student_id=id.getText().toString().trim();

                i+=1;
                System.out.println("present a dhukse"+student_id+" "+timeStamp);
                attendanceRef.child(student_id).child(date_text.getText().toString().trim()).setValue("1");

                student_id=studentsAttendanceList.getSID(i);
                if(student_id.equals("End")){
                    Toast.makeText(getApplicationContext(), "No more students left, Go back or Edit", Toast.LENGTH_LONG).show();
                    i=-1;}
                else {
                    id.setText(student_id);
                    date_text.setText(timeStamp);
                    System.out.println("calculation a jabe $$"+student_id+" "+timeStamp);
                    attendanceCalculation=new AttendanceCalculation(crs,sec,student_id,"percentage_text",attendanceInPercentage);
                }


            }
        });

        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student_id=id.getText().toString().trim();

                i+=1;
                System.out.println("LAAAALALALALA"+student_id);

                attendanceRef.child(student_id).child(date_text.getText().toString().trim()).setValue("0");

                student_id=studentsAttendanceList.getSID(i);
                if(student_id.equals("End")){
                    Toast.makeText(getApplicationContext(), "No more students left, Go back or Edit", Toast.LENGTH_LONG).show();
                    i=-1;}
                else {
                    date_text.setText(timeStamp);
                    id.setText(student_id);
                    attendanceCalculation=new AttendanceCalculation(crs,sec,student_id,"percentage_text",attendanceInPercentage);
                }
            }
        });

        late.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student_id=id.getText().toString().trim();

                attendanceRef.child(student_id).child(date_text.getText().toString().trim()).setValue(".5");
                i+=1;
                student_id=studentsAttendanceList.getSID(i);
                if(student_id.equals("End")){
                    Toast.makeText(getApplicationContext(), "No more students left, Go back or Edit", Toast.LENGTH_LONG).show();
                    i=-1;}
                else {
                    date_text.setText(timeStamp);
                    id.setText(student_id);
                    attendanceCalculation=new AttendanceCalculation(crs,sec,student_id,"percentage_text",attendanceInPercentage);
                }
            }
        });

    }

    public void showRecords(){

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attendanceCalculation=new AttendanceCalculation();
                attendanceCalculation.clearRecordLists();
                RecordsShowActivity recordsShowActivity=new RecordsShowActivity();
                recordsShowActivity.setValue(id.getText().toString().trim(),sec,crs);
                startActivity(new Intent(getApplicationContext(),RecordsShowActivity.class));
            }
        });

    }
}
