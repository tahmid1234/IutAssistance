package com.example.iutassistant.Model.Server;

import androidx.annotation.NonNull;

import com.example.iutassistant.Model.IModel;
import com.example.iutassistant.Model.Connectors.FirebaseConnector;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDataBaseHandler implements DataBaseHandler {

    public ValueEventListener getValueEventListener() {
        return valueEventListener;
    }

    private ValueEventListener valueEventListener;

    private DatabaseReference databaseReference;

    private String path;

    private FirebaseConnector firebaseConnector;



    public FirebaseDataBaseHandler(FirebaseConnector firebaseConnector,DatabaseReference databaseReference) {

        this.firebaseConnector=firebaseConnector;
        this.databaseReference= databaseReference;


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
                else {
                    firebaseConnector.onDataNotExist();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                firebaseConnector.setErrorStatus(databaseError.toString());
            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);



    }

    @Override
    public void send(IModel model) {
        firebaseConnector.setErrorStatus("true");
        databaseReference.setValue(model).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                firebaseConnector.setErrorStatus(e.toString());
            }
        });
    }
}
