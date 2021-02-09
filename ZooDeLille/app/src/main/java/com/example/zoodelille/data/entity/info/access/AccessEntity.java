package com.example.zoodelille.data.entity.info.access;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;

@Entity
public class AccessEntity {
    @PrimaryKey
    private int access_id;
    private String access_description;
    private String access_auto;
    private String access_bus;
    private String access_metro;
    private String access_vlille;
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)

    public AccessEntity() {
    }

    public int getAccess_id() {
        return access_id;
    }

    public void setAccess_id(int id) {
        this.access_id = id;
    }

    public String getAccess_description() {
        return access_description;
    }

    public void setAccess_description(String description) {
        this.access_description = description;
    }

    public String getAccess_auto() {
        return access_auto;
    }

    public void setAccess_auto(String access_auto) {
        this.access_auto = access_auto;
    }

    public String getAccess_bus() {
        return access_bus;
    }

    public void setAccess_bus(String access_bus) {
        this.access_bus = access_bus;
    }

    public String getAccess_metro() {
        return access_metro;
    }

    public void setAccess_metro(String access_metro) {
        this.access_metro = access_metro;
    }

    public String getAccess_vlille() {
        return access_vlille;
    }

    public void setAccess_vlille(String access_vlille) {
        this.access_vlille = access_vlille;
    }
}
