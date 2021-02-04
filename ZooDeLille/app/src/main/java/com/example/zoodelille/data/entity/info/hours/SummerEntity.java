package com.example.zoodelille.data.entity.info.hours;

public class SummerEntity {
    private int open_hour_week_summer;
    private int close_hour_week_summer;
    private int close_hour_weekend_summer;
    private String close_day_summer;

    public SummerEntity() {
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
}
