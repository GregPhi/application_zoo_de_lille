package com.example.zoodelille.data.entity.info;

import com.example.zoodelille.data.entity.info.access.AccessEntity;
import com.example.zoodelille.data.entity.info.hours.HoursEntity;
import com.example.zoodelille.data.entity.info.prices.PricesEntity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;

@Entity
public class InfoEntity {
    @PrimaryKey
    private int info_id;
    private String name_zoo;
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded
    private HoursEntity hoursEntity;
    private String address;
    private int zip_code;
    private String street;
    private String mail;
    private String number;
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded
    private AccessEntity accessEntity;
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded
    private PricesEntity pricesEntity;

    public InfoEntity() {}

    public InfoEntity(int id){
        this.info_id = id;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public String getName_zoo() {
        return name_zoo;
    }

    public void setName_zoo(String name_zoo) {
        this.name_zoo = name_zoo;
    }

    public HoursEntity getHoursEntity() {
        return hoursEntity;
    }

    public void setHoursEntity(HoursEntity hoursEntity) {
        this.hoursEntity = hoursEntity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AccessEntity getAccessEntity() {
        return accessEntity;
    }

    public void setAccessEntity(AccessEntity accessEntity) {
        this.accessEntity = accessEntity;
    }

    public PricesEntity getPricesEntity() {
        return pricesEntity;
    }

    public void setPricesEntity(PricesEntity pricesEntity) {
        this.pricesEntity = pricesEntity;
    }
}
