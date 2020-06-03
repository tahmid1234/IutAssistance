package com.example.iutassistant.Model;

import java.io.Serializable;

public class ProjectInfo implements Serializable {
    private String ProjectName,Course,SupervisorMail,AllSid,Description;

    public ProjectInfo() {

    }

    public ProjectInfo(String projectName, String course, String supervisorMail, String allSid, String description) {
        ProjectName = projectName;
        Course = course;
        SupervisorMail = supervisorMail;
        AllSid = allSid;
        Description = description;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public String getCourse() {
        return Course;
    }

    public String getSupervisorMail() {
        return SupervisorMail;
    }

    public String getAllSid() {
        return AllSid;
    }

    public String getDescription() {
        return Description;
    }
}
