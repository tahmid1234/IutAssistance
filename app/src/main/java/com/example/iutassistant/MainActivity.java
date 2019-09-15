package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText enteredPassword,confirmPassWord,emailAdd, inputName, inputDept, inputUni, inputProg,inputSec,inputId;
    Button signUp;
    RadioButton radioStudent,radioTeacher;
    DatabaseReference databaseReference,dbref;
    String proffesion ="";
    private FirebaseAuth mAuth;
    private static String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        dbref=FirebaseDatabase.getInstance().getReference("University");



        enteredPassword=(EditText)findViewById(R.id.enteredPassword);
        confirmPassWord=(EditText)findViewById(R.id.cPassword);
        emailAdd=(EditText)findViewById(R.id.emailAdd);
        signUp=(Button)findViewById(R.id.SignUp);
        inputName =(EditText)findViewById(R.id.name);
        inputDept =(EditText)findViewById(R.id.dept);
        inputProg =(EditText)findViewById(R.id.prog);
        inputUni =(EditText)findViewById(R.id.university);
        inputSec=(EditText)findViewById(R.id.sec) ;
        inputId=(EditText)findViewById(R.id.id);
        radioStudent=(RadioButton)findViewById(R.id.student);
        radioTeacher=(RadioButton)findViewById(R.id.teacher);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailAdd.getText().toString().trim();
                String password=enteredPassword.getText().toString().trim();
                String conPassword=confirmPassWord.getText().toString().trim();
                final String name=inputName.getText().toString().trim();
                final String dept=inputDept.getText().toString().trim();
                final String prog=inputProg.getText().toString().trim();
                final String sec =inputSec.getText().toString().trim();
                final String id=inputId.getText().toString();
                final String uni=inputUni.getText().toString().trim();

                if(radioStudent.isChecked()){
                    proffesion="Students";

                }
                else {
                    proffesion = "Teachers";

                }

                if(TextUtils.isEmpty(email)){

                    Toast.makeText(MainActivity.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Please enter  password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(conPassword)){
                    Toast.makeText(MainActivity.this, "Please confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(MainActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(id)){
                    Toast.makeText(MainActivity.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(dept)){
                    Toast.makeText(MainActivity.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
                    return;
                }



                if(TextUtils.isEmpty(uni)){
                    Toast.makeText(MainActivity.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
                    return;
                }



                if(TextUtils.isEmpty(proffesion)){
                    Toast.makeText(MainActivity.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (password.equals(conPassword)){
                    System.out.println(email+"***************"+password);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        User information=new User(id,name,sec,prog,dept,uni,proffesion);

                                        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        System.out.println("Laal");
                                        System.out.println(uid);
                                        setUid(uid);


                                        dbref.child(uni).setValue(uid);
                                        FirebaseDatabase.getInstance().getReference(proffesion).child(uid).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                System.out.println("Profession"+proffesion);
                                                if(proffesion.equals("Students"))
                                                startActivity(new Intent(getApplicationContext(),home_page_student.class));
                                                else
                                                startActivity(new Intent(getApplicationContext(),Information.class));
                                                Toast.makeText(MainActivity.this, "Registration", Toast.LENGTH_SHORT).show();
                                                setUid(uid);
                                            }
                                        });



                                    } else {
                                        Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });


                }

            }
        });

    }

    public void setUid(String uid) {
        this.uid = uid;
        System.out.println("Laal 1");
        System.out.println(uid);

    }

    public String getUid() {
        System.out.println("Laal2");
        System.out.println(uid);
        return uid;}

    /*   @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }*/




}
