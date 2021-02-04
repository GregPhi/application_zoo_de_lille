package com.example.zoodelille.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ZooPositionEntity {
    @NonNull
    @PrimaryKey
    private int zooPosition_id;
    private int longitude;
    private int latitude;

    public ZooPositionEntity() {
    }

    public int getZooPosition_id() {
        return zooPosition_id;
    }

    public void setZooPosition_id(int id) {
        this.zooPosition_id = id;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
