package com.example.iutassistant.Model;

public class Section implements IModel {
    private String totalStudents;
    private String sectionName;

    public Section(String totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Section() {
    }

    public String getTotalStudents() {
        return totalStudents;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
