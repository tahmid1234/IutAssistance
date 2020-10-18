package com.example.iutassistant.Acitivities;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iutassistant.R;


public class Quiz_Announcement extends Fragment implements View.OnClickListener {

    private EditText quizNo, syllabus, date, time;

    private Button AssignmentPost;


    public Quiz_Announcement() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz__announcement, container, false);

        quizNo = view.findViewById(R.id.et_quizNo);
        syllabus = view.findViewById(R.id.et_quizSyllabus);
        date = view.findViewById(R.id.et_quizDate);
        time = view.findViewById(R.id.et_quizTime);


        AssignmentPost = view.findViewById(R.id.quizPost);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}