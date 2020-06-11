package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.ProjectInfo;
import com.example.iutassistant.R;
import com.google.firebase.database.FirebaseDatabase;

public class Teachers_Requested_Project_Details_Activity extends AppCompatActivity {

    private CardView accept, reject;
    private TextView projectName, courseName, studentId, description;
    private  static ProjectInfo project_list_info;
    SharedPreferences userInfo;
    String supervisor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__project__details__after__click__to__list_view);
        userInfo=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);

        supervisor=userInfo.getString(Constant.user_email_preference,"NA");
        supervisor=supervisor.substring(0,supervisor.indexOf("."));

        accept = findViewById(R.id.acceptProjectId);
        reject = findViewById(R.id.rejectProjectId);

        projectName = findViewById(R.id.prjectNameID);
        courseName  = findViewById(R.id.courseNameId);
        studentId  = findViewById(R.id.studentIdforProject);
        description  = findViewById(R.id.descriptionOfProjectId);

        projectName.setText(project_list_info.getProjectName());
        courseName.setText(project_list_info.getCourse());
        studentId.setText(project_list_info.getAllSid());
        description.setText(project_list_info.getDescription());
    }

    public static void setProject_list_info(ProjectInfo project_list_info) {
        Teachers_Requested_Project_Details_Activity.project_list_info = project_list_info;
    }

    public void Accept_Request(View view){
        FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Supervisor_Node).child(supervisor).child(Constant.Project_Status_Requested_Node).child(project_list_info.getProject_Crs()).removeValue();
        FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Supervisor_Node).child(supervisor).child(Constant.Project_Status_Accepted_Node).child(project_list_info.getProject_Crs()).setValue(project_list_info.getBatch_prog());
        FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Course_Node).child(project_list_info.getCourse()).child(project_list_info.getProjectName()).setValue(Constant.Project_Status_Accepted_Node);

        String[] teamMateList= project_list_info.getAllSid().split(",");

        for(String sid : teamMateList){
            FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Student_Node).child(sid).child(project_list_info.getProject_Crs()).setValue(supervisor);
        }

        //generate a notification

    }

    public void Reject_Request(View view){
        FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Course_Node).child(project_list_info.getCourse()).child(project_list_info.getProjectName()).removeValue();
        FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Supervisor_Node).child(supervisor).child(Constant.Project_Status_Requested_Node).child(project_list_info.getProject_Crs()).removeValue();
        FirebaseDatabase.getInstance().getReference(Constant.Ref).child(Constant.Project_Structure_Root).child(Constant.Project_Node).child(project_list_info.getProject_Crs()).removeValue();
    }
}
