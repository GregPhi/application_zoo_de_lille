package com.example.zoodelille.data.api.object.info.hours;

import java.util.Objects;

public class Winter {
    private int open_hour_week_winter;
    private int close_hour_week_winter;
    private String close_day_winter;

    public Winter() {
    }

    public Winter(int open_hour_week_winter, int close_hour_week_winter, String close_day_winter) {
        this.open_hour_week_winter = open_hour_week_winter;
        this.close_hour_week_winter = close_hour_week_winter;
        this.close_day_winter = close_day_winter;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winter winter = (Winter) o;
        return open_hour_week_winter == winter.open_hour_week_winter &&
                close_hour_week_winter == winter.close_hour_week_winter &&
                Objects.equals(close_day_winter, winter.close_day_winter);
    }
}
