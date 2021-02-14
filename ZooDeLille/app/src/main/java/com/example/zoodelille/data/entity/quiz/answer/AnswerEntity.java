package com.example.zoodelille.data.entity.quiz.answer;

import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class AnswerEntity {
    @NonNull
    @PrimaryKey
    private int answer_id;
    private String answer;
    private String url_picture;
    @ForeignKey(entity = QuestionEntity.class,parentColumns = "question_id",childColumns = "questionid",onDelete = ForeignKey.CASCADE)
    private int questionid;
    private boolean isGood;

    public AnswerEntity() {
    }

    public void setAnswer_id(int id) {
        this.answer_id = id;
    }

    public int getAnswer_id() {
        return answer_id;
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

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }
}
