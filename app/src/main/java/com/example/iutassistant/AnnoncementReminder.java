package com.example.iutassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.iutassistant.AdapterClasses.Assignment_Announcement_Adapter;
import com.example.iutassistant.AdapterClasses.ClassRescheduling_Adapter;
import com.example.iutassistant.AdapterClasses.Quiz_Announcement_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnnoncementReminder extends AppCompatActivity {

    DatabaseReference announcementInfoDB;
    home_page_student homePageStudent=new home_page_student();

    public String sec, id, crs, path,syllabus,date,type,teacher_id;
    static String teacher_name;
    ListView listView;
    public int flag;
    static  int i=0;
    ArrayList<AnnouncementDetail> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_reminder);
        flag=homePageStudent.getFlag();

     listView=findViewById(R.id.listViewAnnouncement);
        list_Creating();

       // System.out.println("flaggggggggggggggggggggggggggggggg"+homePageStudent.getFlag());


    }

    public void list_Creating() {
        System.out.println("function a&&&&&&&&&&&&&&&&&&&&&&&&7&& "+flag);

        final ArrayList<String> crs_list = new ArrayList<>();
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //path = "University/IUT/Quiz";
        if(flag==1)
            path = "University/IUT/Quiz";
        else if(flag==2)
            path="University/IUT/ClassTime";
        else if(flag==3)
            path="University/IUT/Assignment";

        announcementInfoDB = FirebaseDatabase.getInstance().getReference(path);

        FirebaseDatabase.getInstance().getReference("User").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    sec = String.valueOf(dataSnapshot.child("sec").getValue());
                    System.out.println(sec + " thik ache************* ****");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        announcementInfoDB.addListenerForSingleValueEvent(new ValueEventListener() {
            {
                System.out.println("HOI ns krno");
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();


                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.child(sec).getChildren()) {
                        crs = snapshot.getKey();


                        System.out.println(sec + "Yooyooo" + crs + " *********" + date+" type");
                        get_quiz_details(sec,crs,path);


                    }


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    void get_quiz_details(String sec, final String crs, String path){
        System.out.println(crs+" crs csrs csrs crs"+path+" "+sec);

        //final ArrayList<Post> list = new ArrayList<>();
        final String path_in=path;
        final  String sec_in=sec;
        final String crs_in=crs;




        FirebaseDatabase.getInstance().getReference(path).child(sec).child(crs).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("function 3 no data changr a&&&&&&&&&&&&&&&&&&&&&&&&7"+crs);
                if(dataSnapshot.exists()) {
                    for (DataSnapshot crsSnapshot : dataSnapshot.getChildren()) {
                        if(flag==1){
                        date = String.valueOf(dataSnapshot.child("quizDate").getValue());
                        type = String.valueOf(dataSnapshot.child("quiz_no").getValue());
                        syllabus = String.valueOf(dataSnapshot.child("syllabus").getValue());
                        teacher_id=String.valueOf(dataSnapshot.child("t_id").getValue());}
                        else if(flag==2){
                            date = String.valueOf(dataSnapshot.child("date").getValue());
                            type = String.valueOf(dataSnapshot.child("type").getValue());
                            syllabus = String.valueOf(dataSnapshot.child("explanation").getValue());
                            teacher_id=String.valueOf(dataSnapshot.child("t_id").getValue());
                        }
                        else{
                            date = String.valueOf(dataSnapshot.child("date").getValue());
                            type = String.valueOf(dataSnapshot.child("type").getValue());
                            syllabus = String.valueOf(dataSnapshot.child("explanation").getValue());
                            teacher_id=String.valueOf(dataSnapshot.child("t_id").getValue());
                        }

                        //String count=String.valueOf(crsSnapshot.getValue());
                        //Attendance_detail attendance_detail=new Attendance_detail(crs_in,date,count);

                        //date="Date"+date+"="+count;
                        System.out.println(date + "function a&&&&&&&&&&&&&&&&&&&&&&&&7" + type + " " + syllabus);
                        //Post post=new Post("Course_Name"+crs_in ,date);
                        // Attendance_detail attendance_detail1=new Attendance_detail(crs_in,date,count);
                        //   System.out.println(post.getPoster_name()+"   ************   "+post.getPost());
                        // list.add(post);
                        // list.add(attendance_detail);
                    }

                }



                System.out.println("Teacher_********name"+teacher_name);

                AnnouncementDetail announcementDetail=new AnnouncementDetail(crs,date,syllabus,type,teacher_id);
                list.add(announcementDetail);

                System.out.println(list.size()+" ****post  size 1*****");
                System.out.println("Hocche  naki dekhio to !!!!!!!!!!!!");
                if(flag==1){
                Quiz_Announcement_Adapter quizAnnouncement_adapter =new Quiz_Announcement_Adapter(AnnoncementReminder.this,list);
                listView.setAdapter(quizAnnouncement_adapter);}
                else if(flag==2)
                {
                    ClassRescheduling_Adapter classRescheduling_adapter=new ClassRescheduling_Adapter(AnnoncementReminder.this,list);
                    listView.setAdapter(classRescheduling_adapter);
                }
                else{
                    Assignment_Announcement_Adapter assignment_announcement_adapter=new Assignment_Announcement_Adapter(AnnoncementReminder.this,list);
                    listView.setAdapter(assignment_announcement_adapter);
                }

               // Collections.reverse(list);
              //  Attendance_Adapter attendance_adapter=new Attendance_Adapter(ShowAttendance.this,list);
                //   PostAdapter postAdapter = new PostAdapter(ShowAttendance.this, list);
                //  listView.setAdapter(postAdapter);
               // listView.setAdapter(attendance_adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
