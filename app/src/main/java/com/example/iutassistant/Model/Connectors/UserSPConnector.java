package com.example.iutassistant.Model.Connectors;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Model.User;
import com.example.iutassistant.Presenter.SharedPreferenceUserPresenter;
import com.example.iutassistant.Model.Server.SharedPreferenceHandler;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class UserSPConnector extends DatabaseConnector implements ISharePreferenceConnector {

    private SharedPreferenceUserPresenter sharedPreferenceUserPresenter;
    private SharedPreferences userInfoSp;
    private Context mContext;
    private User user;
    private String path;

    public UserSPConnector( SharedPreferenceUserPresenter sharedPreferenceUserPresenter, String path, Context mContext) {
        this.sharedPreferenceUserPresenter = sharedPreferenceUserPresenter;
        this.path = path;
        userInfoSp=mContext.getSharedPreferences(Constant.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        super.dataBaseHandler=new SharedPreferenceHandler(this,userInfoSp,path);

    }





    public UserSPConnector(){

    }

    @Override
    public void convertJson(String json) {
        Gson gson = new Gson();
        user = gson.fromJson(json, User.class);
        //userPresenter.useUser(user);
        sharedPreferenceUserPresenter.useSpUserModel(user);
        System.out.println(json+" hdksh"+ user.getDept()+" dshfb"+ user.getEmail());
    }
}
