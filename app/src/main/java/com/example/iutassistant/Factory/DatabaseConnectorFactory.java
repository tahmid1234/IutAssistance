package com.example.iutassistant.Factory;

import android.content.Context;

import com.example.iutassistant.Model.Connectors.ClassFirebaseConnector;
import com.example.iutassistant.Model.Connectors.DatabaseConnector;
import com.example.iutassistant.Model.Connectors.UserSPConnector;
import com.example.iutassistant.Presenter.IFirebaseClassPresenter;
import com.example.iutassistant.Presenter.SharedPreferenceUserPresenter;

public class DatabaseConnectorFactory {
    public DatabaseConnector getDatabaseConnector(String section,String course,String hint){
        return new ClassFirebaseConnector(section,course);
    }
    public DatabaseConnector getDatabaseConnector(IFirebaseClassPresenter firebaseClassPresenter ,String section, String course, String hint){
        return new ClassFirebaseConnector(firebaseClassPresenter,section,course);
    }
    public DatabaseConnector getDatabaseConnector(SharedPreferenceUserPresenter sharedPreferenceUserPresenter, String path, Context mContext, String hint){
        return new UserSPConnector(sharedPreferenceUserPresenter,path,mContext);
    }

}
