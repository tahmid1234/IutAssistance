package com.example.iutassistant.Model.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.IdentityModel;
import com.example.iutassistant.Presenter.FireBaseIdentityPresenter;
import com.example.iutassistant.Model.Server.FirebaseDataBaseHandler;
import com.example.iutassistant.Presenter.Presenter;
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
        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);

        super.dataBaseHandler =new FirebaseDataBaseHandler(this,databaseReference);
    }



    @Override
    public void convertDataSnapShot(DataSnapshot dataSnapshot) {

          identityModel=  dataSnapshot.getValue(IdentityModel.class);
          fireBaseIdentityPresenter.useFireBaseIdentityModel(identityModel);

    }

    @Override
    public void setErrorStatus(String status) {
        Presenter presenter= (Presenter) fireBaseIdentityPresenter;
        ((Presenter) fireBaseIdentityPresenter).sendWarning(status);
    }

    @Override
    public void onDataNotExist() {

    }
}
