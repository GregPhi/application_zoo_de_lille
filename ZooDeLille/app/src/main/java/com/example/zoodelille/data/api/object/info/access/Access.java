package com.example.zoodelille.data.api.object.info.access;


import com.example.zoodelille.data.api.object.ZooPosition;

import java.util.List;

public class Access {
    private int id;
    private String description;
    private List<String> access_auto;
    private List<String> access_bus;
    private List<String> access_metro;
    private List<String> access_vlille;
    private ZooPosition zooPosition;

    public Access() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAccess_auto() {
        return access_auto;
    }

    public void setAccess_auto(List<String> access_auto) {
        this.access_auto = access_auto;
    }

    public List<String> getAccess_bus() {
        return access_bus;
    }

    public void setAccess_bus(List<String> access_bus) {
        this.access_bus = access_bus;
    }

    public List<String> getAccess_metro() {
        return access_metro;
    }

    public void setAccess_metro(List<String> access_metro) {
        this.access_metro = access_metro;
    }

    public List<String> getAccess_vlille() {
        return access_vlille;
    }

    public void setAccess_vlille(List<String> access_vlille) {
        this.access_vlille = access_vlille;
    }

    public ZooPosition getZooPosition() {
        return zooPosition;
    }

    public void setZooPosition(ZooPosition zooPosition) {
        this.zooPosition = zooPosition;
    }
}
