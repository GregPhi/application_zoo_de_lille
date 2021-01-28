package com.example.zoodelille.data.repository.zoo.local;

import com.example.zoodelille.data.db.ProjectDatabase;
import com.example.zoodelille.data.entity.ZooEntity;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ZooLocalDataSource {
    private final ProjectDatabase projectDatabase;

    public ZooLocalDataSource(ProjectDatabase database) {
        this.projectDatabase = database;
    }

    public Completable insert(ZooEntity zooEntity){
        return projectDatabase.zooDao().insert(zooEntity);
    }

    public Completable update(ZooEntity zooEntity){
        return projectDatabase.zooDao().update(zooEntity);
    }

    public Single<ZooEntity> findZooVersion(){
        return projectDatabase.zooDao().findZooVersion();
    }
}
