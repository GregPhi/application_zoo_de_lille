package com.example.zoodelille.data.repository.zooposition.mapper;

import com.example.zoodelille.data.api.object.ZooPosition;
import com.example.zoodelille.data.entity.ZooPositionEntity;

public class ZooPositionToZooPositionEntity {
    public static ZooPositionEntity map(ZooPosition zooPosition){
        ZooPositionEntity zooPositionEntity = new ZooPositionEntity();
        zooPositionEntity.setZooPosition_id(zooPosition.getId());
        zooPositionEntity.setLatitude(zooPosition.getLatitude());
        zooPositionEntity.setLongitude(zooPosition.getLongitude());
        return zooPositionEntity;
    }
}
