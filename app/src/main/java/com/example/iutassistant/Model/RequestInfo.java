package com.example.iutassistant.Model;

import java.io.Serializable;

public class RequestInfo implements Serializable {
    public RequestInfo() {
    }

    String sid,uid,secName;

    public String getSid() {
        return sid;
    }

    public String getUid() {
        return uid;
    }

    public String getSecName() {
        return secName;
    }

    public RequestInfo(String sid, String uid, String secName){
            this.sid=sid;
            this.uid=uid;
            this.secName=secName;
    }

}
