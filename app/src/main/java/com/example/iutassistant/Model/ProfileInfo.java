package com.example.iutassistant.Model;

import java.io.Serializable;

public class ProfileInfo implements Serializable {
    public String  name, id,sec,programme,deptment,uni,profession;

    public ProfileInfo(String name, String id,   String deptment, String uni, String profession) {
        this.name = name;
        this.id = id;


        this.deptment = deptment;
        this.uni = uni;
        this.profession = profession;
    }

    public ProfileInfo(){}

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSec() {
        return sec;
    }

    public String getProgramme() {
        return programme;
    }

    public String getDeptment() {
        return deptment;
    }

    public String getUni() {
        return uni;
    }

    public String getProfession() {
        return profession;
    }
}
