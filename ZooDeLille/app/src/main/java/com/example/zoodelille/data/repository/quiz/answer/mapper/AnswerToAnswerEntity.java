package com.example.zoodelille.data.repository.quiz.answer.mapper;

import com.example.zoodelille.data.api.object.quiz.answer.Answer;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;

import java.util.ArrayList;
import java.util.List;

public class AnswerToAnswerEntity {
    public static AnswerEntity map(Answer answer, int question_id){
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswer_id(answer.getId());
        answerEntity.setAnswer(answer.getAnswer());
        answerEntity.setUrl_picture(answer.getUrl_picture());
        answerEntity.setQuestionid(question_id);
        return answerEntity;
    }

    public static List<AnswerEntity> map(List<Answer> answers, int question_id){
        List<AnswerEntity> answerEntities = new ArrayList<>();
        for(Answer answer : answers){
            answerEntities.add(map(answer,question_id));
        }
        return answerEntities;
    }
}
