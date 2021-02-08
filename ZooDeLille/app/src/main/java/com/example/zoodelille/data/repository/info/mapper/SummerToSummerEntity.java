package com.example.zoodelille.data.repository.info.mapper;

import com.example.zoodelille.data.api.object.info.hours.Summer;
import com.example.zoodelille.data.entity.info.hours.SummerEntity;

public class SummerToSummerEntity {
    public static SummerEntity map(Summer summer){
        SummerEntity summerEntity = new SummerEntity();
        summerEntity.setOpen_hour_week_summer(summer.getOpen_hour_week_summer());
        summerEntity.setClose_hour_week_summer(summer.getClose_hour_week_summer());
        summerEntity.setClose_hour_weekend_summer(summer.getClose_hour_weekend_summer());
        summerEntity.setClose_day_summer(summer.getClose_day_summer());
        return summerEntity;
    }
}
