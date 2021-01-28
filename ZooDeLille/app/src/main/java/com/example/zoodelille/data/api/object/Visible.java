package com.example.zoodelille.data.api.object;

public class Visible {
    private int id;
    private String name;
    private ZooPosition zooPosition;

    public Visible() {
    }

    public Visible(String name) {
        this.name = name;
    }

    public Visible(String name, ZooPosition zooPosition) {
        this.name = name;
        this.zooPosition = zooPosition;
    }

    public Visible(int id, String name, ZooPosition zooPosition) {
        this.id = id;
        this.name = name;
        this.zooPosition = zooPosition;
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

    public ZooPosition getZooPosition() {
        return zooPosition;
    }

    public void setZooPosition(ZooPosition zooPosition) {
        this.zooPosition = zooPosition;
    }
}