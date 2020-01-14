package com.example.iutassistant;

import android.widget.EditText;

public class SupervisionInfo {
    String grpName, memebers,  mate1,  mate2, mate3,  mate4,  leader,  desc,  selectedCrs;

    public String getGrpName() {
        return grpName;
    }

    public String getMemebers() {
        return memebers;
    }

    public String getMate1() {
        return mate1;
    }

    public String getMate2() {
        return mate2;
    }

    public String getMate3() {
        return mate3;
    }

    public String getMate4() {
        return mate4;
    }

    public String getLeader() {
        return leader;
    }

    public String getDesc() {
        return desc;
    }

    public String getSelectedCrs() {
        return selectedCrs;
    }

    public SupervisionInfo(String grpName, String memebers, String mate1, String leader, String desc, String crs) {
        this.grpName=grpName;
        this.memebers=memebers;
        this.mate1=mate1;

        this.leader=leader;
        this.desc=desc;
        this.selectedCrs=selectedCrs;
    }

    public SupervisionInfo(String grpName, String memebers, String mate1, String mate2, String leader, String desc, String selectedCrs) {
        this.grpName=grpName;
        this.memebers=memebers;
        this.mate1=mate1;
        this.mate2=mate2;

        this.leader=leader;
        this.desc=desc;
        this.selectedCrs=selectedCrs;
    }

    public SupervisionInfo(String grpName, String memebers, String mate1, String mate2, String mate3, String leader, String desc, String selectedCrs) {
        this.grpName=grpName;
        this.memebers=memebers;
        this.mate1=mate1;
        this.mate2=mate2;
        this.mate3=mate3;

        this.leader=leader;
        this.desc=desc;
        this.selectedCrs=selectedCrs;
    }

    public SupervisionInfo(String grpName, String memebers, String mate1, String mate2, String mate3, String mate4, String leader, String desc, String selectedCrs) {
        this.grpName=grpName;
        this.memebers=memebers;
        this.mate1=mate1;
        this.mate2=mate2;
        this.mate3=mate3;
        this.mate4=mate4;
        this.leader=leader;
        this.desc=desc;
        this.selectedCrs=selectedCrs;
    }
}
