package com.example.iutassistant.Model.Connectors;

import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Model.Server.DataBaseHandler;

public abstract class DatabaseConnector {

    DataBaseHandler dataBaseHandler;
    public void postData(IModel iModel){
        dataBaseHandler.send(iModel);
    }
    public void getData(){
        dataBaseHandler.receive();
    }


}
