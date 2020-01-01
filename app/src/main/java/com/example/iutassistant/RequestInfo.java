package com.example.iutassistant;

public class RequestInfo {
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

    RequestInfo(String sid, String uid, String secName){
            this.sid=sid;
            this.uid=uid;
            this.secName=secName;
    }
}
