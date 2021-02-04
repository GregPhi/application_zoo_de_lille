package com.example.zoodelille.data.entity.info.hours;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HoursEntity {
    @PrimaryKey
    private int hours_id;
    @Embedded
    private SummerEntity summerEntity;
    @Embedded
    private WinterEntity winterEntity;
    private String exceptional_opening;
    private String annual_closure_oldYear;
    private String annual_closure_newYear;
    private String attention;

    public HoursEntity() {
    }

    public int getHours_id() {
        return hours_id;
    }

    public void setHours_id(int id) {
        this.hours_id = id;
    }

    public SummerEntity getSummerEntity() {
        return summerEntity;
    }

    public void setSummerEntity(SummerEntity summerEntity) {
        this.summerEntity = summerEntity;
    }

    public WinterEntity getWinterEntity() {
        return winterEntity;
    }

    public void setWinterEntity(WinterEntity winterEntity) {
        this.winterEntity = winterEntity;
    }

    public String getExceptional_opening() {
        return exceptional_opening;
    }

    public void setExceptional_opening(String exceptional_opening) {
        this.exceptional_opening = exceptional_opening;
    }

    public String getAnnual_closure_oldYear() {
        return annual_closure_oldYear;
    }

    public void setAnnual_closure_oldYear(String annual_closure_oldYear) {
        this.annual_closure_oldYear = annual_closure_oldYear;
    }

    public String getAnnual_closure_newYear() {
        return annual_closure_newYear;
    }

    public void setAnnual_closure_newYear(String annual_closure_newYear) {
        this.annual_closure_newYear = annual_closure_newYear;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }
}
