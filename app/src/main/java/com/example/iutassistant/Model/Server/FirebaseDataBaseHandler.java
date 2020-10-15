package com.example.iutassistant.Model.Server;

import androidx.annotation.NonNull;

import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Model.Connectors.FirebaseConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDataBaseHandler implements DataBaseHandler {

    public ValueEventListener getValueEventListener() {
        return valueEventListener;
    }

    private ValueEventListener valueEventListener;

    private DatabaseReference databaseReference;

    private String path;

    private FirebaseConnector firebaseConnector;

    public FirebaseDataBaseHandler(FirebaseConnector firebaseConnector,String path) {
        this.path=path;
        this.firebaseConnector=firebaseConnector;
        databaseReference= FirebaseDatabase.getInstance().getReference().child(path);

    }

    public FirebaseDataBaseHandler(DatabaseReference child) {
    }




    @Override
    public void receive() {

        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    firebaseConnector.convertDataSnapShot(dataSnapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);



    }

    @Override
    public void send(IModel model) {
        databaseReference.setValue(model);
    }
}
