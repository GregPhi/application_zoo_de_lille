package com.example.zoodelille.data.api.object.quiz.question;


import com.example.zoodelille.data.api.object.quiz.answer.Answer;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String question;
    private String url_extra;
    private List<Answer> answers;
    private boolean right_answer;

    public Question() {
        this.answers = new ArrayList<>();
        this.right_answer = false;
    }

    public Question(int id, String question, String url_extra, List<Answer> answers, boolean right_answer) {
        this.id = id;
        this.question = question;
        this.url_extra = url_extra;
        this.answers = answers;
        this.right_answer = right_answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUrl_extra() {
        return url_extra;
    }

    public void setUrl_extra(String url_extra) {
        this.url_extra = url_extra;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isRight_answer() {
        return right_answer;
    }

    public void setRight_answer(boolean right_answer) {
        this.right_answer = right_answer;
    }

    public void setRight_answer(){
        this.right_answer = true;
    }
}
