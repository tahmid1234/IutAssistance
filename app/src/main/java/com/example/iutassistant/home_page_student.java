package com.example.iutassistant;

import android.content.Intent;
import android.os.Bundle;

import com.example.iutassistant.AdapterClasses.PostAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

//todo: gmnhj,khujk,jlk

public class home_page_student extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;

    private ImageView profileimageview, moreimageview,message;
    private Button postbutton;
    private EditText postedit;
    private ListView postList;
    DatabaseReference databaseReference,dbNameFechingRef;
    String key1;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       databaseReference=FirebaseDatabase.getInstance().getReference("Post");
       // setDatabase();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        moreimageview = findViewById(R.id.moreid);
        postList = findViewById(R.id.postList);
        getData();
        moreimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShowAttendance.class));

            }
        });





        postbutton = findViewById(R.id.postid);
        postedit = findViewById(R.id.posteditid);
        profileimageview = findViewById(R.id.profile_id);
        databaseReference = FirebaseDatabase.getInstance().getReference("Post");

        profileimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });
        postbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                saveData();
                getData();
            }
        });
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
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println(uid);
        key1=databaseReference.push().getKey();
        dbNameFechingRef=FirebaseDatabase.getInstance().getReference().child("Students").child(uid);



        dbNameFechingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        System.out.println("I will tell you all about when i see you again,see you again");
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        System.out.println("name");
                        String poster_name = String.valueOf(dataSnapshot.child("name").getValue());
                        System.out.println(uid);
                        String post = postedit.getText().toString().trim();
                        Post post1 = new Post(poster_name, post);
                        key1 = databaseReference.push().getKey() + uid;
                        databaseReference.child(key1).setValue(post1);
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


    public void getData() {

        final ArrayList<Post> postArrayList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("Post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postArrayList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Post post1 = dataSnapshot1.getValue(Post.class);

                    postArrayList.add(post1);

                    System.out.println(post1.getPost() + "................................");

                }

                Collections.reverse(postArrayList);
                PostAdapter postAdapter = new PostAdapter(home_page_student.this, postArrayList);
               //  ListView postList;
               // postList= findViewById(R.id.postList);
                postList.setAdapter(postAdapter);
                Toast.makeText(getApplicationContext(), "Post sent222", Toast.LENGTH_LONG).show();
                System.out.println("OKAY");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
