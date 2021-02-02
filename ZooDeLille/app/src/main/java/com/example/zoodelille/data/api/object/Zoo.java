package com.example.zoodelille.data.api.object;

import java.util.Date;

public class Zoo {
    private int id;
    private Date date_last_version;

    public Zoo() {
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
}
