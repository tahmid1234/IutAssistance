package com.example.iutassistant.Model.Connectors;

import com.example.iutassistant.Extra.Constant;
import com.example.iutassistant.Model.CourseModel;
import com.example.iutassistant.Model.Section;
import com.example.iutassistant.Model.Server.FirebaseDataBaseHandler;
import com.example.iutassistant.Presenter.IFirebaseSectionListPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SectionListFirebaseConnector extends DatabaseConnector implements FirebaseConnector {

    IFirebaseSectionListPresenter firebaseSectionListPresenter;
    private String path;
    private List<Section> sections;
    DatabaseReference databaseReference;

    public SectionListFirebaseConnector(IFirebaseSectionListPresenter firebaseSectionListPresenter) {
        this.firebaseSectionListPresenter = firebaseSectionListPresenter;
        path= Constant.Ref+"/"+Constant.Section_Node;
        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);
        super.dataBaseHandler =new FirebaseDataBaseHandler(this,databaseReference);
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

    @Override
    public void setErrorStatus(String error) {

    }

    @Override
    public void onDataNotExist() {

    }
}
