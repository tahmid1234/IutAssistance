package com.example.iutassistant.Model.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.CourseModel;
import com.example.iutassistant.Model.Section;
import com.example.iutassistant.Model.Server.FirebaseDataBaseHandler;
import com.example.iutassistant.Presenter.IFirebaseSectionListPresenter;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SectionListFirebaseConnector extends DatabaseConnector implements FirebaseConnector {

    IFirebaseSectionListPresenter firebaseSectionListPresenter;
    private String path;
    private List<Section> sections;

    public SectionListFirebaseConnector(IFirebaseSectionListPresenter firebaseSectionListPresenter) {
        this.firebaseSectionListPresenter = firebaseSectionListPresenter;
        path= Constant.Ref+"/"+Constant.Section_Node;
        super.dataBaseHandler =new FirebaseDataBaseHandler(this,path);
        sections=new ArrayList<Section>();
    }



    @Override
    public void convertDataSnapShot(DataSnapshot dataSnapshot) {

        for(DataSnapshot sectionDataSnapshot:dataSnapshot.getChildren()){
            String key=sectionDataSnapshot.getKey();
            Section section=new Section();
            section.setSectionName(key);
            sections.add(section);
        }
        firebaseSectionListPresenter.useFirebaseSectionlList(sections);
    }
}
