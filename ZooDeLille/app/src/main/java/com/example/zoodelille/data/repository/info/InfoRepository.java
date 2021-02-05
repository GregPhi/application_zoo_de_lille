package com.example.zoodelille.data.repository.info;

import com.example.zoodelille.data.api.object.info.Info;
import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.repository.info.local.InfoLocalDataSource;
import com.example.zoodelille.data.repository.info.remote.InfoRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class InfoRepository {
    private final InfoLocalDataSource infoLocalDataSource;
    private final InfoRemoteDataSource infoRemoteDataSource;

    public InfoRepository(InfoLocalDataSource infoLocalDataSource, InfoRemoteDataSource infoRemoteDataSource) {
        this.infoLocalDataSource = infoLocalDataSource;
        this.infoRemoteDataSource = infoRemoteDataSource;
    }

    public Completable addInfo(InfoEntity zooEntity){
        return infoLocalDataSource.addInfo(zooEntity);
    }

    public Completable deleteInfoWithId(int id){
        return infoLocalDataSource.deleteInfoWithId(id);
    }

    public Single<List<InfoEntity>> getInfoEntity(){
        return infoLocalDataSource.getInfoEntity();
    }

    public Single<Info> getAllInfos(){
        return infoRemoteDataSource.getAllInfos();
    }
}
