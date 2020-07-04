package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.R;
import com.example.iutassistant.Model.FirebaseKeyDataListAutoTextInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Teachers_Class_Invitation extends AppCompatActivity {

    public AutoCompleteTextView selectCourse, selectSection;
    private Button invite;
    private ListView invitedList;
    DatabaseReference firebaseDatabase,firebaseDatabaseCrs,firebaseDatabaseSec;
    FirebaseKeyDataListAutoTextInfo firebaseKeyDataListAutoTextInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers__class__invitation);


        selectCourse = findViewById(R.id.courseSelectId);
        selectSection = findViewById(R.id.sectionSelectId);

        invite = findViewById(R.id.inviteButtonId);

        invitedList = findViewById(R.id.invitedListId);
        //firebaseKeyDataListAutoTextSingleTone=FirebaseKeyDataListAutoTextSingleTone.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance().getReference(Constant.Ref);

        addCourses();
        addSections();

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



}
