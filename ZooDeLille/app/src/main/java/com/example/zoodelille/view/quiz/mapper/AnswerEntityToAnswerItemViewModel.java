package com.example.zoodelille.view.quiz.mapper;

import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.view.quiz.adapter.item.AnswerItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class AnswerEntityToAnswerItemViewModel {
    public static AnswerItemViewModel map(AnswerEntity answer){
        AnswerItemViewModel answerEntity = new AnswerItemViewModel();
        answerEntity.setId(answer.getAnswer_id());
        answerEntity.setAnswer(answer.getAnswer());
        answerEntity.setUrl_picture(answer.getUrl_picture());
        return answerEntity;
    }

    public static List<AnswerItemViewModel> map(List<AnswerEntity> answers){
        List<AnswerItemViewModel> answerEntities = new ArrayList<>();
        for(AnswerEntity answer : answers){
            answerEntities.add(map(answer));
        }
        return answerEntities;
    }
}
