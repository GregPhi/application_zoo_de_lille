package com.example.zoodelille.data.api.object;

public class ZooPosition {
    private int id;
    private int longitude;
    private int latitude;

    public ZooPosition() {
    }

    public ZooPosition(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
