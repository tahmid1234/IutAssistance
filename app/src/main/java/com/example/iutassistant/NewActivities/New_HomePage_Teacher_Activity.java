package com.example.iutassistant.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.CourseModel;
import com.example.iutassistant.Presenter.HomePagePresenter;
import com.example.iutassistant.Presenter.IHomePagePresenter;
import com.example.iutassistant.Presenter.IPopUpClassAssignmentPresenter;
import com.example.iutassistant.Presenter.PopUpClassAssignPresenterI;
import com.example.iutassistant.R;
import com.example.iutassistant.SingleTone.UserInfoSharedPreferenceSingleTone;
import com.example.iutassistant.View.IClassAssignmentPopUpView;
import com.example.iutassistant.View.IHomePageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class New_HomePage_Teacher_Activity extends AppCompatActivity implements View.OnClickListener, IHomePageView , IClassAssignmentPopUpView {

    CardView classInfo,projects,announcement,attendence;
    SharedPreferences userInfoCheck,logInSTate,userInfoSp;
    String uid;
    FirebaseDatabase database;
    DatabaseReference ref;
    private IHomePagePresenter presenter;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__home_page__teacher);

        IPopUpClassAssignmentPresenter iPopUpClassAssignmentPresenter=new PopUpClassAssignPresenterI(this);
        //this method to be called after pop up
        //iPopUpClassAssignmentPresenter.fetchCourseLIst();
        classInfo = findViewById(R.id.classInfoId_teacher);
        projects = findViewById(R.id.projectId_teacher);
        attendence = findViewById(R.id.attendenceId_teacher);
        announcement = findViewById(R.id.announcementId_teacher);
        userInfoCheck=getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        logInSTate= getSharedPreferences(Constant.USER_LOGIN_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        presenter=new HomePagePresenter(this,userInfoCheck,logInSTate,Constant.Teacher_Node,this
        );
        presenter.onOpeningHomePage();

        myDialog = new Dialog(this);

        System.out.println(userInfoCheck.getString(Constant.user_email_preference,"no")+" "+(userInfoCheck.getBoolean(Constant.user_exists_preference,false)));

        if(!(userInfoCheck.getBoolean(Constant.user_exists_preference,false)))
        {
            UserInfoSharedPreferenceSingleTone.storeUserInfo(userInfoCheck,logInSTate,"Teachers",database,ref,uid);
        }

        Toast.makeText(this, userInfoCheck.getString(Constant.username_preference,"N/A"), Toast.LENGTH_SHORT).show();


        projects.setOnClickListener(this);
        attendence.setOnClickListener(this);
        //classInfo.setOnClickListener(this);  pop up screen er jonno eta of  kora. pop up drkr na hoile eta on kora lagbe abar

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.projectId_teacher){
            Intent intent = new Intent(getApplicationContext(), TeacherSide_Project_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else if(view.getId() == R.id.attendenceId_teacher){
            Intent intent = new Intent(getApplicationContext(), New_Attendence_Teacher.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else if(view.getId() == R.id.classInfoId_teacher){
            //Intent intent = new Intent(getApplicationContext(), Teachers_Class_Invitation.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //startActivity(intent);


        }





    }
    //pop up course spinner section
    public void makeCourseListSpinner(List<CourseModel> courseModels){
        /*ami toke course list pathai disi tui ekhn ei list je vabe khushi use korbi as in pop up hole jei course er spinner asbe
        ota banabi.age pop up bana then eta use korte parbi ar ki

        *
        */
        //System.out.println(courseModels.get(0).getName());
    }

    public void PopupScreen(View v) {
        TextView txtclose;
        Button doneBtn;
        myDialog.setContentView(R.layout.course_selection_popup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        doneBtn= (Button) myDialog.findViewById(R.id.popUpBtn);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
