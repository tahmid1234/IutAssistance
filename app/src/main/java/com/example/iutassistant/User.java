package com.example.iutassistant;

public class User {


    private String name,id,dept,prog,uni,profession,sec,uid;

    public User() {
    }
    public void setUni(String uni) {
        this.uni = uni;
    }

    public User(String id,String uid){

        this.uid=uid;
        this.id=id;
    }

    public User(String id, String name, String sec, String prog, String dept, String profession,String uni) {
        this.name = name;
        this.id = id;
        this.dept = dept;
        this.prog = prog;

        this.profession = profession;
        this.sec=sec;
        this.uni=uni;
    }

    public User(String id, String name, String dept, String profession,String uni) {
        this.name = name;
        this.id = id;
        this.dept = dept;


        this.profession = profession;

        this.uni=uni;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDept() {
        return dept;
    }

    public String getProg() {
        return prog;
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
}
