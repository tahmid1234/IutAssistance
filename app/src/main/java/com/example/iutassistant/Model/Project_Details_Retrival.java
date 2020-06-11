package com.example.iutassistant.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.iutassistant.AdapterClasses.Project_List_Adapter;
import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.NewActivities.Teacher_Requested__ProjectList_Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Project_Details_Retrival {
    SharedPreferences userInfo;

    String supervisorMail,status;
    ArrayList<ProjectInfo> project_list_infos =new ArrayList<>();
    Context mContext;
    ListView listView;

    public Project_Details_Retrival(SharedPreferences userInfo,ListView listView,Context mContext,String status) {
        this.userInfo = userInfo;
        this.listView=listView;
        this.mContext=mContext;
        this.status=status;

    }

    public void getRequested_Projects(){

        supervisorMail=userInfo.getString(Constant.user_email_preference,"Not Defined");
        supervisorMail=supervisorMail.substring(0,supervisorMail.indexOf("."));
        System.out.println(supervisorMail+" function er upor");
        FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Supervisor_Node).child(supervisorMail).child(status).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                project_list_infos.clear();

                if(dataSnapshot.exists()){
                    System.out.println("function er vitor if er o vitor");
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        String project_crs_name=snapshot.getKey();
                        String prog_batch=String.valueOf(snapshot.getValue());
                        GetProjectsDetails(project_crs_name,prog_batch);

                    }

                    System.out.println("Adapter");

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void GetProjectsDetails(String projectCrs,String batch_prog){
        FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Node).child(projectCrs).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String project_name=String.valueOf(dataSnapshot.child(Constant.Project_Name).getValue());
                    String crs=String.valueOf(dataSnapshot.child(Constant.Project_Course).getValue());
                    String allSid=String.valueOf(dataSnapshot.child(Constant.Project_Team_Mates).getValue());
                    String description=String.valueOf(dataSnapshot.child(Constant.Project_Description).getValue());
                    ProjectInfo projectInfo=new ProjectInfo(project_name,crs,allSid,description,projectCrs,batch_prog);
                    project_list_infos.add(projectInfo);
                    System.out.println("Description"+description );

                }
                Project_List_Adapter project_list_adapter=new Project_List_Adapter(project_list_infos,mContext,status);
                listView.setAdapter(project_list_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
