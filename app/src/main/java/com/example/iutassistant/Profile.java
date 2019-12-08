package com.example.iutassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
    TextView name,id,uni,prof,dept,bsc_text,bsc_id_text;
    private Spinner residential_status_spinner,blood_group_spinner,hall_spinner;

    DatabaseReference dbprofiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        System.out.println("ProfileInfo ************");



        residential_status_spinner = findViewById(R.id.res_status_id);
        blood_group_spinner = findViewById(R.id.blood_id);
        hall_spinner = findViewById(R.id.hall_id);



        final ArrayList<String> hall_of_residence = new ArrayList<>();
        hall_of_residence.add("South Hall of Residence");
        hall_of_residence.add("North Hall of Residence");

        final ArrayAdapter<String> hall_adapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,hall_of_residence);
        hall_spinner.setAdapter(hall_adapter);





        final ArrayList<String> residential_status = new ArrayList<>();
        residential_status.add("Residential");
        residential_status.add("Non-Residential");

        final ArrayAdapter<String> residential_status_adapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,residential_status);
        residential_status_spinner.setAdapter(residential_status_adapter);







        final ArrayList<String> blood_group = new ArrayList<>();
        blood_group.add("A-positive");
        blood_group.add("A-negative");
        blood_group.add("B-positive");
        blood_group.add("B-negative");
        blood_group.add("AB-positive");
        blood_group.add("AB-negative");
        blood_group.add("O-positive");
        blood_group.add("O-negative");

        final ArrayAdapter<String> blood_group_adapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,blood_group);
        blood_group_spinner.setAdapter(blood_group_adapter);



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
                       // prof=(TextView)findViewById(R.id.profText);
                        dept=(TextView)findViewById(R.id.deptText);
                       // bsc_text=(TextView)findViewById(R.id.progText);

                        name.setText(names);
                        id.setText(ids);


                       // uni.setText(unis);
                       // prof.setText(professions);
                        dept.setText(depts);
                        System.out.println("Yooyooo");


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
