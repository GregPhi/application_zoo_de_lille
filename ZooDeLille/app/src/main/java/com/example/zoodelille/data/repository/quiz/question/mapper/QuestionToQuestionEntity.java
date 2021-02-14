package com.example.zoodelille.data.repository.quiz.question.mapper;

import com.example.zoodelille.data.api.object.quiz.question.Question;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;
import com.example.zoodelille.data.repository.quiz.answer.mapper.AnswerToAnswerEntity;

import java.util.ArrayList;
import java.util.List;

public class QuestionToQuestionEntity {
    public static QuestionEntity map(Question question, int quiz_id){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion_id(question.getId());
        questionEntity.setQuestion(question.getQuestion());
        questionEntity.setUrl_extra(question.getUrl_extra());
        questionEntity.setQuizid(quiz_id);
        return questionEntity;
    }

    public static List<QuestionEntity> map(List<Question> questions, int quiz_id){
        List<QuestionEntity> questionEntities = new ArrayList<>();
        for(Question question : questions){
            questionEntities.add(map(question,quiz_id));
        }
        return questionEntities;
    }

    public static List<AnswerEntity> mapAnswer(Question question, int question_id){
        return AnswerToAnswerEntity.map(question.getAnswers(),question_id);
    }
}
