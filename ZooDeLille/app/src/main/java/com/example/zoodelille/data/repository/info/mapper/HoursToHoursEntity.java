package com.example.zoodelille.data.repository.info.mapper;

import android.text.TextUtils;

import com.example.zoodelille.data.api.object.info.hours.Hours;
import com.example.zoodelille.data.entity.info.hours.HoursEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoursToHoursEntity {
    public static HoursEntity map(Hours hours){
        SimpleDateFormat formater;
        formater = new SimpleDateFormat("dd MMMM yyyy");
        HoursEntity hoursEntity = new HoursEntity();
        hoursEntity.setSummerEntity(SummerToSummerEntity.map(hours.getSummer()));
        hoursEntity.setWinterEntity(WinterToWinterEntity.map(hours.getWinter()));
        if(hours.getExceptional_opening() == null){
            hoursEntity.setExceptional_opening("Non Communiqué");
        }else{
            List<String> strings = new ArrayList<>();
            for(Date date : hours.getExceptional_opening()){
                strings.add(formater.format(date));
            }
            hoursEntity.setExceptional_opening(TextUtils.join("\n", strings));
        }
        hoursEntity.setAnnual_closure_oldYear(formater.format(hours.getAnnual_closure_oldYear()));
        hoursEntity.setAnnual_closure_newYear(formater.format(hours.getAnnual_closure_newYear()));
        hoursEntity.setAttention(hours.getAttention());
        return hoursEntity;
    }
}
