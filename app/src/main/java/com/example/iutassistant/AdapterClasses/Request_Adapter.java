package com.example.iutassistant.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.iutassistant.LogIn;
import com.example.iutassistant.R;
import com.example.iutassistant.RequestInfo;
import com.example.iutassistant.Services.Notification;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Request_Adapter extends BaseAdapter {

    ArrayList<RequestInfo> requestInfo=new ArrayList<>();
    private Context mContext;
    String sid,uid,sec,ref="University/IUT";
    Button accept,reject;

    public Request_Adapter(Context mContext, ArrayList<RequestInfo> requestInfo) {
        this.requestInfo=requestInfo;
        this.mContext=mContext;
        System.out.println(requestInfo.size()+" ****atttttt  size 2*****");
    }
    @Override
    public int getCount() {
        return requestInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.request_list,viewGroup,false);}

        TextView reqId = view.findViewById(R.id.requestSenderID);
         accept =view.findViewById(R.id.accept);
         reject=view.findViewById(R.id.reject);

        uid=requestInfo.get(i).getUid();
        sid=requestInfo.get(i).getSid();
        sec=requestInfo.get(i).getSecName();

        reqId.setText(sid);
        accept(i);
        reject(i);





        return view;
    }

    void accept(int i){
        final int j=i;
        accept.setOnClickListener(new View.OnClickListener() {

            {System.out.println("accepted bujhlaaaaaaaaaaaaaaa");}
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference(ref).child("Students").child(uid).child("sec").setValue(sec);
                FirebaseDatabase.getInstance().getReference(ref).child("REQUEST").child(sec).child(sid).removeValue();
                requestInfo.remove(j);
                Notification notification=new Notification(uid+"Request","Accepted","You have become a member of "+sec, LogIn.contextLogin);
                notification.setNotification();

            }
        });
    }
    void reject(int i){
        final int j=i;
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference(ref).child("Students").child(uid).child("sec").setValue("REJECTED");
                FirebaseDatabase.getInstance().getReference(ref).child("REQUEST").child(sec).child(sid).removeValue();
                requestInfo.remove(j);
                Notification notification=new Notification(uid+"Request","Rejected",sec+" has rejected your request", LogIn.contextLogin);
                notification.setNotification();

            }
        });
    }

}
