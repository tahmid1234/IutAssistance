package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Projects extends AppCompatActivity {

    Button task,request;
    AutoCompleteTextView supervisor,crs;
    EditText t_mate1,t_mate2,t_mate3,t_mate4,description,group_name,total_members;

    String ref="University/IUT";
     String sec;
    private String leader= FirebaseAuth.getInstance().getCurrentUser().getUid();

    DatabaseReference firebaseDatabase=FirebaseDatabase.getInstance().getReference(ref);

    SupervisionInfo supervisionInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        crs=(AutoCompleteTextView) findViewById(R.id.crs_value);
        supervisor =findViewById(R.id.supervisor_mail);

        group_name=findViewById(R.id.grp_name);
        t_mate1=findViewById(R.id.team_mate_1);
        t_mate2=findViewById(R.id.team_mate_2);
        t_mate3=findViewById(R.id.team_mate_3);
        t_mate4=findViewById(R.id.team_mate_4);
        description=findViewById(R.id.project_description);
        total_members=findViewById(R.id.members);

        request=findViewById(R.id.request_btn);
        task=findViewById(R.id.task);

        fetchSection();
        addCourse();
        addSupervisorMail();
        request();


    }
    public void fetchSection(){

        firebaseDatabase.child("Students").child(leader).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                sec= String.valueOf(dataSnapshot.child("sec").getValue());
                System.out.println(sec+"   add crs er age eta section of the group   ");

                System.out.println(sec+" add crs er pore  eta section of the group   ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void addCourse(){


        System.out.println(sec+"   eta section of the group   ");
        final List<String> list = new ArrayList<String>();
        list.add("");
        firebaseDatabase.child("TEACHES").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                if(dataSnapshot.exists()) {
                    for (DataSnapshot deptSnapshot : dataSnapshot.child(sec).getChildren()) {

                        String deptKey = deptSnapshot.getKey();

                        list.add(deptKey);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        //  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crs.setThreshold(1);
        crs.setAdapter(dataAdapter);
    }

    public void addSupervisorMail(){



        final List<String> list = new ArrayList<String>();
        list.add("");
        firebaseDatabase.child("Teachers").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String mail=String.valueOf(snapshot.child("email").getValue());
                        String dept=String.valueOf(snapshot.child("dept").getValue());
                        String deptKey = mail+"("+dept+")";
                        System.out.println(deptKey+" yuss");
                        list.add(deptKey);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        //  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supervisor.setThreshold(1);
        supervisor.setAdapter(dataAdapter);

    }

    public void request(){
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String grpName=group_name.getText().toString().trim();
                String mate1=t_mate1.getText().toString().trim();
                String mate2=t_mate2.getText().toString().trim();
                String mate3=t_mate3.getText().toString().trim();
                String mate4=t_mate4.getText().toString().trim();
                String desc=description.getText().toString().trim();
                String selectedCrs=crs.getText().toString().trim();
                selectedCrs=selectedCrs.toUpperCase();
                String mailId= supervisor.getText().toString().trim();
                String memebers=total_members.getText().toString().trim();
                FirebaseDatabase.getInstance().getReference(ref).child("COURSES").child(selectedCrs).setValue(selectedCrs);
                if(memebers.equals("2")){
                     supervisionInfo=new SupervisionInfo(grpName,memebers,mate1,leader,desc,selectedCrs);}
                else if(memebers.equals("3")){

                     supervisionInfo=new SupervisionInfo(grpName,memebers,mate1,mate2,leader,desc,selectedCrs);
                }
                else if(memebers.equals("4")){
                     supervisionInfo=new SupervisionInfo(grpName,memebers,mate1,mate2,mate3,leader,desc,selectedCrs);
                }
                else{
                     supervisionInfo=new SupervisionInfo(grpName,memebers,mate1,mate2,mate3,mate4,leader,desc,selectedCrs);
                }
                String mId=mailId.split("\\.")[0];

                System.out.println("eta tw request"+mailId);
                try {

                    mailId=mId;
                    System.out.println(mailId);

                }
                catch (Exception e){


                    System.out.println(mailId+"catch er vitor");
                }
               //


                firebaseDatabase.child("Supervision").child(mailId).setValue(supervisionInfo);
                firebaseDatabase.child("Supervision").child(mailId).child("status").setValue("Requested");



            }
        });
    }


}
