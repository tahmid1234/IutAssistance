package com.example.iutassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    EditText enteredPassword,confirmPassWord,emailAdd;
    Button signUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        LogIn logIn=new LogIn();
        TextView t1=(TextView)findViewById(R.id.textView1);
        t1.setText(logIn.getUid());
        System.out.println("Lol");
        System.out.println(logIn.getUid());
       // System.out.println(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
}
