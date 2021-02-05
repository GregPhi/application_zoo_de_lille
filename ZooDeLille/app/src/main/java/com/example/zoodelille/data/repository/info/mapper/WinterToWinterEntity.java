package com.example.zoodelille.data.repository.info.mapper;

import com.example.zoodelille.data.api.object.info.hours.Winter;
import com.example.zoodelille.data.entity.info.hours.WinterEntity;

public class WinterToWinterEntity {
    public static WinterEntity map(Winter summer){
        WinterEntity summerEntity = new WinterEntity();
        summerEntity.setOpen_hour_week_winter(summer.getOpen_hour_week_winter());
        summerEntity.setClose_hour_week_winter(summer.getClose_hour_week_winter());
        summerEntity.setClose_day_winter(summer.getClose_day_winter());
        return summerEntity;
    }
}
