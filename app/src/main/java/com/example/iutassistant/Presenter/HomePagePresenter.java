package com.example.iutassistant.Presenter;

import android.content.SharedPreferences;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.User;
import com.example.iutassistant.View.IHomePageView;

public class HomePagePresenter implements IHomePagePresenter {

    public  IHomePageView iHomePageView;

    public HomePagePresenter(IHomePageView iHomePageView) {
        this.iHomePageView = iHomePageView;
    }

    @Override
    public void onOpeningHomePage(SharedPreferences userInfoCheck, SharedPreferences logInSTate) {

        if(!(userInfoCheck.getBoolean(Constant.user_exists_preference,false)))
        {

        }
    }
}
