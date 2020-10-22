package com.example.iutassistant.Model.Connectors;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.Server.SharedPreferenceHandler;
import com.example.iutassistant.Model.Teaches;
import com.example.iutassistant.Model.User;
import com.example.iutassistant.Presenter.ISharedPreferenceTeachesPresenter;
import com.example.iutassistant.Presenter.SharedPreferenceUserPresenter;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class TeachesSharedPreferenceConnector extends DatabaseConnector implements ISharePreferenceConnector {

    private ISharedPreferenceTeachesPresenter sharedPreferenceTeachesPresenter;
    private SharedPreferences sharedPreferences;
    private Context mContext;
    private Teaches teaches;
    private String path;

    public TeachesSharedPreferenceConnector( Context mContext, String path) {
        this.mContext = mContext;
        this.path = path;
        sharedPreferences=mContext.getSharedPreferences(Constant.ASSIGNED_CLASSES_SHARED_PREFERENCES,MODE_PRIVATE);
        super.dataBaseHandler=new SharedPreferenceHandler(this,sharedPreferences,path);
    }

    public TeachesSharedPreferenceConnector(ISharedPreferenceTeachesPresenter sharedPreferenceTeachesPresenter, Context mContext, String path) {
        this.sharedPreferenceTeachesPresenter = sharedPreferenceTeachesPresenter;
        this.mContext = mContext;
        this.path = path;
        sharedPreferences=mContext.getSharedPreferences(Constant.ASSIGNED_CLASSES_SHARED_PREFERENCES,MODE_PRIVATE);
        super.dataBaseHandler=new SharedPreferenceHandler(this,sharedPreferences,path);
    }

    @Override
    public void convertJson(String json) {
        Gson gson = new Gson();

        teaches = gson.fromJson(json, Teaches.class);




        sharedPreferenceTeachesPresenter.useSharedPreferenceTeachesModel(teaches);
    }
}
