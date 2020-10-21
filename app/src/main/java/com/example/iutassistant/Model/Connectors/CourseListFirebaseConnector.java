package com.example.iutassistant.Model.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.CourseModel;
import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Presenter.IFirebaseCourseListPresenter;
import com.example.iutassistant.Model.Server.FirebaseDataBaseHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CourseListFirebaseConnector extends DatabaseConnector implements FirebaseConnector {

    private IFirebaseCourseListPresenter firebaseCourseListPresenter;
    private String path;
    private List<CourseModel> courseModels;
    private DatabaseReference databaseReference;

    public CourseListFirebaseConnector(IFirebaseCourseListPresenter firebaseCourseListPresenter) {
        this.firebaseCourseListPresenter = firebaseCourseListPresenter;
        path= Constant.Ref+"/"+Constant.Course_Node;

        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);
        super.dataBaseHandler =new FirebaseDataBaseHandler(this,databaseReference);
        courseModels=new ArrayList<CourseModel>();
    }





    @Override
    public void convertDataSnapShot(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds:dataSnapshot.getChildren()){
            String key=ds.getKey();
            CourseModel courseModel=ds.getValue(CourseModel.class);
            courseModel.setCourseCode(key);
            courseModels.add(courseModel);



        }
        firebaseCourseListPresenter.useFireBaseCourseModelList(courseModels);




    }

    @Override
    public void setErrorStatus(String error) {

    }

    @Override
    public void onDataNotExist() {

    }
}
