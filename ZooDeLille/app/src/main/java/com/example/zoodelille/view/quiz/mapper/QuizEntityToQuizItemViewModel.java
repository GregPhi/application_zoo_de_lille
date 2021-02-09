package com.example.zoodelille.view.quiz.mapper;

import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizEntityToQuizItemViewModel {
    public QuizItemViewModel map(QuizEntity quizEntity){
        QuizItemViewModel quizItemViewModel = new QuizItemViewModel();
        quizItemViewModel.setName(quizEntity.getQuiz_name());
        return quizItemViewModel;
    }

    public List<QuizItemViewModel> map(List<QuizEntity> quizEntities){
        List<QuizItemViewModel> quizItemViewModel = new ArrayList<>();
        for(QuizEntity quizEntity : quizEntities){
            quizItemViewModel.add(map(quizEntity));
        }
        return quizItemViewModel;
    }

    public QuizEntity reverse(QuizItemViewModel quizItemViewModel){
        QuizEntity quizEntity = new QuizEntity();
        return quizEntity;
    }
}
