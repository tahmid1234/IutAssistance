package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText enteredPassword,confirmPassWord,emailAdd, inputName,  inputUni,inputId;
    Button signUp;
    Spinner inputProg,inputSec;
    AutoCompleteTextView inputDept;
    RadioButton radioStudent,radioTeacher;

    DatabaseReference databaseReference,dbref,deptDatabase;

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



        enteredPassword=(EditText)findViewById(R.id.enteredPassword);
        confirmPassWord=(EditText)findViewById(R.id.cPassword);
        emailAdd=(EditText)findViewById(R.id.emailAdd);
        signUp=(Button)findViewById(R.id.SignUp);
        inputName =(EditText)findViewById(R.id.name);
        inputDept =(AutoCompleteTextView) findViewById(R.id.dept);
       // inputProg =(Spinner) findViewById(R.id.prog);
        inputUni =(EditText)findViewById(R.id.university);
        //inputSec=(Spinner) findViewById(R.id.sec) ;
        inputId=(EditText)findViewById(R.id.id);
       // secText=(TextView)findViewById(R.id.secText);
       // progText=(TextView)findViewById(R.id.progText);

            //inputSec.setVisibility(View.INVISIBLE);
           // inputProg.setVisibility(View.INVISIBLE);
           // secText.setVisibility(View.INVISIBLE);
           // progText.setVisibility(View.INVISIBLE);





        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailAdd.getText().toString().trim();
                String password=enteredPassword.getText().toString().trim();
                String conPassword=confirmPassWord.getText().toString().trim();
                final String name=inputName.getText().toString().trim();
                final String lowerDept=inputDept.getText().toString();
                final  String dept=lowerDept.toUpperCase();
                //final String prog=inputProg.getSelectedItem().toString();
               // final String sec =inputSec.getSelectedItem().toString();
                final String id=inputId.getText().toString();


                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(conPassword)|| TextUtils.isEmpty(id)||TextUtils.isEmpty(name)||TextUtils.isEmpty(dept)){

                    Toast.makeText(MainActivity.this, "Please fill the form properly", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(MainActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.equals(conPassword)){



                    System.out.println(email+"***************"+password+"^^^^^^^^^"+dept);
                    FirebaseDatabase.getInstance().getReference("University").child("IUT").child("DEPT").child(dept).setValue(dept);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        System.out.println("professionnnnnnnnnnnnnnnnnnnnnnn");
                                        final String university= universitySelectingClass.getUid();
                                        final String profession=universitySelectingClass.getProffesion();
                                        final User information;
                                       // if (profession=="Students"){
                                            //information=new User(id,name,sec,prog,dept,profession,university);
                                         // }
                                        //else
                                            information=new User(id,name,dept,profession,university);

                                        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                        setUid(uid);


                                        //dbref.child(university).child(dept).child(prog).child(sec).child("UID").setValue(uid);//hierarchy of university to student uid is set
                                        FirebaseDatabase.getInstance().getReference("University/IUT").child(profession).child(uid).setValue(information);
                                        //FirebaseDatabase.getInstance().getReference("University").child("IUT").child("StudentsInSection").child("id").setValue(uid);
                                        //databaseReference.child("profession").child(uid).setValue("");
                                        FirebaseDatabase.getInstance().getReference("User").child(uid).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {



                                                if(profession.equals("Students")){
                                                    FirebaseDatabase.getInstance().getReference("University/IUT").child(profession).child(uid).child("sec").setValue("PENDING");

                                                   // System.out.println(sec+"HOItese naki ***********************************"+id+"   "+timeStamp);
                                                  //etar kaaj kora lagbe pore &&&&&&&&&&&&&&&&7->
                                                   // FirebaseDatabase.getInstance().getReference("University/IUT/StudentsInSection").child(sec).child(id).setValue(uid);
                                                    startActivity(new Intent(getApplicationContext(), SectionCreation.class));}
                                                    else
                                                    startActivity(new Intent(getApplicationContext(), TeachersHomePage.class));


                                                //startActivity(new Intent(getApplicationContext(), LogIn.class));
                                                Toast.makeText(MainActivity.this, profession, Toast.LENGTH_SHORT).show();

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



    public void addItemsOnDept() {

        inputDept = (AutoCompleteTextView) findViewById(R.id.dept);
        deptDatabase=FirebaseDatabase.getInstance().getReference("University/IUT");
        final List<String> list = new ArrayList<String>();
        list.add("");
        deptDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot deptSnapshot : dataSnapshot.child("DEPT").getChildren()){

                    String deptKey = deptSnapshot.getKey();

                    list.add(deptKey);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
      //  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputDept.setThreshold(1);
        inputDept.setAdapter(dataAdapter);
    }




}
