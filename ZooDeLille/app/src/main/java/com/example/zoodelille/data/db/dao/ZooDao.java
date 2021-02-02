package com.example.zoodelille.data.db.dao;

import com.example.zoodelille.data.entity.ZooEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ZooDao {
    @Insert
    Completable insert(ZooEntity zooEntity);

    @Update
    Completable update(ZooEntity zooEntity);


    @Query("SELECT * FROM zooentity")
    Single<List<ZooEntity>> findZooVersion();
}
