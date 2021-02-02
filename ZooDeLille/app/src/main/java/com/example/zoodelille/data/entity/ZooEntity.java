package com.example.zoodelille.data.entity;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ZooEntity {
    @PrimaryKey
    private int id = 0;
    private Date date_last_version;

    public ZooEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_last_version() {
        return date_last_version;
    }

    public void setDate_last_version(Date date_last_version) {
        this.date_last_version = date_last_version;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null){
            return false;
        }
        if(!(obj instanceof ZooEntity)){
            return false;
        }
        final ZooEntity zooEntity = (ZooEntity) obj;
        return this.date_last_version.equals(zooEntity.getDate_last_version());
    }
}
