package com.example.zoodelille.data.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ZooEntity {
    @PrimaryKey
    private int id;
    private int animal_version;
    private int route_version;
    private int quiz_version;
    private int interest_version;
    private int info_version;

    public ZooEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimal_version() {
        return animal_version;
    }

    public void setAnimal_version(int animal_version) {
        this.animal_version = animal_version;
    }

    public int getRoute_version() {
        return route_version;
    }

    public void setRoute_version(int route_version) {
        this.route_version = route_version;
    }

    public int getQuiz_version() {
        return quiz_version;
    }

    public void setQuiz_version(int quiz_version) {
        this.quiz_version = quiz_version;
    }

    public int getInterest_version() {
        return interest_version;
    }

    public void setInterest_version(int interest_version) {
        this.interest_version = interest_version;
    }

    public int getInfo_version() {
        return info_version;
    }

    public void setInfo_version(int info_version) {
        this.info_version = info_version;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null){
            return false;
        }
        final ZooEntity zooEntity = (ZooEntity) obj;
        return this.animal_version == zooEntity.animal_version
                && this.quiz_version == zooEntity.quiz_version
                && this.info_version == zooEntity.info_version
                && this.route_version == zooEntity.route_version
                && this.interest_version == zooEntity.interest_version;
    }
}
