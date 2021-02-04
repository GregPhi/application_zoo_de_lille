package com.example.zoodelille.data.repository.zooposition.mapper;

import com.example.zoodelille.data.api.object.ZooPosition;
import com.example.zoodelille.data.entity.ZooPositionEntity;

import java.util.ArrayList;
import java.util.List;

public class ZooPositionToZooPositionEntity {
    public ZooPositionEntity map(ZooPosition zooPosition){
        ZooPositionEntity zooPositionEntity = new ZooPositionEntity();
        zooPositionEntity.setZooPosition_id(zooPosition.getId());
        zooPositionEntity.setLatitude(zooPosition.getLatitude());
        zooPositionEntity.setLongitude(zooPosition.getLongitude());
        return zooPositionEntity;
    }

    public List<ZooPositionEntity> map(List<ZooPosition> zooPositionList){
        List<ZooPositionEntity> zooPositionEntities = new ArrayList<>();
        for(ZooPosition zooPosition : zooPositionList){
            zooPositionEntities.add(map(zooPosition));
        }
        return zooPositionEntities;
    }
}
