package com.example.iutassistant.NewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.ProjectInfo;
import com.example.iutassistant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Student_Project_List_Activity extends AppCompatActivity {

    private ListView projectList;
    SharedPreferences userInfo;
    private EditText projectNameText,teamMateIdText,descriptionText;
    private TextView warning;
    private AutoCompleteTextView supervisorText, crsText;
    private String projectName,teamMateId="Nothing",description,user_sid,allSid,supervisor,crs;

    private ProjectInfo projectInfo;
    int check=1;
    List<String> teamMateList=new ArrayList<String>();
    Dialog myDialog;
    DatabaseReference firebaseDatabase= FirebaseDatabase.getInstance().getReference(Constant.Ref);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project__list);
        userInfo=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        user_sid=userInfo.getString(Constant.user_sid_preference,"Not Defined");
        allSid=user_sid;
        projectList = findViewById(R.id.allprojects);
        myDialog = new Dialog(this);
    }

    public void showPopUp(View v){



        myDialog.setContentView(R.layout.activity_new__project_request);

        projectNameText=myDialog.findViewById(R.id.project_name);
        teamMateIdText=myDialog.findViewById(R.id.team_mate_id);
        warning=myDialog.findViewById(R.id.warning_text);
        supervisorText =myDialog.findViewById(R.id.superVisor_mail);
        crsText =myDialog.findViewById(R.id.crs_name);
        descriptionText=myDialog.findViewById(R.id.project_description);

        addCourse();
        addSupervisorMail();
        teamMateIdText.setText(user_sid.substring(0,5)+"****");


        myDialog.show();


    }

    public void add_teamId(View view){
        teamMateId=teamMateIdText.getText().toString().trim();
        if(!check_tId_validity(teamMateId))
        {
            warning.setText("Invalid Student Id");
            return;
        }
        warning.setText("");
        teamMateList.add(teamMateId);
        if(!teamMateId.equals(user_sid))
        {
            allSid=allSid+","+teamMateId;
        }

    }

    public boolean check_tId_validity(String sid){
        if(sid.length()==9 && !sid.equals(user_sid.substring(0,5)+"****") && !sid.equals(null))
            return true;

        return false;
    }

    public void submit(View view) {
        check = 2;
        warning.setText("");

        projectName = projectNameText.getText().toString().trim();
        if (!checkField(projectName))
            return;
        supervisor = supervisorText.getText().toString().trim();

        if (!checkField(supervisor))
            return;
        if (!teamMateId.equals(teamMateIdText.getText().toString().trim())){
            teamMateId = teamMateIdText.getText().toString().trim();
        if (check_tId_validity(teamMateId)) {
            allSid = allSid + "," + teamMateId;
            teamMateList.add(teamMateId);
        }
    }
        if(!checkField(allSid))
            return;
        description=descriptionText.getText().toString();
        if(!checkField(description))
            return;
        crs=crsText.getText().toString();
        if(!checkField(crs))
            return;
        checkProjectName(projectName);



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
                        String deptKey = mail+"   ("+dept+")";

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
        supervisorText.setThreshold(1);
        supervisorText.setAdapter(dataAdapter);

    }

    public void addCourse(){



        final List<String> list = new ArrayList<String>();
        list.add("");
        firebaseDatabase.child("TEACHES").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                if(dataSnapshot.exists()) {
                    for (DataSnapshot deptSnapshot : dataSnapshot.child(userInfo.getString(Constant.user_sec_preference,"Not Defined")).getChildren()) {

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
        crsText.setThreshold(1);
        crsText.setAdapter(dataAdapter);
    }

    public Boolean checkField(String field){

        if(field.equals("")) {

            warning.setText("Please Fill up the form properly");
            return false;
        }
        return true;
    }

    public void checkProjectName(String projectName){

        firebaseDatabase.child(Constant.Project_Structure_Root).child(Constant.Project_Course_Node).child(crs).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean status =false;
                if(dataSnapshot.child(projectName).exists()) {


                        String projectStatus=String.valueOf(dataSnapshot.child(projectName).getValue());


                                status = true;





                    if(check==2 ){


                        warning.setText("This project name is taken");
                    }


                }
                else
                    saveProjectInfo();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void saveProjectInfo(){
        check=1;
        projectInfo=new ProjectInfo(projectName,crs,supervisor,allSid,description);
        firebaseDatabase.child(Constant.Project_Structure_Root).child(Constant.Project_Course_Node).child(crs).child(projectName).setValue(Constant.Project_Status_Requested_Node);
        firebaseDatabase.child(Constant.Project_Structure_Root).child(Constant.Project_Node).child(projectName+"-"+crs).setValue(projectInfo);



        firebaseDatabase.child(Constant.Project_Structure_Root).child(Constant.Project_Supervisor_Node).child(getSupervisorMail()).child(Constant.Project_Status_Requested_Node).child(projectName+"-"+crs).setValue(userInfo.getString(Constant.user_prog_preference,"Not Defined")+userInfo.getString(Constant.user_batch_preference,"Not Defined"));
        /*for(String sid : teamMateList){
            firebaseDatabase.child(Constant.Project_Structure_Root).child(Constant.Project_Student_Node).child(sid).child(projectName+"-"+crs).setValue(supervisor);
        }
        teamMateList.clear();*/
        projectNameText.setText("");
        supervisorText.setText("");
        teamMateIdText.setText(user_sid.substring(0,5)+"****");
        crsText.setText("");
        descriptionText.setText("");
    }




    public String getSupervisorMail(){
        return supervisor.substring(0,supervisor.indexOf("."));
    }

}
