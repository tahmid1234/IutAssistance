package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.R;
import com.example.iutassistant.SingleTone.UserInfoSharedPreferenceSingleTone;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class New_HomePage_Student_Activity extends AppCompatActivity implements View.OnClickListener{

    private CardView classInfo,projects,invitation,request;

    private TextView numOfRequest;
    SharedPreferences userInfoCheck,logInSTate;
    String uid;
    FirebaseDatabase database;
    DatabaseReference ref;
    UserInfoSharedPreferenceSingleTone userInfoSharedPreferenceSingleTone=UserInfoSharedPreferenceSingleTone.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home_page_student);



        //card view id
        classInfo = findViewById(R.id.classInfoId);
        projects = findViewById(R.id.projectId);
        invitation = findViewById(R.id.invitationId);
        request = findViewById(R.id.requestId);

        //textview er id
        numOfRequest = findViewById(R.id.requestNumberId);
        userInfoCheck=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        logInSTate= getSharedPreferences(Constant.USER_LOGIN_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(!(userInfoCheck.getBoolean(Constant.user_exists_preference,false)))
        {
            UserInfoSharedPreferenceSingleTone.storeUserInfo(userInfoCheck,logInSTate,"Students",database,ref,uid);
        }

        classInfo.setOnClickListener(this);
        projects.setOnClickListener(this);


    }

    //project button
    @Override
    public void onClick(View view) {

//        if(view.getId()==R.id.classInfoId){
//            Intent intent = new Intent(getApplicationContext(), NewClassInfo.class);
//            startActivity(intent)/
//        }
        if(view.getId()==R.id.projectId){
            Intent intent = new Intent(getApplicationContext(), Student_Project_List_Activity.class);
            startActivity(intent);
        }

    }

   /* public void storeUserInfo(){

        database = FirebaseDatabase.getInstance();
        uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = database.getReference().child("University/IUT/Students").child(uid);
        userInfoCheck.edit().putString(Constant.uid_preference,uid).apply();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    userInfoCheck.edit().putString(Constant.username_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_name).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_sid_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_id).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_prog_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_programme).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_sec_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_sec).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_dept_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_dept).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_university_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_uni).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_profession_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_profession).getValue())).apply();
                    userInfoCheck.edit().putBoolean(Constant.user_exists_preference, true).apply();
                    logInSTate.edit().putBoolean(Constant.user_login_state_shared_preference,true).apply();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);




    }*/
}
