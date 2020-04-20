package com.example.iutassistant.Model;

import java.io.Serializable;

public class QuizInfo implements Serializable {
    String t_id,syllabus,quizDate,quiz_no;
    QuizInfo(){}
    public QuizInfo(String t_id, String syllabus, String quizDate, String quiz_no){
        this.t_id=t_id;
        this.quizDate=quizDate;
        this.syllabus=syllabus;
        this.quiz_no=quiz_no;
    }

    public String getT_id() {
        return t_id;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getQuizDate() {
        return quizDate;
    }

    public String getQuiz_no() {
        return quiz_no;
    }
}
