package com.example.zoodelille.data.db.dao;

import com.example.zoodelille.data.entity.ZooEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ZooDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(ZooEntity zooEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable update(ZooEntity zooEntity);

    @Query("SELECT * FROM zooentity WHERE id = 0")
    Single<ZooEntity> findZooVersion();
}
