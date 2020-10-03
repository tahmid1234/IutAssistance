package com.example.iutassistant.Presenter.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Model.IdentityModel;
import com.example.iutassistant.Presenter.FireBaseIdentityPresenter;
import com.example.iutassistant.Server.FirebaseDataBaseHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IdentityFirebaseConnector extends DatabaseConnector implements FirebaseConnector {
    DatabaseReference databaseReference;
    IdentityModel identityModel;
    FireBaseIdentityPresenter fireBaseIdentityPresenter;
    String path;
    public IdentityFirebaseConnector(FireBaseIdentityPresenter fireBaseIdentityPresenter, String uid) {
        this.fireBaseIdentityPresenter = fireBaseIdentityPresenter;

        path=Constant.Ref+"/"+Constant.IDENTITY_NODE+"/"+uid;
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

          identityModel=  dataSnapshot.getValue(IdentityModel.class);
          fireBaseIdentityPresenter.useFireBaseIdentityModel(identityModel);

    }
}
