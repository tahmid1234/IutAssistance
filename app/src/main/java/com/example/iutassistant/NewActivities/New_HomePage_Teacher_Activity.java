package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.R;
import com.example.iutassistant.SingleTone.UserInfoSharedPreferenceSingleTone;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class New_HomePage_Teacher_Activity extends AppCompatActivity implements View.OnClickListener {

    CardView classInfo,projects,announcement,attendence;
    SharedPreferences userInfoCheck,logInSTate;
    String uid;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__home_page__teacher);

        classInfo = findViewById(R.id.classInfoId_teacher);
        projects = findViewById(R.id.projectId_teacher);
        attendence = findViewById(R.id.attendenceId_teacher);
        announcement = findViewById(R.id.announcementId_teacher);
        userInfoCheck=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        logInSTate= getSharedPreferences(Constant.USER_LOGIN_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println(uid+" dskhgfksjhdjkghsdjkhjs");
        if(!(userInfoCheck.getBoolean(Constant.user_exists_preference,false)))
        {
            UserInfoSharedPreferenceSingleTone.storeUserInfo(userInfoCheck,logInSTate,"Teachers",database,ref,uid);
        }

        projects.setOnClickListener(this);
        attendence.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.projectId_teacher){
            Intent intent = new Intent(getApplicationContext(), TeacherSide_Project_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
//        else if(view.getId() == R.id.attendenceId_teacher){
//        }

    }
}
