package com.example.iutassistant.Acitivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.iutassistant.R;


public class Assignment_Announcement extends Fragment implements View.OnClickListener {

    private EditText assignmentNo, deadline, media, description;

    private Button AssignmentPost;


    public Assignment_Announcement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assignment__announcement, container, false);

        assignmentNo = view.findViewById(R.id.et_AssignmentNo);
        deadline = view.findViewById(R.id.et_deadline);
        media = view.findViewById(R.id.et_media);
        description = view.findViewById(R.id.et_description);


        AssignmentPost = view.findViewById(R.id.assignmentPost);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}