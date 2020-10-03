package com.example.iutassistant.Presenter.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Model.IUser;
import com.example.iutassistant.Model.User;
import com.example.iutassistant.Presenter.FirebaseUserPresenter;
import com.example.iutassistant.Server.FirebaseDataBaseHandler;
import com.google.firebase.database.DataSnapshot;

public class UserFirebaseConnector extends DatabaseConnector implements FirebaseConnector {


    private FirebaseUserPresenter firebaseUserPresenter;
    private String path;
    private User user;

    public UserFirebaseConnector(FirebaseUserPresenter firebaseUserPresenter, String identity, String profession) {

        this.firebaseUserPresenter = firebaseUserPresenter;


        path=Constant.Ref+"/"+profession+"/"+identity;
        super.dataBaseHandler =new FirebaseDataBaseHandler(this,path);
    }

    @Override
    public void postData(IModel iModel) {
            super.postData(iModel);
    }

    @Override
    public void getData() {

        super.getData();
    }

    @Override
    public void convertDataSnapShot(DataSnapshot dataSnapshot) {
            user=dataSnapshot.getValue(User.class);
            firebaseUserPresenter.useFireBaseUserModel(user);

    }
}
