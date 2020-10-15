package com.example.iutassistant.Acitivities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.iutassistant.Model.User;
import com.example.iutassistant.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class TeachersHomePage extends AppCompatActivity


        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;



    private ImageView profileimageview, classResheduling, assignment_img, quiz_img;
    Button courses, moreimageview;
    private Button postbutton, profileBtn;
    Spinner section, course;
    // private EditText postedit;
    // private ListView postList;
    DatabaseReference databaseReference, dbNameFechingRef, courseDatabase;
    String key1;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseReference = FirebaseDatabase.getInstance().getReference("Post");



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        course = findViewById(R.id.crs);
        section = findViewById((R.id.sec));

        moreimageview = findViewById(R.id.moreid);
        //  postList = findViewById(R.id.postList);
        //courses=findViewById(R.id.courseSelection);
        postbutton = findViewById(R.id.postid);
        //  postedit = findViewById(R.id.posteditid);
        profileimageview = findViewById(R.id.profile_id);
        //  getData();


        quiz_img = findViewById(R.id.quizBtn);
        classResheduling = findViewById(R.id.classUpdateBtn);
        assignment_img = findViewById(R.id.assignmentbtn);
        quiz_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Quiz Class a jaitese");
                startActivity(new Intent(getApplicationContext(), QuizDeclarationActivity.class));
            }
        });
        classResheduling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ClassResheduleDeclarationActivity.class));
            }
        });

        assignment_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AssignmentDeclarationActivity.class));
            }
        });


        //databaseReference = FirebaseDatabase.getInstance().getReference("Post");

        //courseRegistration();

        moreimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getData();
                startActivity(new Intent(getApplicationContext(), CourseSelection.class));
            }
        });

        profileimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

       // addItemsOnSextion();
       // addItemsOnCourses();





       /* quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   saveData();
              //  getData();
            }
        });*/
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





  /*  public void addItemsOnSextion() {

        section = (Spinner) findViewById(R.id.sec);
        section.setPrompt("Section") ;
        courseDatabase = FirebaseDatabase.getInstance().getReference("University/IUT");
        final List<String> list = new ArrayList<String>();

        courseDatabase.addValueEventListener(new ValueEventListener() {
            {
                System.out.println("HOI ns krno");
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                System.out.println("HOI  krno");
                // for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                //Loop 1 to go through all the child nodes of users
                for (DataSnapshot crsSnapshot : dataSnapshot.child("SECTION").getChildren()) {
                    //loop 2 to go through all the child nodes of books node
                    String seckey = crsSnapshot.getKey();
                    String secValue = String.valueOf(crsSnapshot.getValue());
                    list.add(seckey);

                    System.out.println(seckey + "  ^^^^^^ " + secValue);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.add("");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        section.setAdapter(dataAdapter);


    }

    public void addItemsOnCourses() {

        course = (Spinner) findViewById(R.id.crs);
        section= (Spinner) findViewById(R.id.sec);

        course.setPrompt("Courses");

        final List<String> list = new ArrayList<String>();
       // list.add("Courses");
        final String[] path = {""};
        courseDatabase=FirebaseDatabase.getInstance().getReference("University/IUT");
        final ArrayList<String> statesArrayList= new ArrayList<>();
        final String[] keyList={""};

        courseDatabase.addValueEventListener(new ValueEventListener() {
            {System.out.println("HOI ns krno");}
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
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

    public String nameT;
    void courseRegistration(){
       // moreimageview=(Button)findViewById(R.id.SelectBtn);

        System.out.println(section+"    section  ");





        moreimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                final String  sec=section.getSelectedItem().toString();
                final String crs=course.getSelectedItem().toString();
                if(TextUtils.isEmpty(sec) || TextUtils.isEmpty(crs) ) {
                    Toast.makeText(getApplicationContext(), "Please choose course and section first", Toast.LENGTH_LONG).show();

                    return;
                }




                System.out.println(sec+"    ****section  "+crs);
                dbNameFechingRef= FirebaseDatabase.getInstance().getReference("Teachers").child(uid);

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

                                FirebaseDatabase.getInstance().getReference("University/IUT").child("Section_assigned_Teacher").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(sec).setValue(crs);

                                FirebaseDatabase.getInstance().getReference("University/IUT").child("Courses_assigned_Teacher").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(crs).setValue(sec);


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



    }*/


}
