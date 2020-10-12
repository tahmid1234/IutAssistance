package com.example.iutassistant.Model;

public class CourseModel {
    private String credits;
    private String name;
    private String courseCode;

    public CourseModel() {
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }




    public String getCredits() {
        return credits;
    }

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }



    public CourseModel(String credits, String name) {
        this.credits = credits;
        this.name = name;
    }
}
