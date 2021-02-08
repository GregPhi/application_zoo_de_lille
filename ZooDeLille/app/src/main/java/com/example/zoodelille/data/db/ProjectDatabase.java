package com.example.zoodelille.data.db;

import com.example.zoodelille.data.db.dao.AnimalDao;
import com.example.zoodelille.data.db.dao.AnswerDao;
import com.example.zoodelille.data.db.dao.InfoDao;
import com.example.zoodelille.data.db.dao.QuestionDao;
import com.example.zoodelille.data.db.dao.QuizDao;
import com.example.zoodelille.data.db.dao.ZooDao;
import com.example.zoodelille.data.db.typeconverters.Converters;
import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {AnimalEntity.class, ZooEntity.class, InfoEntity.class, QuizEntity.class, QuestionEntity.class, AnswerEntity.class}, version=1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ProjectDatabase extends RoomDatabase {
    public abstract AnimalDao animalDao();
    public abstract ZooDao zooDao();
    public abstract InfoDao infoDao();
    public abstract QuizDao quizDao();
    public abstract QuestionDao questionDao();
    public abstract AnswerDao answerDao();
}
