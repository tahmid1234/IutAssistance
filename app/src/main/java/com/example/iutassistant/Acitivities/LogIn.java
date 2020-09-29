package com.example.iutassistant.Acitivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.NewActivities.New_HomePage_Student_Activity;
import com.example.iutassistant.NewActivities.New_HomePage_Teacher_Activity;
import com.example.iutassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class LogIn extends AppCompatActivity {

    EditText enteredPassword, confirmPassWord, emailAdd;
    Button signUp, logIn,admin;
    private FirebaseAuth mAuth;
    private static String uid,prefUid;
    DatabaseReference dbFetch;
    public static final String MY_PREFERENCE_KEY = "my_preference_key";

    SharedPreferences sp,spId,userInfoCheck; //sp is going to be used to keep users logged in
    SharedPreferences editId;//= (SharedPreferences.Editor) getSharedPreferences(MY_PREFERENCE_KEY,MODE_PRIVATE);
    SharedPreferences dataRetriver;//=getSharedPreferences("IDVAL",MODE_PRIVATE);

    public static Context contextLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enteredPassword = (EditText) findViewById(R.id.enteredPassword);
        emailAdd = (EditText) findViewById(R.id.emailAdd);
        signUp = (Button) findViewById(R.id.SignUp);
        logIn = (Button) findViewById(R.id.LogIn);

        admin=(Button) findViewById(R.id.admin);

        sp = getSharedPreferences(Constant.USER_LOGIN_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        dataRetriver=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        userInfoCheck=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        Toast.makeText(LogIn.this,sp.getBoolean(Constant.user_login_state_shared_preference,false)+"", Toast.LENGTH_SHORT).show();
        if(sp.getBoolean(Constant.user_login_state_shared_preference,false)){
            checkUserInfo();
        }
        else{
            dataRetriver.edit().putBoolean(Constant.user_exists_preference, false).apply();
        }


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailAdd.getText().toString().trim();
                String password = enteredPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                    Toast.makeText(LogIn.this, "Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }



                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    System.out.println(uid+" hgfghf");
                                   dbFetch=FirebaseDatabase.getInstance().getReference("User").child(uid);

                                    FirebaseMessaging.getInstance().subscribeToTopic(uid+"Request");


                                   dbFetch.addValueEventListener(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                           String prof =String.valueOf(dataSnapshot.child("profession").getValue());
                                           Toast.makeText(getApplicationContext(), prof, Toast.LENGTH_LONG).show();
                                           dataRetriver.edit().putBoolean(Constant.user_exists_preference, false).apply();
                                           userInfoCheck.edit().putBoolean(Constant.user_exists_preference,false).apply();
                                           if(prof.equals("Students")) {
                                               GoToSectionCreation();

                                               sp.edit().putBoolean(Constant.user_login_state_shared_preference,true).apply();
                                               System.out.println(sp+"  if sp");
                                               //String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                               editId.edit().putString("UserID",FirebaseAuth.getInstance().getCurrentUser().getUid()).apply();
                                               //spid=spid.getString("Id",id);
                                               contextLogin=LogIn.this;
                                               finish();

                                           }
                                           else {
                                               GoToTeacherHomePage();
                                               sp.edit().putBoolean(Constant.user_login_state_shared_preference,true).apply();

                                               System.out.println(sp+"  else sp");
                                               contextLogin=LogIn.this;
                                               finish();

                                           }


                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError databaseError) {

                                       }
                                   });


                                } else {

                                    Toast.makeText(LogIn.this, "User not available", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UniversitySelectingClass.class));
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }



  public void checkUserInfo(){
      if(dataRetriver.getBoolean(Constant.user_exists_preference,false)){
          if(dataRetriver.getString(Constant.user_profession_preference,"Not Defined").equals("Students")){
            GoToStudentHomePage();
          }
          else
          {
              GoToTeacherHomePage();
          }
      }
      else
      {
          GoToSectionCreation();
      }
  }

  public void GoToStudentHomePage(){
      startActivity(new Intent(getApplicationContext(), New_HomePage_Student_Activity.class));
  }
    public void GoToTeacherHomePage(){
        startActivity(new Intent(getApplicationContext(), New_HomePage_Teacher_Activity.class));
    }
    public void GoToSectionCreation(){
        startActivity(new Intent(getApplicationContext(), SectionCreation.class));
    }



}