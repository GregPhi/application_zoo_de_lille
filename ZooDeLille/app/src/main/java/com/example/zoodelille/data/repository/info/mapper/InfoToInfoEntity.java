package com.example.zoodelille.data.repository.info.mapper;

import com.example.zoodelille.data.api.object.info.Info;
import com.example.zoodelille.data.entity.info.InfoEntity;

public class InfoToInfoEntity {
    public InfoEntity map(Info info){
        InfoEntity infoEntity = new InfoEntity();
        infoEntity.setInfo_id(info.getId());
        infoEntity.setName_zoo(info.getName());
        infoEntity.setHoursEntity(HoursToHoursEntity.map(info.getHours()));
        infoEntity.setAddress(info.getAddress());
        infoEntity.setZip_code(info.getZip_code());
        infoEntity.setStreet(info.getStreet());
        infoEntity.setMail(info.getMail());
        infoEntity.setNumber(info.getNumber());
        infoEntity.setAccessEntity(AccessToAccessEntity.map(info.getAccess()));
        infoEntity.setPricesEntity(PricesToPricesEntity.map(info.getPrices()));
        return infoEntity;
    }
}
