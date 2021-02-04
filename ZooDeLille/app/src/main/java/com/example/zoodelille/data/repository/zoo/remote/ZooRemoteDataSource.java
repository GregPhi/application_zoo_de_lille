package com.example.zoodelille.data.repository.zoo.remote;

import com.example.zoodelille.data.api.object.Zoo;
import com.example.zoodelille.data.api.service.ZooService;
import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.repository.zoo.mapper.ZooToZooEntity;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class ZooRemoteDataSource {
    private final ZooService zooService;
    private final ZooToZooEntity zooToZooEntity = new ZooToZooEntity();

    public ZooRemoteDataSource(ZooService zooService) {
        this.zooService = zooService;
    }

    public Single<Zoo> getVersion(){
        return zooService.getVersion();
    }

    public Single<ZooEntity> getVersionEntity(){
        return zooService.getVersion()
                .map(new Function<Zoo, ZooEntity>() {
                    @Override
                    public ZooEntity apply(Zoo zoo) {
                        return zooToZooEntity.map(zoo);
                    }
                });
    }
}
