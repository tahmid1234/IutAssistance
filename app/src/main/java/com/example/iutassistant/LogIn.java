package com.example.iutassistant;

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

    SharedPreferences sp,spId; //sp is going to be used to keep users logged in
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

        sp = getSharedPreferences("signIn",MODE_PRIVATE);

        editId=  getSharedPreferences(MY_PREFERENCE_KEY,MODE_PRIVATE);
        //dataRetriver=getSharedPreferences(MY_PREFERENCE_KEY,MODE_PRIVATE);
      /*  if(sp.getBoolean("logged",false)){
            prefUid = editId.getString("UserID","No Id Defined");
            System.out.println(prefUid+" uid before check prof");
            checkProfession(prefUid);
            finish();
        }*/

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
                                   dbFetch=FirebaseDatabase.getInstance().getReference("User").child(uid);

                                    FirebaseMessaging.getInstance().subscribeToTopic(uid+"Request");


                                   dbFetch.addValueEventListener(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                           String prof =String.valueOf(dataSnapshot.child("profession").getValue());
                                           Toast.makeText(getApplicationContext(), prof, Toast.LENGTH_LONG).show();
                                           if(prof.equals("Students")) {
                                               startActivity(new Intent(getApplicationContext(), SectionCreation.class));
                                               sp.edit().putBoolean("logged",true).apply();
                                               System.out.println(sp+"  if sp");
                                               //String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                               editId.edit().putString("UserID",FirebaseAuth.getInstance().getCurrentUser().getUid()).apply();
                                               //spid=spid.getString("Id",id);
                                               contextLogin=LogIn.this;
                                               finish();

                                           }
                                           else {
                                               startActivity(new Intent(getApplicationContext(), TeachersHomePage.class));
                                               sp.edit().putBoolean("logged",true).apply();
                                               editId.edit().putString("UserID",FirebaseAuth.getInstance().getCurrentUser().getUid()).apply();
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

  /*  public void checkSectionAcceptance(String prefUid){
        FirebaseDatabase database = FirebaseDatabase.getInstance();




        DatabaseReference ref = database.getReference().child("University/IUT/Students").child(prefUid);

        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String sectionStatus=String.valueOf(dataSnapshot.child("sec").getValue());
                    System.out.println(sectionStatus+" section"+dataSnapshot);
                    if(sectionStatus.equals("PENDING")||sectionStatus.equals("REJECTED")||sectionStatus.equals("REQUESTED"))
                    startActivity(new Intent(getApplicationContext(),SectionCreation.class));
                    else
                        startActivity(new Intent(getApplicationContext(),home_page_student.class));
                }
                else {


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);

    }

    public void checkProfession(String prefUid){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

         String  ab=editId.getString("UserID","No Id Defined");


        DatabaseReference ref = database.getReference().child("User").child(prefUid);

        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String professionStatus=String.valueOf(dataSnapshot.child("profession").getValue());
                    System.out.println(professionStatus+" section"+dataSnapshot);
                    if(professionStatus.equals("Teachers"))
                        startActivity(new Intent(getApplicationContext(),TeachersHomePage.class));
                    else
                       checkSectionAcceptance(editId.getString("UserID","No Id Defined"));
                }
                else {


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);
    }

    public void goToWelcome(){
        startActivity(new Intent(getApplicationContext(),Welcome.class));
    }*/




}