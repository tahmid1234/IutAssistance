package com.example.iutassistant.SingleTone;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.iutassistant.Extra.Constant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class UserInfoSharedPreferenceSingleTone {
    private static UserInfoSharedPreferenceSingleTone instance;

     private UserInfoSharedPreferenceSingleTone() {
    }

    public static UserInfoSharedPreferenceSingleTone getInstance(){
        if(instance==null){
            synchronized (UserInfoSharedPreferenceSingleTone.class) {
                if (instance == null) {
                    instance = new UserInfoSharedPreferenceSingleTone();
                }
            }
        }
        return instance;
    }

    public static void storeUserInfo(SharedPreferences userInfoCheck,SharedPreferences logInState,String profession,FirebaseDatabase database,
            DatabaseReference ref,String uid){

        database = FirebaseDatabase.getInstance();
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = database.getReference().child("University/IUT").child(profession).child(uid);
        userInfoCheck.edit().putString(Constant.uid_preference,uid).apply();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    userInfoCheck.edit().putString(Constant.username_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_name).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_sid_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_id).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_prog_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_programme).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_sec_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_sec).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_dept_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_dept).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_university_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_uni).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_profession_preference, String.valueOf(dataSnapshot.child(Constant.userInfo_node_profession).getValue())).apply();
                    userInfoCheck.edit().putString(Constant.user_email_preference,String.valueOf(dataSnapshot.child(Constant.userInfo_node_email).getValue())).apply();
                    if(profession.equals("Students"))
                        userInfoCheck.edit().putString(Constant.user_batch_preference,getBatch(userInfoCheck.getString(Constant.user_sec_preference,"Not defined"))).apply();
                    userInfoCheck.edit().putBoolean(Constant.user_exists_preference, true).apply();
                    logInState.edit().putBoolean(Constant.user_login_state_shared_preference,true).apply();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);




    }

    public static String getBatch( String sec){
          String[] prog_batch=sec.split(" ");
          System.out.println(prog_batch[0]);
         return prog_batch[0];

    }
}
