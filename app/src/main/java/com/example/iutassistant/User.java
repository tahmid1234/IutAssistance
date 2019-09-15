package com.example.iutassistant;

public class User {
    private String name,id,dept,prog,uni,profession,sec;

    public User() {
    }

    public User(String id, String name, String dept, String prog, String sec, String uni,String profession) {
        this.name = name;
        this.id = id;
        this.dept = dept;
        this.prog = prog;
        this.uni = uni;
        this.profession = profession;
        this.sec=sec;
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
