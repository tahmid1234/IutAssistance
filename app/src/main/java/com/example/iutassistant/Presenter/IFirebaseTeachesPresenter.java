package com.example.iutassistant.Presenter;

import com.example.iutassistant.Model.AssignedClass;
import com.example.iutassistant.Model.Teaches;

public interface IFirebaseTeachesPresenter {

    public void useFireBaseTeaches(Teaches teaches);
    public void onTeachesDataNotFound();
}
