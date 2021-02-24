package com.example.zoodelille.data.db.dao;

import com.example.zoodelille.data.entity.quiz.QuizEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addQuiz(QuizEntity quizEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addAllQuiz(List<QuizEntity> quizEntity);

    @Query("DELETE FROM quizEntity WHERE quiz_id = :id")
    Completable deleteQuizWithId(int id);

    @Query("SELECT * FROM quizEntity WHERE quiz_id = :id")
    Flowable<QuizEntity> getQuizEntity(int id);

    @Query("SELECT * FROM quizEntity")
    Flowable<List<QuizEntity>> getAllQuiz();

    @Query("SELECT quiz_id FROM quizEntity WHERE make = 1")
    Single<List<Integer>> getAllMakeQuizId();

    @Query("SELECT * FROM quizEntity WHERE make = 1")
    Flowable<List<QuizEntity>> getAllMakeQuiz();
}
