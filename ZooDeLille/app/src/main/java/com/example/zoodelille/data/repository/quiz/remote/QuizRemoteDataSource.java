package com.example.zoodelille.data.repository.quiz.remote;

import com.example.zoodelille.data.api.object.quiz.Quiz;
import com.example.zoodelille.data.api.service.ZooService;
import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;
import com.example.zoodelille.data.repository.quiz.mapper.QuizToQuizEntity;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class QuizRemoteDataSource {
    private final ZooService zooService;

    private final QuizToQuizEntity quizToQuizEntity = new QuizToQuizEntity();

    private Single<List<QuestionEntity>> questionEntities;

    private Single<List<AnswerEntity>> answerEntities;

    public QuizRemoteDataSource(ZooService zooService) {
        this.zooService = zooService;
    }

    public Single<List<Quiz>> getAllQuizzes(){
        return zooService.getAllQuizzes();
    }

    public Single<List<QuizEntity>> getAllQuizzesEntity(){
        return zooService.getAllQuizzes()
                .map(new Function<List<Quiz>, List<QuizEntity>>() {
                    @Override
                    public List<QuizEntity> apply(List<Quiz> quizzes) throws Exception {
                        return quizToQuizEntity.map(quizzes);
                    }
                });
    }

}