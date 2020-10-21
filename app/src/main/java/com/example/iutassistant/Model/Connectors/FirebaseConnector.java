package com.example.iutassistant.Model.Connectors;

import com.google.firebase.database.DataSnapshot;

public interface FirebaseConnector  {
    public  void convertDataSnapShot(DataSnapshot dataSnapshot);

    public  void setErrorStatus(String error);

    public  void onDataNotExist();
}
