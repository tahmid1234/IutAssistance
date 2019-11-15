package com.example.iutassistant;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentsAttendanceList {
    String path;
    static String studentATTpath,sec,crs,teacher_name;
    static List<String> student_list=new ArrayList<>();
    StudentsAttendanceList(){}
    StudentsAttendanceList(String path,String sec){
        student_list.clear();
        this.path=path;
        final String section=sec;
        FirebaseDatabase.getInstance().getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sSnapshot : dataSnapshot.child(section).getChildren()) {
                    //loop 2 to go through all the child nodes of books node
                    String sidkey = sSnapshot.getKey();
                    String sidValue = String.valueOf(sSnapshot.getValue());
                    student_list.add(sidkey);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public String getSID(int i){
        System.out.println(i+"  hdgfjg    get i ");
        if(i<student_list.size()){
            System.out.println(student_list.get(i)+"  hdgfjg 2   get i ");
        return student_list.get(i);}
        else
            return "End";
    }
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
}
