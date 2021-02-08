package com.example.zoodelille.data.api.object.info.prices;

import java.util.List;

public class Prices {
    private int id;
    private List<String> prices_one_day;
    private List<String> prices_one_year;
    private List<String> prices_on_group;
    private List<String> prices_free;

    public Prices() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getPrices_one_day() {
        return prices_one_day;
    }

    public void setPrices_one_day(List<String> prices_one_day) {
        this.prices_one_day = prices_one_day;
    }

    public List<String> getPrices_one_year() {
        return prices_one_year;
    }

    public void setPrices_one_year(List<String> prices_one_year) {
        this.prices_one_year = prices_one_year;
    }

    public List<String> getPrices_on_group() {
        return prices_on_group;
    }

    public void setPrices_on_group(List<String> prices_on_group) {
        this.prices_on_group = prices_on_group;
    }

    public List<String> getPrices_free() {
        return prices_free;
    }

    public void setPrices_free(List<String> prices_free) {
        this.prices_free = prices_free;
    }
}
