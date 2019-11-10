package com.example.iutassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private List<ProfileInfo> profileListInfo;
    TextView name,id,uni,prof,dept;

    DatabaseReference dbprofiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        System.out.println("ProfileInfo ************");

        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
  /*      Query query = FirebaseDatabase.getInstance().getReference("User")
                .orderByChild("id")
                .equalTo(uid);
        System.out.println(query+" query**********"+uid);
        */
        dbprofiles = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
        /*
        query.addValueEventListener(new ValueEventListener() {
*/
            dbprofiles.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String ids = String.valueOf(dataSnapshot.child("id").getValue());
                        String names = String.valueOf(dataSnapshot.child("name").getValue());
                        String depts = String.valueOf(dataSnapshot.child("dept").getValue());
                        String professions = String.valueOf(dataSnapshot.child("profession").getValue());
                        String unis = String.valueOf(dataSnapshot.child("uni").getValue());
                        name=(TextView)findViewById(R.id.nameText);
                        id=(TextView)findViewById(R.id.idText);
                        uni=(TextView)findViewById(R.id.uniText);
                        prof=(TextView)findViewById(R.id.profText);
                        dept=(TextView)findViewById(R.id.deptText);

                        name.setText(names);
                        id.setText(ids);
                        uni.setText(unis);
                        prof.setText(professions);
                        dept.setText(depts);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

/*    ValueEventListener valueEventListener = */
}
