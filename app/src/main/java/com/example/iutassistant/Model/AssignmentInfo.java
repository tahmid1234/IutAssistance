package com.example.iutassistant.Model;

import java.io.Serializable;

public class AssignmentInfo implements Serializable {

    String t_id,media,explanation,submissionDate;

    public AssignmentInfo(String t_id, String media, String explanation, String submissionDate) {
        this.t_id = t_id;
        this.media = media;
        this.explanation = explanation;
        this.submissionDate = submissionDate;
    }

    public AssignmentInfo() {
    }

    public String getT_id() {
        return t_id;
    }

    public String getMedia() {
        return media;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }
}

