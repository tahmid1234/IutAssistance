package com.example.iutassistant.Acitivities;

public class Attendance_detail {
    String crs,attendace_count,date;
    Attendance_detail(){}
    Attendance_detail(String crs,String date,String attendance_count){
        this.crs=crs;
        this.attendace_count=attendance_count;
        this.date=date;

    }

    public String getAttendace_count() {
        return attendace_count;
    }

    public String getCrs() {
        return crs;
    }

    public String getDate() {
        return date;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }

    public void setAttendace_count(String attendace_count) {
        this.attendace_count = attendace_count;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
