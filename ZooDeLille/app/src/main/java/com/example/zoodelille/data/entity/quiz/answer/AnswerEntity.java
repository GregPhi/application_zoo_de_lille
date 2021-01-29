package com.example.zoodelille.data.entity.quiz.answer;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AnswerEntity {
    @NonNull
    @PrimaryKey
    private int id;
    private String answer;
    private String url_picture;
    private int question_id;
    private boolean isGood;

    public AnswerEntity() {
        isGood = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUrl_picture() {
        return url_picture;
    }

    public void setUrl_picture(String url_picture) {
        this.url_picture = url_picture;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public void isGoodTrue(){
        isGood = true;
    }
}