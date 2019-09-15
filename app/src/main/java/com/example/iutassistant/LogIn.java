package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LogIn extends AppCompatActivity {

    EditText enteredPassword, confirmPassWord, emailAdd;
    Button signUp, logIn;
    private FirebaseAuth mAuth;
    private static String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enteredPassword = (EditText) findViewById(R.id.enteredPassword);
        emailAdd = (EditText) findViewById(R.id.emailAdd);
        signUp = (Button) findViewById(R.id.SignUp);
        logIn = (Button) findViewById(R.id.LogIn);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailAdd.getText().toString().trim();
                String password = enteredPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(LogIn.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LogIn.this, "Please enter  password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                    setUid(uid);
                                    startActivity(new Intent(getApplicationContext(),home_page_student.class));

                                   // startActivity(new Intent(getApplicationContext(), Welcome.class));
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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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


}