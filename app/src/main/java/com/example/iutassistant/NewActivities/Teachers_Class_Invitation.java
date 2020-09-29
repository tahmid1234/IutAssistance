package com.example.iutassistant.NewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.R;
import com.example.iutassistant.Model.FirebaseKeyDataListAutoTextInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teachers_Class_Invitation extends AppCompatActivity {

    public AutoCompleteTextView selectCourse, selectSection;
    private Button invite;
    private ImageView yes,no;
    private ListView invitedList;
    private String courseText,secText,name,email,teacherNameEmail="";
    private boolean isNotTaken=true;
    private Dialog asstTeacherDialog;
    private TextView teWarning;
    private int count_class;
    DatabaseReference firebaseDatabase,firebaseDatabaseCrs,firebaseDatabaseSec;
    FirebaseKeyDataListAutoTextInfo firebaseKeyDataListAutoTextInfo;
    SharedPreferences userInfo,classInfo;
    String teachers_all_uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers__class__invitation);

        userInfo=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        classInfo=getSharedPreferences(Constant.CLASSES_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        selectCourse = findViewById(R.id.courseSelectId);
        selectSection = findViewById(R.id.sectionSelectId);

        invite = findViewById(R.id.inviteButtonId);

        invitedList = findViewById(R.id.invitedListId);
        //firebaseKeyDataListAutoTextSingleTone=FirebaseKeyDataListAutoTextSingleTone.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance().getReference(Constant.Ref);

        addCourses();
        addSections();
        System.out.println(" tarpor ");
        Invite();
        asstTeacherDialog=new Dialog(this);

    }

    private void addSections() {

        firebaseDatabaseSec=firebaseDatabase.child(Constant.Section_Node);
        firebaseKeyDataListAutoTextInfo =new FirebaseKeyDataListAutoTextInfo();
        firebaseKeyDataListAutoTextInfo.getDataList(2,this,selectSection,firebaseDatabaseSec);
    }

    public void addCourses(){
        firebaseDatabaseCrs=firebaseDatabase.child(Constant.Course_Node);
        firebaseKeyDataListAutoTextInfo =new FirebaseKeyDataListAutoTextInfo();
        firebaseKeyDataListAutoTextInfo.getDataList(1,this,selectCourse,firebaseDatabaseCrs);

    }

    //listeners

    public void Invite(){

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" hoi nai ken");
                courseText=selectCourse.getText().toString().trim();
                secText=selectSection.getText().toString().trim();
                isClassTaken();

            }


        });
    }
    //dialog box button
    public void yesBtn(){
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                addClass(teachers_all_uid);
                asstTeacherDialog.dismiss();
            }
        });
    }

    public void noBtn(){
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                asstTeacherDialog.dismiss();
            }
        });
    }

    //fetching data from firebase

    public void isClassTaken(){
        firebaseDatabase.child(Constant.Classes_Node).child(secText).child(courseText).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
               if(dataSnapshot.exists()){
                   System.out.println("kia hua");
                   teachers_all_uid=String.valueOf(dataSnapshot.getValue());
                   System.out.println(teachers_all_uid+ "uid");

                   getTeachesName(teachers_all_uid);
               }

               else{
                   System.out.println("shabbash");
                    addClass(userInfo.getString(Constant.uid_preference,"N/A"));
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void getTeachesName(String teachers_all_uid){
            String[] all_uid=teachers_all_uid.split(",");
            firebaseDatabase.child(Constant.Teacher_Node).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   if(dataSnapshot.exists()){
                       teacherNameEmail="";
                       for(String uid : all_uid) {
                           name = String.valueOf(dataSnapshot.child(uid).child(Constant.userInfo_node_name).getValue());
                           email = String.valueOf(dataSnapshot.child(uid).child(Constant.userInfo_node_email).getValue());
                           System.out.println(email+ "  email");
                           teacherNameEmail = teacherNameEmail + name + "(" + email + ")";
                       }
                       popUpAssistantTeacherDialogBox();

                   }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }
//storing into Firebase
    public void addClass(String teachesId){


        firebaseDatabase.child(Constant.Teaches_Node).child(userInfo.getString(Constant.user_email_preference,"N/A")).child(secText).child(courseText).setValue("1");
        firebaseDatabase.child(Constant.Classes_Node).child(secText).child(courseText).setValue(teachesId);
        addClassesToSp();



    }

//DialogBox
    public void popUpAssistantTeacherDialogBox(){
        asstTeacherDialog.setContentView(R.layout.dialog_is_class_taken);
        yes=asstTeacherDialog.findViewById(R.id.yesBtn);
        no=asstTeacherDialog.findViewById(R.id.noBtn);
        teWarning=asstTeacherDialog.findViewById(R.id.teWarning);
        teWarning.setText(teacherNameEmail);
        yesBtn();
        noBtn();
        asstTeacherDialog.show();

    }

    public void addClassesToSp(){
            count_class=classInfo.getInt(Constant.CLASSES_count_preference,0);
            count_class++;
            classInfo.edit().putString(String.valueOf(count_class),courseText+secText);
            classInfo.edit().putInt(Constant.CLASSES_count_preference,count_class);
    }


}
