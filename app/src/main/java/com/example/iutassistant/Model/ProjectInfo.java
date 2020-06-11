package com.example.iutassistant.Model;

import java.io.Serializable;

public class ProjectInfo implements Serializable {
    private String ProjectName,Course,SupervisorMail,AllSid,Description,Project_Crs,Batch_prog;

    public ProjectInfo(String projectName, String course, String allSid, String description, String project_Crs, String batch_prog) {
        ProjectName = projectName;
        Course = course;
        AllSid = allSid;
        Description = description;
        Project_Crs = project_Crs;
        Batch_prog = batch_prog;
    }

    public String getProject_Crs() {
        return Project_Crs;
    }

    public String getBatch_prog() {
        return Batch_prog;
    }

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
