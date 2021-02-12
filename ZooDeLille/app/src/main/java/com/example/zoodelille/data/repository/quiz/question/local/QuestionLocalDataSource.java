package com.example.zoodelille.data.repository.quiz.question.local;

import com.example.zoodelille.data.db.ProjectDatabase;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class QuestionLocalDataSource {
    private final ProjectDatabase projectDatabase;

    public QuestionLocalDataSource(ProjectDatabase database) {
        this.projectDatabase = database;
    }

    public Completable addQuestion(QuestionEntity questionEntity){
        return projectDatabase.questionDao().addQuestion(questionEntity);
    }

    public Completable addAllQuestion(List<QuestionEntity> questionEntity){
        return projectDatabase.questionDao().addAllQuestion(questionEntity);
    }

    public Completable deleteQuestionWithId(int id){
        return projectDatabase.questionDao().deleteQuestionWithId(id);
    }

    public Single<QuestionEntity> getQuestionEntity(int id){
        return projectDatabase.questionDao().getQuestionEntity(id);
    }

    public Flowable<List<QuestionEntity>> getAllQuestion(){
        return projectDatabase.questionDao().getAllQuestion();
    }

    public Flowable<List<QuestionEntity>> getAllQuestionWithQuizId(int id){
        return projectDatabase.questionDao().getAllQuestionWithQuizId(id);
    }

    public Flowable<List<Integer>> getAllQuestionIdWithQuizId(int id){
        return projectDatabase.questionDao().getAllQuestionIdWithQuizId(id);
    }
}
