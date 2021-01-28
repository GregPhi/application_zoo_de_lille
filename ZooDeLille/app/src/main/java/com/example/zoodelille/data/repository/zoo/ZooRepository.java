package com.example.zoodelille.data.repository.zoo;

import com.example.zoodelille.data.api.object.Zoo;
import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.repository.zoo.local.ZooLocalDataSource;
import com.example.zoodelille.data.repository.zoo.remote.ZooRemoteDataSource;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ZooRepository {
    private final ZooLocalDataSource zooLocalDataSource;
    private final ZooRemoteDataSource zooRemoteDataSource;

    public ZooRepository(ZooLocalDataSource zooLocalDataSource, ZooRemoteDataSource zooRemoteDataSource) {
        this.zooLocalDataSource = zooLocalDataSource;
        this.zooRemoteDataSource = zooRemoteDataSource;
    }

    public Completable insert(ZooEntity zooEntity){
        return zooLocalDataSource.insert(zooEntity);
    }

    public Completable update(ZooEntity zooEntity){
        return zooLocalDataSource.update(zooEntity);
    }

    public Single<ZooEntity> findZooVersion(){
        return zooLocalDataSource.findZooVersion();
    }

    public Single<Zoo> getVersion(){
        return zooRemoteDataSource.getVersion();
    }
}
