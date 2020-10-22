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

import com.example.iutassistant.Acitivities.Teachers_ClassInfo;
import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Presenter.HomePagePresenter;
import com.example.iutassistant.Presenter.IHomePagePresenter;
import com.example.iutassistant.Presenter.IPopUpClassAssignmentPresenter;
import com.example.iutassistant.Presenter.PopUpClassAssignPresenterI;
import com.example.iutassistant.R;
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
    TextView txtclose;
    Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__home_page__teacher);
        

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




        projects.setOnClickListener(this);
        attendence.setOnClickListener(this);
        //classInfo.setOnClickListener(this);  pop up screen er jonno eta of  kora. pop up drkr na hoile eta on kora lagbe abar
        announcement.setOnClickListener(this);

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

        else if(view.getId() == R.id.announcementId_teacher){
            Intent intent = new Intent(getApplicationContext(), Teachers_ClassInfo.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
 }


    public void PopupScreen(View v) {

        IPopUpClassAssignmentPresenter iPopUpClassAssignmentPresenter=new PopUpClassAssignPresenterI(this,this);
        /*
        * these two methods are called for fetching all offered course
        * and all existed section for teachers to assign a course to a section as a class
         */
        iPopUpClassAssignmentPresenter.fetchCourseLIst();
        iPopUpClassAssignmentPresenter.fetchSectionList();



        myDialog.setContentView(R.layout.course_selection_popup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        doneBtn= (Button) myDialog.findViewById(R.id.popUpBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPopUpClassAssignmentPresenter.saveCLass("SWE17 3","CSE5690");
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    //pop up course spinner section
    public void makeCourseListSpinner(List<String> courseModels){
        /*ami toke course list pathai disi tui ekhn ei list je vabe khushi use korbi as in pop up hole jei course er spinner asbe
        ota banabi.age pop up bana then eta use korte parbi ar ki
        *
        */
        //running a loop on get(i) will give u all courses name
        System.out.println(courseModels.get(0));
    }

    public void makeSectionListSpinner(List<String> sections){
        /*ami toke course list pathai disi tui ekhn ei list je vabe khushi use korbi as in pop up hole jei course er spinner asbe
        ota banabi.age pop up bana then eta use korte parbi ar ki
        auto text hob ar ki

        *
        */
        //this running a loop on get(i) function will give u all sections name
        System.out.println(sections.get(0));
    }



}
