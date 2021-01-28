package com.example.zoodelille.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VisibleEntity {
    @NonNull
    @PrimaryKey
    private int id;
    private String name;
    @Embedded
    private ZooPositionEntity zooPositionEntity;

    public VisibleEntity() {
    }

    public VisibleEntity(String name) {
        this.name = name;
    }

    public VisibleEntity(String name, ZooPositionEntity zooPositionEntity) {
        this.name = name;
        this.zooPositionEntity = zooPositionEntity;
    }

    public VisibleEntity(int id, String name, ZooPositionEntity zooPositionEntity) {
        this.id = id;
        this.name = name;
        this.zooPositionEntity = zooPositionEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZooPositionEntity getZooPositionEntity() {
        return zooPositionEntity;
    }

    public void setZooPositionEntity(ZooPositionEntity zooPositionEntity) {
        this.zooPositionEntity = zooPositionEntity;
    }
}