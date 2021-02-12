package com.example.zoodelille.view.quiz.mapper;

import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;
import com.example.zoodelille.view.quiz.adapter.item.AnswerItemViewModel;
import com.example.zoodelille.view.quiz.adapter.item.QuestionItemViewModel;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizEntityToQuizItemViewModel {
    public QuizItemViewModel map(QuizEntity quizEntity){
        QuizItemViewModel quizItemViewModel = new QuizItemViewModel();
        quizItemViewModel.setId(quizEntity.getQuiz_id());
        quizItemViewModel.setName(quizEntity.getQuiz_name());
        quizItemViewModel.setBest_score(quizEntity.getBest_score());
        return quizItemViewModel;
    }

    public List<QuizItemViewModel> map(List<QuizEntity> quizEntities){
        List<QuizItemViewModel> quizItemViewModel = new ArrayList<>();
        for(QuizEntity quizEntity : quizEntities){
            quizItemViewModel.add(map(quizEntity));
        }
        return quizItemViewModel;
    }
/*
    public List<QuizItemViewModel> mapAll(List<QuizEntity> quizEntities, List<QuestionEntity> questionEntities, List<AnswerEntity> answerEntities){
        List<QuizItemViewModel> quizItemViewModels = new ArrayList<>();

        for(QuizEntity quizEntity : quizEntities){
            QuizItemViewModel quizItemViewModel = map(quizEntity);
            for(QuestionEntity questionEntity : questionEntities){
                List<QuestionItemViewModel> questionItemViewModels = new ArrayList<>();
                if(questionEntity.getQuizid() == quizEntity.getQuiz_id()){
                    QuestionItemViewModel questionItemViewModel = QuestionEntityToQuestionItemViewModel.map(questionEntity);
                    for(AnswerEntity answerEntity : answerEntities){
                        List<AnswerItemViewModel> answerItemViewModels = new ArrayList<>();
                        if(answerEntity.getQuestionid() == questionEntity.getQuestion_id()){
                            answerItemViewModels.add(AnswerEntityToAnswerItemViewModel.map(answerEntity));
                        }
                        questionItemViewModel.setAnswers(answerItemViewModels);
                    }
                    questionItemViewModels.add(questionItemViewModel);
                }
                quizItemViewModel.setQuestions(questionItemViewModels);
            }
            quizItemViewModels.add(quizItemViewModel);
        }

        return quizItemViewModels;
    }
*/
    public QuizEntity reverse(QuizItemViewModel quizItemViewModel){
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setQuiz_id(quizItemViewModel.getId());
        quizEntity.setQuiz_name(quizItemViewModel.getName());
        quizEntity.setMake();
        quizEntity.setBest_score(quizItemViewModel.getBest_score());
        return quizEntity;
    }
/*
    public QuizItemViewModel mapAQuiz(QuizEntity quizEntity, List<QuestionEntity> questionEntities, List<AnswerEntity> answerEntities) {
        QuizItemViewModel quizItemViewModel = new QuizItemViewModel();
        quizItemViewModel.setName(quizEntity.getQuiz_name());
        quizItemViewModel.setQuestions(QuestionEntityToQuestionItemViewModel.map(questionEntities,answerEntities));
        return quizItemViewModel;
    }
*/
    public List<QuestionItemViewModel> mapQuestions(List<QuestionEntity> questionEntities){
        return QuestionEntityToQuestionItemViewModel.map(questionEntities);
    }

    public AnswerItemViewModel mapAnswer(AnswerEntity answerEntity){
        return AnswerEntityToAnswerItemViewModel.map(answerEntity);
    }
}