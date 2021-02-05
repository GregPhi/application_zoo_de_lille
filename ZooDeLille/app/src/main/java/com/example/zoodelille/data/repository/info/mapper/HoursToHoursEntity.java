package com.example.zoodelille.data.repository.info.mapper;

import com.example.zoodelille.data.api.object.info.hours.Hours;
import com.example.zoodelille.data.entity.info.hours.HoursEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HoursToHoursEntity {
    public static HoursEntity map(Hours hours){
        SimpleDateFormat formater;
        formater = new SimpleDateFormat("'le' dd MMMM yyyy");
        HoursEntity hoursEntity = new HoursEntity();
        hoursEntity.setSummerEntity(SummerToSummerEntity.map(hours.getSummer()));
        hoursEntity.setWinterEntity(WinterToWinterEntity.map(hours.getWinter()));
        String exceptional_opening = "";
        for(Date date : hours.getExceptional_opening()){
            exceptional_opening += formater.format(date)+"\n";
        }
        hoursEntity.setExceptional_opening(exceptional_opening);
        hoursEntity.setAnnual_closure_oldYear(formater.format(hours.getAnnual_closure_oldYear()));
        hoursEntity.setAnnual_closure_newYear(formater.format(hours.getAnnual_closure_newYear()));
        hoursEntity.setAttention(hours.getAttention());
        return hoursEntity;
    }
}
