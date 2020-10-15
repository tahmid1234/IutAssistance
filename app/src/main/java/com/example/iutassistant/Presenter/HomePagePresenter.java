package com.example.iutassistant.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.iutassistant.Model.IdentityModel;
import com.example.iutassistant.Model.User;
import com.example.iutassistant.Model.Connectors.DatabaseConnector;
import com.example.iutassistant.Model.Connectors.IdentityFirebaseConnector;
import com.example.iutassistant.Model.Connectors.UserFirebaseConnector;
import com.example.iutassistant.Model.Connectors.UserSPConnector;
import com.example.iutassistant.View.IHomePageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class HomePagePresenter implements IHomePagePresenter, FireBaseIdentityPresenter, FirebaseUserPresenter,SharedPreferenceUserPresenter {

    private   IHomePageView iHomePageView;
    private   User user;
    private DatabaseConnector databaseConnector;
    private DatabaseReference databaseReference;
    private SharedPreferences userInfoCheck,userInfoSp;
    private SharedPreferences logInSTate;
    private String uid;
    private String profession;
    private Context context;

    public HomePagePresenter(IHomePageView iHomePageView, SharedPreferences userInfoCheck, SharedPreferences logInSTate,  String profession, Context context) {
        this.iHomePageView = iHomePageView;
        this.userInfoCheck = userInfoCheck;
        this.logInSTate = logInSTate;
        this.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        this.profession = profession;
        this.context=context;
    }

    public HomePagePresenter(){}




    @Override
    public void onOpeningHomePage() {

       // if(!(userInfoCheck.getBoolean(Constant.user_exists_preference,false)))

        databaseConnector=new IdentityFirebaseConnector(this,uid);
        databaseConnector.getData();




    }

    public  void useFireBaseIdentityModel(IdentityModel identityModel){

            databaseConnector=new UserFirebaseConnector(this,identityModel.getIdentity(),profession);
            databaseConnector.getData();


    }

    @Override
    public void useFireBaseUserModel(User user) {

            databaseConnector=new UserSPConnector(this,uid,context);
            databaseConnector.postData(user);
            databaseConnector.getData();

    }

    @Override
    public void useSpUserModel(User user) {
            //implemet
    }
}
