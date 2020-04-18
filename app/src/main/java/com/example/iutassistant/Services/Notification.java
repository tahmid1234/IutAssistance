package com.example.iutassistant.Services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.iutassistant.OldNotiFication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

 public class Notification {

    final static private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final static private String serverKey = "key=" + "AAAA_9tBebo:APA91bHMWuBlfbUe58s7YBPCLX5S_DvvifAg9c3Pzz6orpxjegc8dfzsYGcOGR5rZ5PPYZ5WQCc1MjimyiOojEWfh6S50as3MvpiHSRh-4mPV-ZXluz8nyvd_WT2Im08CN0T8OTd6SO5";
    final static private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    String topic,title,content;
    Context context;

    public Notification(String topic,String title,String contetnt,Context context){
        this.topic=topic;
        this.title=title;
        this.content=contetnt;
        this.context=context;
    }

    public  void  setNotification(){
       // OldNotiFication.showNotification(context.getApplicationContext(), intent, 1, "Request", statement+"/n congratulation");
        String TOPIC = "/topics/"+topic;; //topic must match with what the receiver subscribed to
        String NOTIFICATION_TITLE = title;
        String NOTIFICATION_MESSAGE = content;
        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        System.out.println("j son object er vitor achi");
        try {
            notifcationBody.put("title", NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
            System.out.println("try er vitor achi");
        } catch (JSONException e) {
           // Log.e(TAG, "onCreate: " + e.getMessage() );
        }
        System.out.println("sned notir upor er vitor achi");
        sendNotification(notification,context);

    }
    private  void sendNotification(JSONObject notification,Context context) {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(FCM_API, notification, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Request error", Toast.LENGTH_LONG).show();
                       // Log.i(TAG, "onErrorResponse: Didn't work");

                    }
                })


        {
            @Override
            public Map<String, String> getHeaders () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        System.out.println("Single ton er vitor achi");
        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjectRequest);


    }


}
