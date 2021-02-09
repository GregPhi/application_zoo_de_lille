package com.example.zoodelille.data.db.dao;

import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addAnswer(AnswerEntity answerEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addAllAnswer(List<AnswerEntity> answerEntity);

    @Query("DELETE FROM answerEntity WHERE answer_id = :id")
    Completable deleteAnswerWithId(int id);

    @Query("SELECT * FROM answerEntity WHERE answer_id = :id")
    Single<AnswerEntity> getAnswerEntity(int id);

    @Query("SELECT * FROM answerEntity")
    Flowable<List<AnswerEntity>> getAllAnswer();

    @Query("SELECT * FROM answerEntity WHERE question_id = :id")
    Flowable<List<AnswerEntity>> getAllAnswerWithQuestionId(int id);
}
