package com.example.iutassistant.SingleTone;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

public class StudentsAttendanceListSingleTone {
    String path;
    private static StudentsAttendanceListSingleTone instance;
    static String studentATTpath,sec,crs,teacher_name;
    static LinkedHashMap<String, String> student_list = new LinkedHashMap<>();
    public String formatedDate;

    public String getFormatedDate() {
        return formatedDate;
    }

    private StudentsAttendanceListSingleTone(){

    }
    public static StudentsAttendanceListSingleTone getInstance(){
        if(instance==null){
            synchronized (StudentsAttendanceListSingleTone.class) {
                if (instance == null) {
                    instance = new StudentsAttendanceListSingleTone();
                }
            }
        }
        return instance;
    }
    public void setStudentsAttendanceList(String path,String sec,String crs){
        System.out.println("ETA keu call korse? k?***************************************************"+student_list.size());
        student_list.clear();
        this.path=path;
        final String section=sec;
        FirebaseDatabase.getInstance().getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(section).exists()){
                    for (DataSnapshot sSnapshot : dataSnapshot.child(section).getChildren()) {
                        //loop 2 to go through all the child nodes of books node
                        String sidkey = sSnapshot.getKey();
                        String sidValue = String.valueOf(sSnapshot.getValue());
                        System.out.println(sidkey+"      sidKey****************************************");
                        //student_list.add(sidkey);
                        student_list.put(sidkey, "red");
                    }
                    student_list.put("END","END");
                    System.out.println("   checking!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+student_list);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date();
                     formatedDate=formatter.format(date);
                    FirebaseDatabase.getInstance().getReference("University/IUT/Attendance/"+sec+"/"+crs+"/red/"+formatedDate).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){



                            }
                            else {

                                FirebaseDatabase.getInstance().getReference("University/IUT/Attendance/" + sec + "/" + crs + "/red/"+formatedDate).setValue(student_list);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    public static LinkedHashMap<String, String> getStudent_list() {
        System.out.println("   cgetting!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+student_list);
        return student_list;
    }

    /*public String getSID(int i){
            if(i==0) {
                Map.Entry<String, String> entry = student_list.entrySet().iterator().next();
                return entry.getKey();
            }
            else
            {
                String id=i+"";
                if(student_list.get(id)==null)
                    return getSID(i+1);
                return id;

            }


    }*/
    public void setStudentsAttendanceListPath(String sec,String crs,String teacher_name){

                this.sec=sec;
                this.crs=crs;
                this.teacher_name=teacher_name;
                studentATTpath=sec+"/"+crs;
               // System.out.println(studentATTpath);

    }
    public  String getStudentATTpath(){
        return studentATTpath;
    }

    public static String getCrs() {
        return crs;
    }

    public static String getSec() {
        return sec;
    }
    public static String getTeacher_name(){
        return teacher_name;
    }

  /*  public void checkSpottedStudents(){
        FirebaseDatabase.getInstance().getReference("University/IUT/Attendance/"+sec+"/"+crs+"/green/"+formatedDate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot datas:dataSnapshot.getChildren()){
                        String key=datas.getKey().toString();
                        student_list.put(key,"green");
                        System.out.println(key+"KEYSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}
