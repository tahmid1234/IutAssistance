package com.example.iutassistant.Model.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.AssignedClass;
import com.example.iutassistant.Model.Server.FirebaseDataBaseHandler;
import com.example.iutassistant.Presenter.IFirebaseClassPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClassFirebaseConnector extends DatabaseConnector implements FirebaseConnector {

    private IFirebaseClassPresenter firebaseClassPresenter;
    private AssignedClass assignedClass;
    private DatabaseReference databaseReference;
    private String path;

    public ClassFirebaseConnector(String section,String course) {

        path= Constant.Ref+"/"+Constant.Classes_Node+"/"+section+"/"+course;
        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);
        super.dataBaseHandler=new FirebaseDataBaseHandler(this,databaseReference);
    }

    public ClassFirebaseConnector(IFirebaseClassPresenter firebaseClassPresenter, String section,String course) {
        this.firebaseClassPresenter = firebaseClassPresenter;

        path= Constant.Ref+"/"+Constant.Classes_Node+"/"+section+"/"+course;
        System.out.println(path+" path ");
        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);
        super.dataBaseHandler=new FirebaseDataBaseHandler(this,databaseReference);
    }

    @Override
    public void convertDataSnapShot(DataSnapshot dataSnapshot) {
        assignedClass=dataSnapshot.getValue(AssignedClass.class);
        firebaseClassPresenter.useFireBaseAssignedClass(assignedClass);

    }

    @Override
    public void setErrorStatus(String error) {

    }

    @Override
    public void onDataNotExist() {
        firebaseClassPresenter.onAssignedClassNotFound();
    }
}
