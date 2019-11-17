package com.example.iutassistant;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AnnouncementDetail {
    String crs,given_date,descriptive_part,type_or_no,teacher_name;

    public String getCrs() {
        return crs;
    }

    public String getGiven_date() {
        return given_date;
    }

    public String getDescriptive_part() {
        return descriptive_part;
    }

    public String getType_or_no() {
        return type_or_no;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    AnnouncementDetail(String crs, String given_date, String descriptive_part, String type_or_no, String t_id){
        this.crs=crs;
        this.given_date=given_date;
        this.descriptive_part=descriptive_part;
        this.type_or_no=type_or_no;
        final String teacher_id=t_id;
        FirebaseDatabase.getInstance().getReference("Teachers").child(teacher_id).addListenerForSingleValueEvent(new ValueEventListener() {
            {System.out.println("BOOOOOOOOOOOOOOOOOOOOOOO");}
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(teacher_id+" okayyyyyyyyyyyyyyyyyyyyyyyyyyyy");
                teacher_name=String.valueOf(dataSnapshot.child("name").getValue());
                System.out.println(teacher_id+"Teacher_********name"+teacher_name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        System.out.println("ABBBB SAAB THIK HAAI ++++++++++++"+ teacher_name);

    }





}
