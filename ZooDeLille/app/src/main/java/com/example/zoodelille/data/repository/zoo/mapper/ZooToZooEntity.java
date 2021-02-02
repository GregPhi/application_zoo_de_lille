package com.example.zoodelille.data.repository.zoo.mapper;

import com.example.zoodelille.data.api.object.Zoo;
import com.example.zoodelille.data.entity.ZooEntity;

public class ZooToZooEntity {
    public ZooEntity map(Zoo zoo){
        ZooEntity zooEntity = new ZooEntity();
        zooEntity.setId(zoo.getId());
        zooEntity.setDate_last_version(zoo.getDate_last_version());
        return zooEntity;
    }
}
