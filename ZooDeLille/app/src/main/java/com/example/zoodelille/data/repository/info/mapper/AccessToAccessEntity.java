package com.example.zoodelille.data.repository.info.mapper;

import android.text.TextUtils;

import com.example.zoodelille.data.api.object.info.access.Access;
import com.example.zoodelille.data.entity.info.access.AccessEntity;
import com.example.zoodelille.data.repository.zooposition.mapper.ZooPositionToZooPositionEntity;

public class AccessToAccessEntity {
    public static AccessEntity map(Access access){
        AccessEntity accessEntity = new AccessEntity();
        accessEntity.setAccess_id(access.getId());

        if(access.getAccess_auto() == null){
            accessEntity.setAccess_auto("Non Communiqué");
        }else{
            accessEntity.setAccess_auto(TextUtils.join("\n",access.getAccess_auto()));
        }

        if(access.getAccess_bus() == null){
            accessEntity.setAccess_bus("Non Communiqué");
        }else{
            accessEntity.setAccess_bus(TextUtils.join("\n",access.getAccess_bus()));
        }

        if(access.getAccess_metro() == null){
            accessEntity.setAccess_metro("Non Communiqué");
        }else{
            accessEntity.setAccess_metro(TextUtils.join("\n",access.getAccess_metro()));
        }

        if(access.getAccess_vlille() == null){
            accessEntity.setAccess_vlille("Non Communiqué");
        }else{
            accessEntity.setAccess_vlille(TextUtils.join("\n",access.getAccess_vlille()));
        }

        accessEntity.setZooPositionEntity(ZooPositionToZooPositionEntity.map(access.getZooPosition()));
        return accessEntity;
    }
}
