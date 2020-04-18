package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.iutassistant.Services.MyFirebaseMassagingService;
import com.example.iutassistant.Services.MySingleton;
import com.example.iutassistant.Services.Notification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SectionCreation extends AppCompatActivity {

    Spinner secId,batchId;
    AutoCompleteTextView prog;
    Button join;
    TextView warningText;

    String secName,progName,batch,secID,checkStatus=" ";
    SharedPreferences sp; //sp is going to be used to keep users logged in

    DatabaseReference progDb,joinActionDb;

    String sid,secValue;

    int dataCheck=0;


    private String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    String ref="University/IUT";

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA_9tBebo:APA91bHMWuBlfbUe58s7YBPCLX5S_DvvifAg9c3Pzz6orpxjegc8dfzsYGcOGR5rZ5PPYZ5WQCc1MjimyiOojEWfh6S50as3MvpiHSRh-4mPV-ZXluz8nyvd_WT2Im08CN0T8OTd6SO5";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE="Request Accepted";
    String NOTIFICATION_MESSAGE="You have become a member of";
    String TOPIC="/topics/userABC";

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
      //  FirebaseMessaging.getInstance().subscribeToTopic(uid);
        //OldNotiFication.createNotificationChannel(this);


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

              //  Notification notification=new Notification(uid,"Request from notification","Abar geche Alhumdulillah",LogIn.contextLogin);
              //  notification.setNotification();

                //started
                /*TOPIC = "/topics/"+uid; //topic must match with what the receiver subscribed to
                NOTIFICATION_TITLE = "Checking";
                NOTIFICATION_MESSAGE = "Request Sent Alhumdulillah ";
                JSONObject notification = new JSONObject();
                JSONObject notifcationBody = new JSONObject();

                System.out.println("j son object er vitor achi");
                try {
                    notifcationBody.put("title", NOTIFICATION_TITLE);
                    notifcationBody.put("message", NOTIFICATION_MESSAGE);

                    notification.put("to", TOPIC);
                    notification.put("data", notifcationBody);
                    System.out.println("try er vitor achi");
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage() );
                }
                System.out.println("sned notir upor er vitor achi");

                sendNotification(notification);
                //end*/



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

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(FCM_API, notification, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SectionCreation.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");

                    }
                })


            {
                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
            };
        System.out.println("Single ton er vitor achi");

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);


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
                    Intent intent = new Intent(SectionCreation.this, home_page_student.class);
                   // Notification.setNotification("Your section request is rejected "+secName,intent,SectionCreation.this);
                    setNotification("Your section request is rejected "+secName,intent);
                }
                else
                {
                    if(checkStatus.equals(secName)) {
                        System.out.println("If er vitor achi");
                        Intent intent = new Intent(SectionCreation.this, home_page_student.class);

                        //Notification.setNotification("You have become a member of "+secName,intent,SectionCreation.this);
                        System.out.println("send notir niche er vitor achi");
                        setNotification("You  became a member of "+secName,intent);

                    }
                    startActivity(new Intent(getApplicationContext(), home_page_student.class));
                    finish();

                }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return checkStatus;
    }

    public void setNotification(String statement,Intent intent){
        OldNotiFication.showNotification(SectionCreation.this.getApplicationContext(), intent, 1, "Request", statement);
        TOPIC = "/topics/userABC"; //topic must match with what the receiver subscribed to
        NOTIFICATION_TITLE = sid;
        NOTIFICATION_MESSAGE = "You have become a member of "+secName;
        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();

        System.out.println("j son object er vitor achi");
        try {
            notifcationBody.put("title", NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
            System.out.println("try er vitor achi");
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }
        System.out.println("sned notir upor er vitor achi");

        sendNotification(notification);

    }






}
