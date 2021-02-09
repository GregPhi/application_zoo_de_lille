package com.example.zoodelille.data.db.dao;

import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addQuestion(QuestionEntity questionEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addAllQuestion(List<QuestionEntity> questionEntity);

    @Query("DELETE FROM questionEntity WHERE question_id = :id")
    Completable deleteQuestionWithId(int id);

    @Query("SELECT * FROM questionEntity WHERE question_id = :id")
    Single<QuestionEntity> getQuestionEntity(int id);

    @Query("SELECT * FROM questionEntity")
    Flowable<List<QuestionEntity>> getAllQuestion();

    @Query("SELECT * FROM questionEntity WHERE quiz_id = :id")
    Flowable<List<QuestionEntity>> getAllQuestionWithQuizId(int id);
}
