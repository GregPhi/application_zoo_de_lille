package com.example.zoodelille.view.quiz.mapper;

import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;

public class QuizEntityToQuizItemViewModel {
    public QuizItemViewModel map(QuizEntity quizEntity){
        QuizItemViewModel quizItemViewModel = new QuizItemViewModel();
        return quizItemViewModel;
    }

    public QuizEntity reverse(QuizItemViewModel quizItemViewModel){
        QuizEntity quizEntity = new QuizEntity();
        return quizEntity;
    }
}
