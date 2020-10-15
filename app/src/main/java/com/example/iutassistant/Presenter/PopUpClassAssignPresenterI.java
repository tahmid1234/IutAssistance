package com.example.iutassistant.Presenter;

import com.example.iutassistant.Model.Connectors.SectionListFirebaseConnector;
import com.example.iutassistant.Model.CourseModel;
import com.example.iutassistant.Model.Connectors.CourseListFirebaseConnector;
import com.example.iutassistant.Model.Connectors.DatabaseConnector;
import com.example.iutassistant.Model.Section;
import com.example.iutassistant.View.IClassAssignmentPopUpView;

import java.util.List;

public class PopUpClassAssignPresenterI implements IFirebaseCourseListPresenter,IPopUpClassAssignmentPresenter,IFirebaseSectionListPresenter {

   private IClassAssignmentPopUpView iClassAssignmentPopUpView;
   private DatabaseConnector databaseConnector;

    public PopUpClassAssignPresenterI(IClassAssignmentPopUpView iClassAssignmentPopUpView) {
        this.iClassAssignmentPopUpView = iClassAssignmentPopUpView;
    }

    @Override
    public void fetchCourseLIst() {
        databaseConnector=new CourseListFirebaseConnector(this);
        databaseConnector.getData();
    }

    @Override
    public void useFireBaseCourseModelList(List<CourseModel> courseModelList) {
            iClassAssignmentPopUpView.makeCourseListSpinner(courseModelList);
    }

    @Override
    public void fetchSectionList() {
        databaseConnector=new SectionListFirebaseConnector(this);
        databaseConnector.getData();

    }

    @Override
    public void saveCLass() {

    }

    @Override
    public void useFirebaseSectionlList(List<Section> sectionList) {
        System.out.println(sectionList.get(0).getSectionName()+" "+sectionList.size());
    }
}
