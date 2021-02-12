package com.example.zoodelille.view.quiz.mapper;

import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;
import com.example.zoodelille.view.quiz.adapter.item.QuestionItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionEntityToQuestionItemViewModel {
    public static QuestionItemViewModel map(QuestionEntity questionEntity){
        QuestionItemViewModel questionItemViewModel = new QuestionItemViewModel();
        questionItemViewModel.setId(questionEntity.getQuestion_id());
        questionItemViewModel.setQuestion(questionEntity.getQuestion());
        questionItemViewModel.setUrl_extra(questionEntity.getUrl_extra());
        return questionItemViewModel;
    }

    public static List<QuestionItemViewModel> map(List<QuestionEntity> questions){
        List<QuestionItemViewModel> questionItemViewModels = new ArrayList<>();
        for(QuestionEntity question : questions){
            questionItemViewModels.add(map(question));
        }
        return questionItemViewModels;
    }
/*
    public static List<QuestionItemViewModel> map(List<QuestionEntity> questions, List<AnswerEntity> answers){
        List<QuestionItemViewModel> questionItemViewModels = new ArrayList<>();
        for(QuestionEntity question : questions){
            QuestionItemViewModel questionItemViewModel = map(question);
            questionItemViewModel.setAnswers(AnswerEntityToAnswerItemViewModel.map(answers));
            questionItemViewModels.add(questionItemViewModel);
        }
        return questionItemViewModels;
    }
*/
}
