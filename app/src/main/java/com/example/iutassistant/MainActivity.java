package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText enteredPassword,confirmPassWord,emailAdd, inputName,  inputUni,inputId;
    Button signUp;
    Spinner inputDept,inputProg,inputSec;
    RadioButton radioStudent,radioTeacher;
    DatabaseReference databaseReference,dbref;
    TextView secText,progText;

    private FirebaseAuth mAuth;
    private static String uid;
    UniversitySelectingClass universitySelectingClass=new UniversitySelectingClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //databaseReference= FirebaseDatabase.getInstance().getReference("Profession");
        dbref=FirebaseDatabase.getInstance().getReference("University");
        String profession=universitySelectingClass.getProffesion();
        addItemsOnDept();
        addItemsOnProg();
        addItemsOnSec();


        enteredPassword=(EditText)findViewById(R.id.enteredPassword);
        confirmPassWord=(EditText)findViewById(R.id.cPassword);
        emailAdd=(EditText)findViewById(R.id.emailAdd);
        signUp=(Button)findViewById(R.id.SignUp);
        inputName =(EditText)findViewById(R.id.name);
        inputDept =(Spinner) findViewById(R.id.dept);
        inputProg =(Spinner) findViewById(R.id.prog);
        inputUni =(EditText)findViewById(R.id.university);
        inputSec=(Spinner) findViewById(R.id.sec) ;
        inputId=(EditText)findViewById(R.id.id);
        secText=(TextView)findViewById(R.id.secText);
        progText=(TextView)findViewById(R.id.progText);
        System.out.println("PROFESSION"+profession);
        if(profession=="Teachers"){
            inputSec.setVisibility(View.INVISIBLE);
            inputProg.setVisibility(View.INVISIBLE);
            secText.setVisibility(View.INVISIBLE);
            progText.setVisibility(View.INVISIBLE);

        }



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailAdd.getText().toString().trim();
                String password=enteredPassword.getText().toString().trim();
                String conPassword=confirmPassWord.getText().toString().trim();
                final String name=inputName.getText().toString().trim();
                final String dept=inputDept.getSelectedItem().toString();
                final String prog=inputProg.getSelectedItem().toString();
                final String sec =inputSec.getSelectedItem().toString();
                final String id=inputId.getText().toString();




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






               /* if(TextUtils.isEmpty(proffesion)){
                    Toast.makeText(MainActivity.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
                    return;
                }*/



                if (password.equals(conPassword)){
                    System.out.println(email+"***************"+password);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        System.out.println("professionnnnnnnnnnnnnnnnnnnnnnn");
                                        final String university= universitySelectingClass.getUid();
                                        final String profession=universitySelectingClass.getProffesion();
                                        final User information;
                                        if (profession=="Students"){
                                            information=new User(id,name,sec,prog,dept,profession,university);
                                          }
                                        else
                                            information=new User(id,name,dept,profession,university);

                                        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        System.out.println("Laalllllllllllllllll profession");
                                        System.out.println(profession);
                                        setUid(uid);


                                        //dbref.child(university).child(dept).child(prog).child(sec).child("UID").setValue(uid);//hierarchy of university to student uid is set
                                        FirebaseDatabase.getInstance().getReference(profession).child(uid).child("name").setValue(name);
                                        //FirebaseDatabase.getInstance().getReference("University").child("IUT").child("StudentsInSection").child("id").setValue(uid);
                                        //databaseReference.child("profession").child(uid).setValue("");
                                        FirebaseDatabase.getInstance().getReference("User").child(uid).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                System.out.println("okay1");
                                                System.out.println(profession);
                                                System.out.println("okay2");

                                                System.out.println(profession);
                                                if(profession.equals("Students")){
                                                    User partialInfo;
                                                    partialInfo=new User(uid,id);

                                                    String timeStamp =new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                                                    System.out.println(sec+"HOItese naki ***********************************"+id+"   "+timeStamp);
                                                    FirebaseDatabase.getInstance().getReference("University/IUT/StudentsInSection").child(sec).child(id).setValue(uid);
                                                    startActivity(new Intent(getApplicationContext(), home_page_student.class));}
                                                    else
                                                    startActivity(new Intent(getApplicationContext(), TeachersHomePage.class));

                                                    System.out.println("Profession 2222222222222222"+profession);
                                                //startActivity(new Intent(getApplicationContext(), LogIn.class));
                                                Toast.makeText(MainActivity.this, profession, Toast.LENGTH_SHORT).show();
                                                System.out.println(profession);
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

    public void addItemsOnDept() {

        inputDept = (Spinner) findViewById(R.id.dept);
        List<String> list = new ArrayList<String>();
        list.add("CSE");
        list.add("EEE");
        list.add("MCE");
        list.add("BTM");
        list.add("CEE");
        list.add("TVE");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputDept.setAdapter(dataAdapter);
    }
    public void addItemsOnSec() {

        inputSec = (Spinner) findViewById(R.id.sec);
        List<String> list = new ArrayList<String>();
        list.add("SWE17  1");
        list.add("CSE17  1");
        list.add("EEE17  1");
        list.add("MCE17  1");
        list.add("BTM17  1");
        list.add("CEE17  1");
        list.add("SWE17  2");
        list.add("CSE17  2");
        list.add("EEE17  2");
        list.add("MCE17  2");
        list.add("BTM17  2");
        list.add("CEE17  2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSec.setAdapter(dataAdapter);
    }
    public void addItemsOnProg() {

        inputProg = (Spinner) findViewById(R.id.prog);
        List<String> list = new ArrayList<String>();
        list.add("SWE");
        list.add("CSE");
        list.add("EEE");
        list.add("BTM");
        list.add("TVE");
        list.add("MCE");
        list.add("CEE");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputProg.setAdapter(dataAdapter);
    }




}
