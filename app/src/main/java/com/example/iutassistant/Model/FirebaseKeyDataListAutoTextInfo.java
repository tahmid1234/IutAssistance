package com.example.iutassistant.Model;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseKeyDataListAutoTextInfo {

    DatabaseReference databaseReference;
    List<String>  dataList = new ArrayList<String>();
    String  key;
    ArrayAdapter<String> dataAdapter;




    public void getDataList(int i,Context mContext, AutoCompleteTextView autoCompleteTextView, DatabaseReference databaseReference){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                if(dataSnapshot.exists()){
                    dataList.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        key=snapshot.getKey();

                        dataList.add(key);


                    }
                    System.out.println(dataList);
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, dataList);

                    System.out.println(autoCompleteTextView+" "+i);
                    autoCompleteTextView.setThreshold(1);
                    autoCompleteTextView.setAdapter(dataAdapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



}
