package com.example.zoodelille.data.repository.quiz.answer.local;

import com.example.zoodelille.data.db.ProjectDatabase;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class AnswerLocalDataSource {
    private final ProjectDatabase projectDatabase;

    public AnswerLocalDataSource(ProjectDatabase database) {
        this.projectDatabase = database;
    }

    public Completable addAnswer(AnswerEntity answerEntity){
        return projectDatabase.answerDao().addAnswer(answerEntity);
    }

    public Completable addAllAnswer(List<AnswerEntity> answerEntity){
        return projectDatabase.answerDao().addAllAnswer(answerEntity);
    }

    public Completable deleteAnswerWithId(int id){
        return projectDatabase.answerDao().deleteAnswerWithId(id);
    }

    public Single<AnswerEntity> getAnswerEntity(int id){
        return projectDatabase.answerDao().getAnswerEntity(id);
    }

    public Flowable<List<AnswerEntity>> getAllAnswer(){
        return projectDatabase.answerDao().getAllAnswer();
    }

    public Flowable<List<AnswerEntity>> getAllAnswerWithQuestionId(int id){
        return projectDatabase.answerDao().getAllAnswerWithQuestionId(id);
    }

    public List<AnswerEntity> getListAnswerWithQuestionId(int id){
        return projectDatabase.answerDao().getListAnswerWithQuestionId(id);
    }
}
