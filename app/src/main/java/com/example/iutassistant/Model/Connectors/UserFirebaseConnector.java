package com.example.iutassistant.Model.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Model.User;
import com.example.iutassistant.Presenter.FirebaseUserPresenter;
import com.example.iutassistant.Model.Server.FirebaseDataBaseHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserFirebaseConnector extends DatabaseConnector implements FirebaseConnector {


    private FirebaseUserPresenter firebaseUserPresenter;
    private String path;
    private User user;
    private DatabaseReference databaseReference;

    public UserFirebaseConnector(FirebaseUserPresenter firebaseUserPresenter, String identity, String profession) {

        this.firebaseUserPresenter = firebaseUserPresenter;


        path=Constant.Ref+"/"+profession+"/"+identity;
        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);
        super.dataBaseHandler =new FirebaseDataBaseHandler(this,databaseReference);
    }



    @Override
    public void convertDataSnapShot(DataSnapshot dataSnapshot) {
            user=dataSnapshot.getValue(User.class);
            firebaseUserPresenter.useFireBaseUserModel(user);

    }

    @Override
    public void setErrorStatus(String error) {

    }

    @Override
    public void onDataNotExist() {

    }
}
