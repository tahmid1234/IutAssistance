package com.example.iutassistant.View;

import com.example.iutassistant.Model.CourseModel;
import com.example.iutassistant.Model.Section;

import java.util.List;

public interface IClassAssignmentPopUpView {

    public void makeCourseListSpinner(List<String> courseModels);

    public void makeSectionListSpinner(List<String> sections);

    public void showWarning(String warning);
}
