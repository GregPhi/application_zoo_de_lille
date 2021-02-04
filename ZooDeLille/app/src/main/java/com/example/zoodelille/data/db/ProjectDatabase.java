package com.example.zoodelille.data.db;

import com.example.zoodelille.data.db.dao.AnimalDao;
import com.example.zoodelille.data.db.dao.ZooDao;
import com.example.zoodelille.data.db.typeconverters.Converters;
import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {AnimalEntity.class, ZooEntity.class}, version=1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ProjectDatabase extends RoomDatabase {
    public abstract AnimalDao animalDao();
    public abstract ZooDao zooDao();
}
