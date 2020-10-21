package com.example.iutassistant.Presenter;

import android.content.Context;

import com.example.iutassistant.Factory.DatabaseConnectorFactory;
import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.AssignedClass;
import com.example.iutassistant.Model.Connectors.ClassFirebaseConnector;
import com.example.iutassistant.Model.Connectors.SectionListFirebaseConnector;
import com.example.iutassistant.Model.CourseModel;
import com.example.iutassistant.Model.Connectors.CourseListFirebaseConnector;
import com.example.iutassistant.Model.Connectors.DatabaseConnector;
import com.example.iutassistant.Model.Section;
import com.example.iutassistant.Model.User;
import com.example.iutassistant.View.IClassAssignmentPopUpView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class PopUpClassAssignPresenterI implements IFirebaseCourseListPresenter,IPopUpClassAssignmentPresenter,IFirebaseSectionListPresenter,IFirebaseClassPresenter ,SharedPreferenceUserPresenter{

   private IClassAssignmentPopUpView iClassAssignmentPopUpView;
   private DatabaseConnector databaseConnector;
   private DatabaseConnectorFactory databaseConnectorFactory;
    private DatabaseConnector sharedPreferenceRecievingConector;
   private DatabaseConnector firebaseRecievingConector;
   private DatabaseConnector firebaseSendingConnector;

   private String course,section;
   private Context context;

    public PopUpClassAssignPresenterI(IClassAssignmentPopUpView iClassAssignmentPopUpView, Context context) {
        this.iClassAssignmentPopUpView = iClassAssignmentPopUpView;
        this.context=context;
        databaseConnectorFactory=new DatabaseConnectorFactory();

    }

    @Override
    public void fetchCourseLIst() {
        databaseConnector=new CourseListFirebaseConnector(this);
        databaseConnector.getData();
    }

    @Override
    public void useFireBaseCourseModelList(List<CourseModel> courseModelList) {
        List<String> courseNameList =new ArrayList<>();
        for (CourseModel course:courseModelList){
            courseNameList.add(course.getName());
        }
            iClassAssignmentPopUpView.makeCourseListSpinner(courseNameList);
    }

    @Override
    public void fetchSectionList() {
        databaseConnector=new SectionListFirebaseConnector(this);
        databaseConnector.getData();

    }

    @Override
    public void saveCLass(String section,String course) {
        this.section=section;
        this.course=course;
        firebaseRecievingConector = databaseConnectorFactory.getDatabaseConnector(this,section,course, Constant.CLASS_FIREBASE_CONNECTOR);
        firebaseRecievingConector.getData();
    }

    @Override
    public void useFirebaseSectionlList(List<Section> sectionList) {

        List<String> sectionNameList =new ArrayList<>();
        for (Section section:sectionList){
            sectionNameList.add(section.getSectionName());
        }
        iClassAssignmentPopUpView.makeSectionListSpinner(sectionNameList);

    }



    @Override
    public void useFireBaseAssignedClass(AssignedClass assignedClass) {

        String emails=assignedClass.getTeachersEmail();
        System.out.println(emails+" Contact any one of these email user to add u as a co-assistant teacher");


    }

    @Override
    public void onAssignedClassNotFound() {
        FirebaseAuth.getInstance().getCurrentUser().getUid();

            sharedPreferenceRecievingConector=databaseConnectorFactory.getDatabaseConnector(this, FirebaseAuth.getInstance().getCurrentUser().getUid(),context,Constant.USER_SHARED_PREFERENCE_CONNECTOR);
            sharedPreferenceRecievingConector.getData();
    }

    @Override
    public void useSpUserModel(User user) {
        System.out.println(user.getEmail()+ "  ahskh");
        firebaseSendingConnector =new ClassFirebaseConnector(section,course);
        firebaseSendingConnector.postData(new AssignedClass(user.getEmail()));

    }
}
