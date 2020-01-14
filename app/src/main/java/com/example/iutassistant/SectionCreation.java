package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;

public class SectionCreation extends AppCompatActivity {

    Spinner secId,batchId;
    AutoCompleteTextView prog;
    Button join;
    TextView warningText;

    String secName,progName,batch,secID,checkStatus=" ";


    DatabaseReference progDb,joinActionDb;

    String sid,secValue;

    int dataCheck=0;


    private String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    String ref="University/IUT";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_creation);
        warningText=findViewById(R.id.warning);
        fetchSid();
        addProg();
        addBatch();
        addSecID();
        join();

        dataCheck=0;

        NotiFication.createNotificationChannel(this);
    }

    void addSecID(){
        secId = (Spinner) findViewById(R.id.secId);
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secId.setAdapter(dataAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void addBatch(){
        batchId=(Spinner) findViewById(R.id.batch);
        int year= Year.now().getValue();
        year=year-2000;
        year=year-1;
        String fstYear=Integer.toString(year);
        year=year-1;
        String sndYear=Integer.toString(year);
        year=year-1;
        String trdYear=Integer.toString(year);
        year=year-1;
        String fthYear=Integer.toString(year);


        List<String> list = new ArrayList<String>();
        list.add(fstYear);
        list.add(sndYear);
        list.add(trdYear);
        list.add(fthYear);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        batchId.setAdapter(dataAdapter);


    }

    void addProg(){
        prog=(AutoCompleteTextView) findViewById(R.id.prog);

        progDb= FirebaseDatabase.getInstance().getReference("University/IUT");
        final List<String> list = new ArrayList<String>();
        list.add("");
        progDb.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();

                for(DataSnapshot deptSnapshot : dataSnapshot.child("PROG").getChildren()){

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
        prog.setThreshold(1);
        prog.setAdapter(dataAdapter);
    }

    void join(){
        join=findViewById(R.id.join);




        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progName=prog.getText().toString().trim();
                progName=progName.toUpperCase();
                secID=secId.getSelectedItem().toString().trim();
                batch=batchId.getSelectedItem().toString().trim();
                secName=progName+""+batch+" "+secID;

                FirebaseDatabase.getInstance().getReference(ref).child("SECTION").child(secName).addValueEventListener(new ValueEventListener() {
                    {System.out.println("EKHANE DEKHIO fn TW KI KAHINI **********" + secValue);}
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            //secValue = String.valueOf(dataSnapshot.child(secName).getValue());

                            System.out.println("EKHANE DEKHIO TW KI KAHINI **********" + secValue);
                            if(dataCheck!=3){
                            dataCheck=1;

                            setInfo();}
                            System.out.println("EKHANE DEKHIO porer TW KI KAHINI **********" + secValue);

                        }
                        else
                        if(dataCheck!=3)
                        setInfo();




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






            }
        });
    }


    String fetchSid(){



        FirebaseDatabase.getInstance().getReference(ref).child("Students").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                        sid= String.valueOf(dataSnapshot.child("id").getValue());
                        System.out.println("age"+sid);

            }


        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            });


            checkStatus();
        return sid;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void setInfo(){
        System.out.println("EKHANE DEKHIO  setInfo TW KI KAHINI **********" + secValue);

        if(dataCheck==1){
            System.out.println("EKHANE DEKHIO else    TW KI KAHINI **********" + dataCheck);
            joinActionDb=FirebaseDatabase.getInstance().getReference(ref).child("REQUEST").child(secName);
            joinActionDb.child(sid).child("id").setValue(uid);
            joinActionDb.child(sid).child("STATUS").setValue("REQUESTED");
            FirebaseDatabase.getInstance().getReference("University/IUT").child("Students").child(uid).child("sec").setValue("REQUESTED");
            dataCheck=3;
            }

        else{
            System.out.println("EKHANE DEKHIO IF    TW KI KAHINI **********" + dataCheck);
            System.out.println(sid+"ashena keno");
            FirebaseDatabase.getInstance().getReference(ref).child("PROG").child(progName).setValue(progName);
            FirebaseDatabase.getInstance().getReference(ref).child("SECTION").child(secName).setValue(secName);
            FirebaseDatabase.getInstance().getReference(ref).child("StudentsInSection").child(secName).child(sid).setValue(uid);
            FirebaseDatabase.getInstance().getReference(ref).child("Students").child(uid).child("sec").setValue(secName);
            FirebaseDatabase.getInstance().getReference(ref).child("REQUEST").child(secName).child(sid).child("STATUS").setValue("ACCEPTED");
            dataCheck=3;


        }


//            mytime.scheduleAtFixedRate(task,60,60);
          //  if(checkStatus().equals("ACCEPTED")){

             //   startActivity(new Intent(getApplicationContext(), home_page_student.class));

       // }





    }

    String checkStatus(){

        System.out.println(secName+" secu2  "+secID);

       FirebaseDatabase.getInstance().getReference(ref).child("Students").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    System.out.println("ki ki hoi kisu tw hoi");
                checkStatus=String.valueOf(dataSnapshot.child("sec").getValue());
                if(checkStatus.equals("PENDING"))
                {
                 warningText.setText("You are not a member of any section yet");
                }
                else if(checkStatus.equals("REQUESTED")){
                    warningText.setText("Your request is pending");
                }
                else if(checkStatus.equals("REJECTED")){
                    warningText.setText("Your request is rejected");
                }
                else
                {
                    if(checkStatus.equals(secName)) {
                        Intent intent = new Intent(SectionCreation.this, home_page_student.class);
                        NotiFication.showNotification(SectionCreation.this.getApplicationContext(), intent, 1, "Request Accepted", "You have become a member of" + secName);
                    }
                    startActivity(new Intent(getApplicationContext(), home_page_student.class));
                }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return checkStatus;
    }




}
