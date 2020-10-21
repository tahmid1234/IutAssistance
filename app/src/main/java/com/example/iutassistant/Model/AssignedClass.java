package com.example.iutassistant.Model;

public class AssignedClass implements IModel {
    private String teachersEmail;
    private String section;
    private String course;

    public AssignedClass() {
    }

    public AssignedClass(String teachersEmail) {
        this.teachersEmail = teachersEmail;
    }

    public AssignedClass(String section, String course) {
        this.section = section;
        this.course = course;
    }

    public AssignedClass(String teachersEmail, String section, String course) {
        this.teachersEmail = teachersEmail;
        this.section = section;
        this.course = course;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeachersEmail() {
        return teachersEmail;
    }

    public String getSection() {
        return section;
    }

    public String getCourse() {
        return course;
    }
}
