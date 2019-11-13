package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseSelection extends AppCompatActivity {

    public Spinner university,course,section;
    DatabaseReference courseDatabase,dbNameFechingRef;
    private Button selectBTN,attendanceBTN;
    public String sec,crs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);
        course = (Spinner) findViewById(R.id.crs);
        section= (Spinner) findViewById(R.id.sec);
        addItemsOnCourses();
        addItemsOnSextion();

       selectButton();
    }

    public void addItemsOnCourses() {

        course = (Spinner) findViewById(R.id.crs);
        section= (Spinner) findViewById(R.id.sec);


         System.out.println(sec+" &&&&&&&&&&&&&&&&&&&&   "+crs);
        final List<String> list = new ArrayList<String>();
        final String[] path = {""};
        courseDatabase=FirebaseDatabase.getInstance().getReference("University/IUT");
        final ArrayList<String> statesArrayList= new ArrayList<>();
        final String[] keyList={""};

        courseDatabase.addValueEventListener(new ValueEventListener() {
            {System.out.println("HOI ns krno");}
                                                       @Override
                                                       public void onDataChange(DataSnapshot dataSnapshot) {
                                                           System.out.println("HOI  krno");
                                                          // for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                                                               //Loop 1 to go through all the child nodes of users
                                                               for(DataSnapshot crsSnapshot : dataSnapshot.child("COURSES").getChildren()){
                                                                   //loop 2 to go through all the child nodes of books node
                                                                   String crskey = crsSnapshot.getKey();
                                                                   String crsValue = String.valueOf(crsSnapshot.getValue());
                                                                   list.add(crskey);
                                                                   System.out.println(crskey+ "  &&&&&&&&&& "+crsValue);

                                                           }
                                                       }

                                                       @Override
                                                       public void onCancelled(DatabaseError databaseError) {

                                                       }
                                                   });


                    list.add("");

                    ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);


                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course.setAdapter(dataAdapter);
            }


    public void addItemsOnSextion() {

        section = (Spinner) findViewById(R.id.sec);
        courseDatabase=FirebaseDatabase.getInstance().getReference("University/IUT");
        final List<String> list = new ArrayList<String>();

        courseDatabase.addValueEventListener(new ValueEventListener() {
            {System.out.println("HOI ns krno");}
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println("HOI  krno");
                // for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                //Loop 1 to go through all the child nodes of users
                for(DataSnapshot crsSnapshot : dataSnapshot.child("SECTION").getChildren()){
                    //loop 2 to go through all the child nodes of books node
                    String seckey = crsSnapshot.getKey();
                    String secValue = String.valueOf(crsSnapshot.getValue());
                    list.add(seckey);

                    System.out.println(seckey+ "  ^^^^^^ "+secValue);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.add("");

        ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        section.setAdapter(dataAdapter);
    }
    public String nameT;
     void selectButton(){
        selectBTN=(Button)findViewById(R.id.SelectBtn);

         System.out.println(section+"    section  ");





         selectBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                final String  sec=section.getSelectedItem().toString();
                final String crs=course.getSelectedItem().toString();




                System.out.println(sec+"    ****section  "+crs);
                dbNameFechingRef= FirebaseDatabase.getInstance().getReference().child("Teachers").child(uid);

                System.out.println(uid+" ******uid");

                dbNameFechingRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        System.out.println(" check******uid");
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                System.out.println("I will tell you all about when i see you again,see you again");
                                //String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                System.out.println("name");
                                String name = String.valueOf(dataSnapshot.child("name").getValue());
                                System.out.println(name+" protidin");
                                nameT=name;
                                FirebaseDatabase.getInstance().getReference("University/IUT").child("TEACHES").child(sec).child(crs).setValue(name);
                                //  Toast.makeText(getApplicationContext(), "Post sent", Toast.LENGTH_LONG).show();

                            }

                        }






                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                System.out.println(nameT+" ************8");

               // startActivity(new Intent(getApplicationContext(), home_page_student.class));
               //
            }
        });

    }



}

