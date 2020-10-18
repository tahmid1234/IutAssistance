package com.example.iutassistant.Acitivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.iutassistant.R;

public class Taken_Course extends Fragment implements View.OnClickListener {

    private ListView Courses;


    public Taken_Course() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taken__course, container, false);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}