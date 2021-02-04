package com.example.zoodelille.data.api.object.info.hours;

import java.util.Objects;

public class Summer {
    private int open_hour_week_summer;
    private int close_hour_week_summer;
    private int close_hour_weekend_summer;
    private String close_day_summer;

    public Summer() {
    }

    public Summer(int open_hour_week_summer, int close_hour_week_summer, int close_hour_weekend_summer, String close_day_summer) {
        this.open_hour_week_summer = open_hour_week_summer;
        this.close_hour_week_summer = close_hour_week_summer;
        this.close_hour_weekend_summer = close_hour_weekend_summer;
        this.close_day_summer = close_day_summer;
    }

    public int getOpen_hour_week_summer() {
        return open_hour_week_summer;
    }

    public void setOpen_hour_week_summer(int open_hour_week_summer) {
        this.open_hour_week_summer = open_hour_week_summer;
    }

    public int getClose_hour_week_summer() {
        return close_hour_week_summer;
    }

    public void setClose_hour_week_summer(int close_hour_week_summer) {
        this.close_hour_week_summer = close_hour_week_summer;
    }

    public int getClose_hour_weekend_summer() {
        return close_hour_weekend_summer;
    }

    public void setClose_hour_weekend_summer(int close_hour_weekend_summer) {
        this.close_hour_weekend_summer = close_hour_weekend_summer;
    }

    public String getClose_day_summer() {
        return close_day_summer;
    }

    public void setClose_day_summer(String close_day_summer) {
        this.close_day_summer = close_day_summer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summer summer = (Summer) o;
        return open_hour_week_summer == summer.open_hour_week_summer &&
                close_hour_week_summer == summer.close_hour_week_summer &&
                close_hour_weekend_summer == summer.close_hour_weekend_summer &&
                Objects.equals(close_day_summer, summer.close_day_summer);
    }
}
