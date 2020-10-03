package com.example.iutassistant.Presenter.Connectors;

import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Server.DataBaseHandler;
import com.google.firebase.database.DataSnapshot;

public abstract class DatabaseConnector {

    DataBaseHandler dataBaseHandler;
    public void postData(IModel iModel){
        dataBaseHandler.send(iModel);
    }
    public void getData(){
        dataBaseHandler.receive();
    }


}
