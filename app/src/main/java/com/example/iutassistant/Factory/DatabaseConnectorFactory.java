package com.example.iutassistant.Factory;

import android.content.Context;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.Connectors.ClassFirebaseConnector;
import com.example.iutassistant.Model.Connectors.DatabaseConnector;
import com.example.iutassistant.Model.Connectors.TeachesFirebaseConnector;
import com.example.iutassistant.Model.Connectors.TeachesSharedPreferenceConnector;
import com.example.iutassistant.Model.Connectors.UserSPConnector;
import com.example.iutassistant.Presenter.IFirebaseClassPresenter;
import com.example.iutassistant.Presenter.IFirebaseTeachesPresenter;
import com.example.iutassistant.Presenter.ISharedPreferenceTeachesPresenter;
import com.example.iutassistant.Presenter.SharedPreferenceUserPresenter;

public class DatabaseConnectorFactory {


    public DatabaseConnector getDatabaseConnector(IFirebaseTeachesPresenter firebaseTeachesPresenter , String email, String section, String hint){

        return new TeachesFirebaseConnector(firebaseTeachesPresenter,email,section);


    }

    public DatabaseConnector getDatabaseConnector(String parent,String child,String hint){
        if(hint.equals(Constant.CLASS_FIREBASE_CONNECTOR))
            return new ClassFirebaseConnector(parent,child);
        else
            return new TeachesFirebaseConnector(parent,child);
    }
    public DatabaseConnector getDatabaseConnector(IFirebaseClassPresenter firebaseClassPresenter ,String section, String course, String hint){

            return new ClassFirebaseConnector(firebaseClassPresenter,section,course);


    }
    public DatabaseConnector getDatabaseConnector(SharedPreferenceUserPresenter sharedPreferenceUserPresenter, String path, Context mContext, String hint){
        return new UserSPConnector(sharedPreferenceUserPresenter,path,mContext);
    }

    public DatabaseConnector getDatabaseConnector(ISharedPreferenceTeachesPresenter sharedPreferenceTeachesPresenter, Context mContext, String path, String hint){
        return new TeachesSharedPreferenceConnector(sharedPreferenceTeachesPresenter,mContext,path);
    }

    public DatabaseConnector getDatabaseConnector( Context mContext, String path, String hint){
        return new TeachesSharedPreferenceConnector(mContext,path);
    }

}
