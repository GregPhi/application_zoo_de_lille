package com.example.zoodelille.data.api.object.quiz;


import com.example.zoodelille.data.api.object.quiz.question.Question;

import java.util.List;

public class Quiz {
    private int id;
    private String name;
    private List<Question> questions;
    private boolean make;

    public Quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean isMake() {
        return make;
    }

    public void setMake(boolean make) {
        this.make = make;
    }

    public void setMake(){
        this.make = true;
    }
}
