package com.example.zoodelille.data.repository.info.local;

import com.example.zoodelille.data.db.ProjectDatabase;
import com.example.zoodelille.data.entity.info.InfoEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class InfoLocalDataSource {
    private final ProjectDatabase projectDatabase;

    public InfoLocalDataSource(ProjectDatabase database) {
        this.projectDatabase = database;
    }

    public Completable addInfo(InfoEntity zooEntity){
        return projectDatabase.infoDao().addInfo(zooEntity);
    }

    public Completable deleteInfoWithId(int id){
        return projectDatabase.infoDao().deleteInfoWithId(id);
    }

    public Single<List<InfoEntity>> getInfoEntity(){
        return projectDatabase.infoDao().getInfoEntity();
    }
}
