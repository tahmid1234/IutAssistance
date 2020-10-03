package com.example.iutassistant.Model;

import com.example.iutassistant.Presenter.IHomePagePresenter;

public class User implements IUser,IModel{


    private String name, uid,dept, programme,uni,profession,sec,email;

    public User() {
    }




    public User(String uid, String name, String sec, String programme, String dept, String profession, String uni, String email) {
        this.name = name;
        this.uid = uid;
        this.dept = dept;
        this.programme = programme;

        this.profession = profession;
        this.sec=sec;
        this.uni=uni;
        this.email=email;
    }

    public User(String uid, String name, String dept, String profession,String uni,String email) {
        this.name = name;
        this.uid = uid;
        this.dept = dept;

        this.profession = profession;

        this.uni=uni;
        this.email=email;


    }

    public User(IHomePagePresenter homePagePresenter) {
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getDept() {
        return dept;
    }

    public String getProgramme() {
        return programme;
    }

    public String getUni() {
        return uni;
    }

    public String getProfession() {
        return profession;
    }

    public String getSec() {
        return sec;
    }

    public  String getEmailExtention(){ return email.substring(0,email.length()-5);}

    public  String getEmailDomain(){ return email.substring(email.length()-4);}

    public String getEmail() {
        return email;
    }
}
