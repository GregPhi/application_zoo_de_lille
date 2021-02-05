package com.example.zoodelille.data.db.dao;

import com.example.zoodelille.data.entity.info.InfoEntity;

import java.util.List;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface InfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addInfo(InfoEntity infoEntity);

    @Query("DELETE FROM infoentity WHERE info_id = :id")
    Completable deleteInfoWithId(int id);

    @Query("SELECT * FROM infoentity")
    Single<List<InfoEntity>> getInfoEntity();
}