package com.example.zoodelille.data.repository.quiz.mapper;

import com.example.zoodelille.data.api.object.quiz.Quiz;
import com.example.zoodelille.data.api.object.quiz.question.Question;
import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;
import com.example.zoodelille.data.repository.quiz.question.mapper.QuestionToQuestionEntity;

import java.util.ArrayList;
import java.util.List;

public class QuizToQuizEntity {
    public QuizEntity map(Quiz quiz){
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setQuiz_id(quiz.getId());
        quizEntity.setQuiz_name(quiz.getName());
        quizEntity.setBest_score(0);
        return quizEntity;
    }

    public List<QuizEntity> map(List<Quiz> quizzes){
        List<QuizEntity> quizEntities = new ArrayList<>();
        for(Quiz quiz : quizzes){
            quizEntities.add(map(quiz));
        }
        return quizEntities;
    }

    public List<QuestionEntity> mapQuestion(Quiz quiz){
        return QuestionToQuestionEntity.map(quiz.getQuestions(),quiz.getId());
    }

    public List<AnswerEntity> mapAnswer(Quiz quiz){
        List<AnswerEntity> answerEntities = new ArrayList<>();
        for(Question question : quiz.getQuestions()){
            answerEntities.addAll(QuestionToQuestionEntity.mapAnswer(question,question.getId()));
        }
        return answerEntities;
    }
}
