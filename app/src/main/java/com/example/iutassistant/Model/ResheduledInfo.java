package com.example.iutassistant.Model;

import java.io.Serializable;

public class ResheduledInfo implements Serializable {
    String t_id,type,explanation,date;
    public ResheduledInfo(String t_id,String type,String explanation,String date){
        this.t_id=t_id;
        this.type=type;
        this.explanation=explanation;
        this.date=date;

    }

    public ResheduledInfo() {
    }

    public String getT_id() {
        return t_id;
    }

    public String getType() {
        return type;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDate() {
        return date;
    }
}
