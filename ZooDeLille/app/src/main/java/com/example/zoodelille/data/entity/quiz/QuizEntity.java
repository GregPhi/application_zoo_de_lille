package com.example.zoodelille.data.entity.quiz;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuizEntity {
    @NonNull
    @PrimaryKey
    private int quiz_id;
    private String quiz_name;
    private String levelQuiz;
    private int best_score;
    private boolean make;

    public QuizEntity() {
        this.make = false;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int id) {
        this.quiz_id = id;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String name) {
        this.quiz_name = name;
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

    public String getLevelQuiz() {
        return levelQuiz;
    }

    public void setLevelQuiz(String levelQuiz) {
        this.levelQuiz = levelQuiz;
    }

    public int getBest_score() {
        return best_score;
    }

    public void setBest_score(int best_score) {
        this.best_score = best_score;
    }
}
