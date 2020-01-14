package com.example.iutassistant;

import android.content.Intent;
import android.os.Bundle;

import com.example.iutassistant.AdapterClasses.PostAdapter;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.iutassistant.AdapterClasses.Request_Adapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//todo: gmnhj,khujk,jlk

public class home_page_student extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;

    private ImageView profileimageview, showAttendancePercentage,message,quiz_img,routin_img,assignment_img,project_img;
    private Button postbutton;
    private EditText postedit;
    private ListView postList, requestListView;
    Spinner postSpinner;
    DatabaseReference databaseReference,dbNameFechingRef;
    String key1,poster_dept,poster_prog;
    List<String> list = new ArrayList<String>();
    User user;
    public static int flag=0;
    String requestedSecName;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

     String postArea;

    ArrayList<RequestInfo> requestInfoList =new ArrayList<RequestInfo>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       databaseReference=FirebaseDatabase.getInstance().getReference("Post");
       // setDatabase();
        postSpinner=findViewById(R.id.post_spinner_id_1);

        requestListView =findViewById(R.id.requestList);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        showAttendancePercentage = findViewById(R.id.moreid);
        postList = findViewById(R.id.postList);
      // getData();
        showAttendancePercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Attendance a jaitese ***");
                AttendanceCalculation attendanceCalculation=new AttendanceCalculation();
                attendanceCalculation.clearPercentageLists();
                startActivity(new Intent(getApplicationContext(), ShowAttendance.class));

            }
        });

        addSpinnerList();





        postbutton = findViewById(R.id.postid);
        postedit = findViewById(R.id.posteditid);
        profileimageview = findViewById(R.id.profile_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Post");

        quiz_img=findViewById(R.id.quiz_img);
        project_img=findViewById(R.id.projects);
        routin_img=findViewById(R.id.routine);
        assignment_img=findViewById(R.id.assignment_img);
        assignment_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=3;
                startActivity(new Intent(getApplicationContext(), AnnoncementReminder.class));
            }
        });
        routin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=2;
                startActivity(new Intent(getApplicationContext(), AnnoncementReminder.class));
            }
        });

        quiz_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                startActivity(new Intent(getApplicationContext(), AnnoncementReminder.class));
            }
        });

        profileimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });
        project_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Projects.class));
            }
        });
        postbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   getData();
                String post_sent=postedit.getText().toString().trim();
                if(TextUtils.isEmpty(post_sent)) {
                    Toast.makeText(getApplicationContext(), "Please write something to post", Toast.LENGTH_LONG).show();

                    return;
                }
                postArea=postSpinner.getSelectedItem().toString().trim();
                saveData();
                //postArrayList.clear();
                getData(postArea);
            }
        });

        //getRequestList();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

    public void saveData() {
        //  String post = postedit.getText().toString().trim();

        // final String[] poster_name = new String[1];

        System.out.println(uid+"save button");

        key1=databaseReference.push().getKey();

        dbNameFechingRef=FirebaseDatabase.getInstance().getReference().child("User").child(uid);



        dbNameFechingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        System.out.println("I will tell you all about when i see you again,see you again");

                        System.out.println("name");
                        String poster_name = String.valueOf(dataSnapshot.child("name").getValue());
                        String poster_id=String.valueOf(dataSnapshot.child("id").getValue());
                        System.out.println(uid);
                        String post = postedit.getText().toString().trim();
                        Post post1 = new Post(poster_name, post,poster_id,postArea);
                     //   key1 = databaseReference.push().getKey() + uid;
                        FirebaseDatabase.getInstance().getReference("University/IUT/POST").child(postArea).child(key1).setValue(post1);
                        //System.out.println("University"+user.getUni());
                        Toast.makeText(getApplicationContext(), "Post sent", Toast.LENGTH_LONG).show();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.println("It been a long without my friend");
        System.out.println(uid);
        // String key = databaseReference.push().getKey();

        // Post post1 = new Post(poster_name[0], post);


        //databaseReference.child(uid).setValue(post1);
        //Toast.makeText(getApplicationContext(), "Post send", Toast.LENGTH_LONG).show();




    }


    public void getData(String postAreaCheck) {

        //postArrayList.clear();
        System.out.println(poster_dept+" poster er kahini ar ki"+requestedSecName);
        if(!postAreaCheck.equals("IUT"))
        getPostDetails("IUT");
        if(!postAreaCheck.equals(poster_dept))
        getPostDetails(poster_dept);
        if(!postAreaCheck.equals(requestedSecName))
        getPostDetails(requestedSecName);
//        getPostDetails("DoneFetchingData");




    }
    public int getFlag(){
        return flag;
    }

    public void addSpinnerList(){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        dbNameFechingRef=FirebaseDatabase.getInstance().getReference().child("University/IUT/Students").child(uid);



        dbNameFechingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                list.add("IUT");
                if (dataSnapshot.exists()) {
                   // for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                        poster_dept = String.valueOf(dataSnapshot.child("dept").getValue());

                        requestedSecName=String.valueOf(dataSnapshot.child("sec").getValue());

                        System.out.println(requestedSecName+" eta retrivimh");

                        list.add(poster_dept);
                        list.add(requestedSecName);

                        getData("Initial posts");

                        getRequestList();
                      //  break;


                   // }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        list.add("IUT");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postSpinner.setAdapter(dataAdapter);

    }

    void getRequestList(){


        System.out.println(requestedSecName+" eta applying");

        FirebaseDatabase.getInstance().getReference("University/IUT/REQUEST").child(requestedSecName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                requestInfoList.clear();
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                     String requestedSid=String.valueOf(snapshot.getKey());
                     String requestedUid=String.valueOf(dataSnapshot.child(requestedSid).child("id").getValue());
                        System.out.println(requestedSid+" eta applying"+requestedUid);
                     RequestInfo requestInfo=new RequestInfo(requestedSid,requestedUid,requestedSecName);
                        requestInfoList.add(requestInfo);

                    }
                }

                Request_Adapter request_adapter=new Request_Adapter(home_page_student.this,requestInfoList);
                requestListView.setAdapter(request_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    final ArrayList<Post> postArrayList = new ArrayList<>();

    void getPostDetails(final String postAreaIn){

            postArrayList.clear();
        System.out.println(postAreaIn+" post Area In");

        FirebaseDatabase.getInstance().getReference("University/IUT/POST").child(postAreaIn).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Post post1 = dataSnapshot1.getValue(Post.class);
                    System.out.println("post dekhi"+String.valueOf(dataSnapshot1.child("post").getValue())+" "+String.valueOf(dataSnapshot1.child("postArea").getValue()));
                    postArrayList.add(post1);

                    System.out.println(post1.getPost() + "................................");

                }

                Collections.reverse(postArrayList);
               // if(postAreaIn.equals("DoneFetchingData")){
                PostAdapter postAdapter = new PostAdapter(home_page_student.this, postArrayList);
                //  ListView postList;
                // postList= findViewById(R.id.postList);
                postList.setAdapter(postAdapter);//}
                // Toast.makeText(getApplicationContext(), "Post sent222", Toast.LENGTH_LONG).show();
                System.out.println("OKAY");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
