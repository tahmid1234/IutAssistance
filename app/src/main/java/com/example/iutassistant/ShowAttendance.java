package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.AdapterClasses.AttendanceInPercentageAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowAttendance extends AppCompatActivity {

    DatabaseReference studentInfo;
    public String sec,id,crs,path;
    ArrayList<Attendance_detail> list = new ArrayList<>();
    ArrayList<AttendanceInPercentage> attendanceInPercentageArrayList=new ArrayList<>();
    Double totalClasses=0.0,totalAttendance=0.0,attendacePercentage=0.0;
    AttendanceInPercentage attendanceInPercentage;
    ListView percentageListView;
    AttendanceCalculation attendanceCalculation;

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

    void attendance_list_creating(final String crs, String sec, String id){
        System.out.println(crs+" crs csrs csrs crs");
        final ListView listView=findViewById(R.id.listViewAttendance);
        //final ArrayList<Post> list = new ArrayList<>();
        final String id_in=id;
        final  String sec_in=sec;
        final String crs_in=crs;

        attendanceCalculation=new AttendanceCalculation(crs_in,sec_in,id_in,"percentageList",ShowAttendance.this,listView);
       // System.out.println(attendanceCalculation.calculateAttendance()+" eta list &^&^&^");
       /* FirebaseDatabase.getInstance().getReference("University/IUT/Attendance").child(sec).child(crs).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("function 3 no data changr a&&&&&&&&&&&&&&&&&&&&&&&&7");
                totalClasses=0.0;
                totalAttendance=0.0;
                for(DataSnapshot crsSnapshot : dataSnapshot.child(id_in).getChildren()){
                    String date=crsSnapshot.getKey();
                    String count=String.valueOf(crsSnapshot.getValue());
                    Attendance_detail attendance_detail=new Attendance_detail(crs_in,date,count);
                    totalClasses++;
                  //  if(count.equals("1"))
                        totalAttendance=totalAttendance+Double.parseDouble(count);


                    date="Date"+date+"="+count;
                    System.out.println(count+"function a&&&&&&&&&&&&&&&&&&&&&&&&7"+date+" "+crs_in+" total classes and attendace"+totalAttendance+"  "+totalClasses);

                    list.add(attendance_detail);
                }
                if(totalClasses>0){
               attendacePercentage=(totalAttendance/totalClasses)*100;
                attendanceInPercentage=new AttendanceInPercentage(crs_in,attendacePercentage);
                attendanceInPercentageArrayList.add(attendanceInPercentage);



                System.out.println(list.size()+" ****post  size 1*****");
                Collections.reverse(list);
                Attendance_Adapter attendance_adapter=new Attendance_Adapter(ShowAttendance.this,list);
                AttendanceInPercentageAdapter attendanceInPercentageAdapter=new AttendanceInPercentageAdapter(ShowAttendance.this,attendanceInPercentageArrayList);

               // listView.setAdapter(attendance_adapter);
                listView.setAdapter(attendanceInPercentageAdapter);}
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
       // attendanceInPercentageArrayList=(attendanceCalculation.getAttendanceInPercentageArrayList());
       // if(attendanceInPercentageArrayList.size()>0)
       //System.out.println(attendanceCalculation.getAttendacePercentage()+" eta list &^&^&^");

      //  AttendanceInPercentageAdapter attendanceInPercentageAdapter=new AttendanceInPercentageAdapter(ShowAttendance.this,attendanceInPercentageArrayList);
      //  listView.setAdapter(attendanceInPercentageAdapter);

    }


}
