package com.example.zoodelille.data.api.object;

public class ZooPosition {
    private int id;
    private float longitude;
    private float latitude;

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

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
