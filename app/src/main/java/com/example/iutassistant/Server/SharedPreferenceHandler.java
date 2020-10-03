package com.example.iutassistant.Server;

import android.content.SharedPreferences;

import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Presenter.Connectors.DatabaseConnector;
import com.example.iutassistant.Presenter.Connectors.ISharePreferenceConnector;
import com.google.gson.Gson;

public class SharedPreferenceHandler implements DataBaseHandler {

    SharedPreferences sharedPreference;
    ISharePreferenceConnector iSharePreferenceConnector;
    String path;
    public SharedPreferenceHandler(ISharePreferenceConnector iSharePreferenceConnector,SharedPreferences sharedPreference,String path){
        this.sharedPreference=sharedPreference;
        this.iSharePreferenceConnector=iSharePreferenceConnector;
        this.path=path;
    }

    @Override
    public void receive() {

        String json = sharedPreference.getString(path, "");
        iSharePreferenceConnector.convertJson(json);

    }

    @Override
    public void send(IModel model) {
        SharedPreferences.Editor prefsEditor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson(model);
        prefsEditor.putString(path, json);
        prefsEditor.commit();
    }
}
