package com.example.zoodelille.data.repository.quiz.mapper;

import com.example.zoodelille.data.api.object.quiz.Quiz;
import com.example.zoodelille.data.entity.quiz.QuizEntity;

import java.util.ArrayList;
import java.util.List;

public class QuizToQuizEntity {
    public QuizEntity map(Quiz quiz){
        QuizEntity quizEntity = new QuizEntity();
        return quizEntity;
    }

    public List<QuizEntity> map(List<Quiz> quizzes){
        List<QuizEntity> quizEntities = new ArrayList<>();
        for(Quiz quiz : quizzes){
            quizEntities.add(map(quiz));
        }
        return quizEntities;
    }
}
