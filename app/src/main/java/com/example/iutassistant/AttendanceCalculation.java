package com.example.iutassistant;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.iutassistant.AdapterClasses.AttendanceInPercentageAdapter;
import com.example.iutassistant.AdapterClasses.Attendance_Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AttendanceCalculation {

    static Double totalClasses=0.0,totalAttendance=0.0,attendacePercentage=0.0;
    static  ArrayList<Attendance_detail> list = new ArrayList<>();
    static ArrayList<AttendanceInPercentage> attendanceInPercentageArrayList=new ArrayList<>();
    AttendanceInPercentage attendanceInPercentage;
    static String id;
    String crs,sec,  type;
    static Context mContext;
    static ListView listView_in;
    static TextView attendanceText;

    AttendanceCalculation(){}
    AttendanceCalculation(String crs, String sec, String id, String type, final Context mContext, final ListView listView_in){
        System.out.println(sec+"Hellooooooooooo Worldddddddddddd"+crs);
         this.id=id;
         this.sec=sec;
         this.crs=crs;
         this.type=type;
         this.mContext=mContext;
         this.listView_in=listView_in;
    calculateAttendance();}

    public AttendanceCalculation(String crs, String sec, String id, String type, TextView attendanceText) {
        this.id=id;
        this.sec=sec;
        this.crs=crs;
        this.type=type;
        this.attendanceText=attendanceText;
        attendanceText.setText("First_Class");
        System.out.println(attendanceText.getText().toString().trim()+"ekhane ki hobe"+id);
        calculateAttendance();
    }


    public Double calculateAttendance(){
        FirebaseDatabase.getInstance().getReference("University/IUT/Attendance").child(sec).child(crs).addValueEventListener(new ValueEventListener() {

            {System.out.println("bujhi na kisui jontronaaaaaaaaaaa");}

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("function 33333333 no data changr a&&&&&&&&&&&&&&&&&&&&&&&&7");
                totalClasses=0.0;
                totalAttendance=0.0;
                for(DataSnapshot crsSnapshot : dataSnapshot.child(id).getChildren()){
                    String date=crsSnapshot.getKey();
                    String count=String.valueOf(crsSnapshot.getValue());
                    Attendance_detail attendance_detail=new Attendance_detail(crs,date,count);
                    totalClasses++;
                    //  if(count.equals("1"))
                    totalAttendance=totalAttendance+Double.parseDouble(count);


                    date="Date"+date+"="+count;
                    System.out.println(count+"function a&&&&&&&&&&&&&&&&&&&&&&&&7"+id+" "+crs+" total classes and attendace"+totalAttendance+"  "+totalClasses);

                    list.add(attendance_detail);

            }

               // if(type=="percentage_text")
                 //   attendanceText.setText("First_Class");

                if(totalClasses>0) {
                    attendacePercentage = (totalAttendance / totalClasses) * 100;
                    if(type=="percentageList"){
                    attendanceInPercentage = new AttendanceInPercentage(crs, attendacePercentage);

                    attendanceInPercentageArrayList.add(attendanceInPercentage);
                        AttendanceInPercentageAdapter attendanceInPercentageAdapter = new AttendanceInPercentageAdapter(mContext, attendanceInPercentageArrayList);
                        listView_in.setAdapter(attendanceInPercentageAdapter);
                    }
                    else if(type=="records"){
                        Attendance_Adapter attendance_adapter=new Attendance_Adapter(mContext,list);
                        listView_in.setAdapter(attendance_adapter);
                    }
                    else{
                        System.out.println("Ki re baba ki kahini bujhlam naa"+id);
                        attendanceText.setText(Double.toString(attendacePercentage)+"%");
                    }



                    // Collections.reverse(list);
                    // Attendance_Adapter attendance_adapter=new Attendance_Adapter(ShowAttendance.this,list);

                    // listView.setAdapter(attendance_adapter);

                }
                }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            });

        return attendacePercentage;

    }

    void clearRecordLists(){
        list.clear();

    }
    void clearPercentageLists(){
        attendanceInPercentageArrayList.clear();

    }


}
