package com.example.zoodelille.data.repository.quiz.local;

import com.example.zoodelille.data.db.ProjectDatabase;
import com.example.zoodelille.data.entity.quiz.QuizEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class QuizLocalDataSource {
    private final ProjectDatabase projectDatabase;

    public QuizLocalDataSource(ProjectDatabase database) {
        this.projectDatabase = database;
    }

    public Completable addQuiz(QuizEntity quizEntity){
        return projectDatabase.quizDao().addQuiz(quizEntity);
    }

    public Completable addAllQuiz(List<QuizEntity> quizEntity){
        return projectDatabase.quizDao().addAllQuiz(quizEntity);
    }

    public Completable deleteQuizWithId(int id){
        return projectDatabase.quizDao().deleteQuizWithId(id);
    }

    public Flowable<QuizEntity> getQuizEntity(int id){
        return projectDatabase.quizDao().getQuizEntity(id);
    }

    public Flowable<List<QuizEntity>> getAllQuiz(){
        return projectDatabase.quizDao().getAllQuiz();
    }

    public Single<List<Integer>> getAllMakeQuizId(){
        return projectDatabase.quizDao().getAllMakeQuizId();
    }

    public Flowable<List<QuizEntity>> getAllMakeQuiz(){
        return projectDatabase.quizDao().getAllMakeQuiz();
    }
}
