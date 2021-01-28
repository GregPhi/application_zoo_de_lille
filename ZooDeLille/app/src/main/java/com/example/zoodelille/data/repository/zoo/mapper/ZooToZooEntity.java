package com.example.zoodelille.data.repository.zoo.mapper;

import com.example.zoodelille.data.api.object.Zoo;
import com.example.zoodelille.data.entity.ZooEntity;

public class ZooToZooEntity {
    public ZooEntity map(Zoo zoo){
        ZooEntity zooEntity = new ZooEntity();
        zooEntity.setId(zoo.getId());
        zooEntity.setAnimal_version(zoo.getAnimal_version());
        zooEntity.setInfo_version(zoo.getInfo_version());
        zooEntity.setInterest_version(zoo.getInterest_version());
        zooEntity.setRoute_version(zoo.getRoute_version());
        zooEntity.setQuiz_version(zoo.getQuiz_version());
        return zooEntity;
    }

    public Zoo reverseMap(ZooEntity zooEntity){
        Zoo zoo = new Zoo();
        zoo.setId(zooEntity.getId());
        zoo.setAnimal_version(zooEntity.getAnimal_version());
        zoo.setInfo_version(zooEntity.getInfo_version());
        zoo.setInterest_version(zooEntity.getInterest_version());
        zoo.setRoute_version(zooEntity.getRoute_version());
        zoo.setQuiz_version(zooEntity.getQuiz_version());
        return zoo;
    }
}
