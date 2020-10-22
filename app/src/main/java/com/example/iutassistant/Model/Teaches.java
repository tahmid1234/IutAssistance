package com.example.iutassistant.Model;

public class Teaches implements IModel {
    private String course;
    private String teacherEmail;
    private String section;

    public Teaches() {
    }

    public Teaches(String course, String section) {
        this.course = course;
        this.section = section;
    }

    public Teaches(String course) {
        this.course = course;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCourse() {
        return course;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public String getSection() {
        return section;
    }
}
