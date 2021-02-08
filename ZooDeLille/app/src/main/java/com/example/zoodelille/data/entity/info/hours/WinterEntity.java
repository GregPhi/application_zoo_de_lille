package com.example.zoodelille.data.entity.info.hours;

public class WinterEntity {
    private int open_hour_week_winter;
    private int close_hour_week_winter;
    private String close_day_winter;

    public WinterEntity() {
    }

    public int getOpen_hour_week_winter() {
        return open_hour_week_winter;
    }

    public void setOpen_hour_week_winter(int open_hour_week_winter) {
        this.open_hour_week_winter = open_hour_week_winter;
    }

    public int getClose_hour_week_winter() {
        return close_hour_week_winter;
    }

    public void setClose_hour_week_winter(int close_hour_week_winter) {
        this.close_hour_week_winter = close_hour_week_winter;
    }

    public String getClose_day_winter() {
        return close_day_winter;
    }

    public void setClose_day_winter(String close_day_winter) {
        this.close_day_winter = close_day_winter;
    }
}
