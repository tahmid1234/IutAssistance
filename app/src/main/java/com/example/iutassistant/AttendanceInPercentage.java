package com.example.iutassistant;

public class AttendanceInPercentage {

    String crs;
    Double attendanceInPercentage;
    AttendanceInPercentage(){

    }

    public String getCrs() {
        return crs;
    }

    public double getAttendanceInPercentage() {
        return attendanceInPercentage;
    }

    AttendanceInPercentage(String crs, Double attendanceInPercentage){
        this.crs=crs;
        this.attendanceInPercentage=attendanceInPercentage;
    }
}
