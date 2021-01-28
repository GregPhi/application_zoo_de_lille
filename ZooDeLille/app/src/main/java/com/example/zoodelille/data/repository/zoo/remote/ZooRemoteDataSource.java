package com.example.zoodelille.data.repository.zoo.remote;

import com.example.zoodelille.data.api.object.Zoo;
import com.example.zoodelille.data.api.service.ZooService;

import io.reactivex.Single;

public class ZooRemoteDataSource {
    private final ZooService zooService;

    public ZooRemoteDataSource(ZooService zooService) {
        this.zooService = zooService;
    }

    public Single<Zoo> getVersion(){
        return zooService.getVersion();
    }
}
