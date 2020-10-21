package com.example.iutassistant.Presenter;

import com.example.iutassistant.Model.AssignedClass;
import com.example.iutassistant.Model.CourseModel;

import java.util.List;

public interface IFirebaseClassPresenter {
    public void useFireBaseAssignedClass(AssignedClass assignedClass);
    public void onAssignedClassNotFound();
}
