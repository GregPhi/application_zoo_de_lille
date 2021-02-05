package com.example.zoodelille.data.repository.info.remote;

import com.example.zoodelille.data.api.object.info.Info;
import com.example.zoodelille.data.api.service.ZooService;
import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.repository.info.mapper.InfoToInfoEntity;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class InfoRemoteDataSource {
    private final ZooService zooService;

    private final InfoToInfoEntity infoToInfoEntity = new InfoToInfoEntity();

    public InfoRemoteDataSource(ZooService zooService) {
        this.zooService = zooService;
    }

    public Single<Info> getAllInfos(){
        return zooService.getAllInfos();
    }

    public Single<InfoEntity> getInfoEntity(){
        return zooService.getAllInfos()
                .map(new Function<Info, InfoEntity>() {
                    @Override
                    public InfoEntity apply(Info info) throws Exception {
                        return infoToInfoEntity.map(info);
                    }
                });
    }
}
