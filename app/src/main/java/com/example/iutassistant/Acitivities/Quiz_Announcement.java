package com.example.iutassistant.Acitivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iutassistant.R;


public class Quiz_Announcement extends Fragment implements View.OnClickListener {


    public Quiz_Announcement() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz__announcement, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}