package com.example.iutassistant.Presenter;

import com.example.iutassistant.Model.CourseModel;
import com.example.iutassistant.Presenter.Connectors.CourseListFirebaseConnector;
import com.example.iutassistant.Presenter.Connectors.DatabaseConnector;
import com.example.iutassistant.View.IClassAssignmentPopUpView;
import com.example.iutassistant.View.IHomePageView;

import java.util.List;

public class PopUpClassAssignPresenterI implements IFirebaseCourseListPresenter,IPopUpClassAssignmentPresenter {

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

    }

    @Override
    public void saveCLass() {

    }
}
