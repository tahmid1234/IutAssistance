package com.example.iutassistant.NewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class New_Class_Info_Student extends AppCompatActivity {

    private ListView allCourses;

    DatabaseReference firebaseDatabase;
    SharedPreferences userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__class__info__student);


        allCourses = findViewById(R.id.allCoursesId_Student);
        userInfo=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        firebaseDatabase= FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Classes_Node).child(userInfo.getString(Constant.user_sec_preference,"N/A"));

    }

    //on data change
    public void getAssignedClassList(){

        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}