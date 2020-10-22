package com.example.iutassistant.Model.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.Server.FirebaseDataBaseHandler;
import com.example.iutassistant.Model.Teaches;
import com.example.iutassistant.Presenter.IFirebaseTeachesPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeachesFirebaseConnector extends DatabaseConnector implements  FirebaseConnector{

    private String email;
    private String section;
    private IFirebaseTeachesPresenter firebaseTeachesPresenter;
    private String path;
    private DatabaseReference databaseReference;
    private Teaches teaches;

    public TeachesFirebaseConnector(IFirebaseTeachesPresenter firebaseTeachesPresenter,String email, String section) {
        this.email = email;
        this.section = section;
        this.firebaseTeachesPresenter = firebaseTeachesPresenter;
        path= Constant.Ref+"/"+Constant.Teaches_Node+"/"+email+"/"+section+"/";
        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);
        super.dataBaseHandler=new FirebaseDataBaseHandler(this,databaseReference);

    }

    public TeachesFirebaseConnector(String email, String section) {
        this.email = email;
        this.section = section;
        path= Constant.Ref+"/"+Constant.Teaches_Node+"/"+email+"/"+section+"/";
        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);
        super.dataBaseHandler=new FirebaseDataBaseHandler(this,databaseReference);
    }

    @Override
    public void convertDataSnapShot(DataSnapshot dataSnapshot) {

        teaches=dataSnapshot.getValue(Teaches.class);
        firebaseTeachesPresenter.useFireBaseTeaches(teaches);

    }

    @Override
    public void setErrorStatus(String error) {

    }

    @Override
    public void onDataNotExist() {
        firebaseTeachesPresenter.onTeachesDataNotFound();
    }
}
