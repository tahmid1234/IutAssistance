package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iutassistant.AdapterClasses.Attendance_Adapter;
import com.example.iutassistant.AdapterClasses.PostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowAttendance extends AppCompatActivity {

    DatabaseReference studentInfo;
    public String sec,id,crs,path;
    ArrayList<Attendance_detail> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_attendance);
        System.out.println("ATTENDANCE a ashche **");
        ListView listView=findViewById(R.id.listViewAttendance);
        list_Creating();





    }

    public void list_Creating(){
        System.out.println("function a&&&&&&&&&&&&&&&&&&&&&&&&7");

        final ArrayList<String> crs_list=new ArrayList<>();
        final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println("function a&&&&&&&&&&&&&&&&&&&&&&&&7");
        path ="User/"+uid;
                studentInfo= FirebaseDatabase.getInstance().getReference(path);
        studentInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            {System.out.println("HOI ns krno");}
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                int i= (int) dataSnapshot.getChildrenCount();

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        id = String.valueOf(dataSnapshot.child("id").getValue());
                         sec=String.valueOf(dataSnapshot.child("sec").getValue());
                        System.out.println(sec+"Yooyooo"+id+" "+dataSnapshot.exists());



                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FirebaseDatabase.getInstance().getReference("University/IUT/TEACHES").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("function porer on datai a&&&&&&&&&&&&&&&&&&&&&&&&7");
                System.out.println("SECCCCCCC"+sec);
                for (DataSnapshot crsSnapshot : dataSnapshot.child(sec).getChildren()) {
                    System.out.println("functionporer loop a a&&&&&&&&&&&&&&&&&&&&&&&&7");
                    crs = crsSnapshot.getKey();
                    attendance_list_creating(crs,sec,id);

                    System.out.println("function a&&&&&&&&&&&&&&&&&&&&&&&&7" + crs);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    void attendance_list_creating(String crs,String sec, String id){
        System.out.println(crs+" crs csrs csrs crs");
        final ListView listView=findViewById(R.id.listViewAttendance);
        //final ArrayList<Post> list = new ArrayList<>();
        final String id_in=id;
        final  String sec_in=sec;
        final String crs_in=crs;

        FirebaseDatabase.getInstance().getReference("University/IUT/Attendance").child(sec).child(crs).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("function 3 no data changr a&&&&&&&&&&&&&&&&&&&&&&&&7");

                for(DataSnapshot crsSnapshot : dataSnapshot.child(id_in).getChildren()){
                    String date=crsSnapshot.getKey();
                    String count=String.valueOf(crsSnapshot.getValue());
                    Attendance_detail attendance_detail=new Attendance_detail(crs_in,date,count);

                    date="Date"+date+"="+count;
                    System.out.println(count+"function a&&&&&&&&&&&&&&&&&&&&&&&&7"+date+" "+crs_in);

                    list.add(attendance_detail);
                }

                System.out.println(list.size()+" ****post  size 1*****");
                Collections.reverse(list);
                Attendance_Adapter attendance_adapter=new Attendance_Adapter(ShowAttendance.this,list);

                listView.setAdapter(attendance_adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
