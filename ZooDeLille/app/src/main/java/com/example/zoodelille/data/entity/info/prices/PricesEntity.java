package com.example.zoodelille.data.entity.info.prices;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PricesEntity {
    @PrimaryKey
    private int price_id;
    private String prices_one_day;
    private String prices_one_year;
    private String prices_on_group;
    private String prices_free;

    public PricesEntity() {
    }

    public int getPrice_id() {
        return price_id;
    }

    public void setPrice_id(int id) {
        this.price_id = id;
    }

    public String getPrices_one_day() {
        return prices_one_day;
    }

    public void setPrices_one_day(String prices_one_day) {
        this.prices_one_day = prices_one_day;
    }

    public String getPrices_one_year() {
        return prices_one_year;
    }

    public void setPrices_one_year(String prices_one_year) {
        this.prices_one_year = prices_one_year;
    }

    public String getPrices_on_group() {
        return prices_on_group;
    }

    public void setPrices_on_group(String prices_on_group) {
        this.prices_on_group = prices_on_group;
    }

    public String getPrices_free() {
        return prices_free;
    }

    public void setPrices_free(String prices_free) {
        this.prices_free = prices_free;
    }
}
