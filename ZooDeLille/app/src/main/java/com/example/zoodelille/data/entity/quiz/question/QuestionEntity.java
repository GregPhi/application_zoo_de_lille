package com.example.zoodelille.data.entity.quiz.question;

import com.example.zoodelille.data.entity.quiz.QuizEntity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity
public class QuestionEntity {
    @NonNull
    @PrimaryKey
    private int question_id;
    private String question;
    private String url_extra;
    @ForeignKey(entity = QuizEntity.class,parentColumns = "quiz_id",childColumns = "quizid",onDelete = ForeignKey.CASCADE)
    private int quizid;
    private boolean right_answer;

    public QuestionEntity() {}

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
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

    public int getQuizid() {
        return quizid;
    }

    public void setQuizid(int quizid) {
        this.quizid = quizid;
    }

    public boolean isRight_answer() {
        return right_answer;
    }

    public void setRight_answer(boolean right_answer) {
        this.right_answer = right_answer;
    }
}
