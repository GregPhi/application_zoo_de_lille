package com.example.zoodelille.data.repository.info.mapper;

import android.text.TextUtils;

import com.example.zoodelille.data.api.object.info.prices.Prices;
import com.example.zoodelille.data.entity.info.prices.PricesEntity;

public class PricesToPricesEntity {
    public static PricesEntity map(Prices prices){
        PricesEntity pricesEntity = new PricesEntity();
        pricesEntity.setPrice_id(prices.getId());

        if(prices.getPrices_one_day() == null){
            pricesEntity.setPrices_one_day("Non Communiqué");
        }else{
            pricesEntity.setPrices_one_day(TextUtils.join("\n",prices.getPrices_one_day()));
        }

        if(prices.getPrices_one_year() == null){
            pricesEntity.setPrices_one_year("Non Communiqué");
        }else{
            pricesEntity.setPrices_one_year(TextUtils.join("\n",prices.getPrices_one_year()));
        }

        if(prices.getPrices_on_group() == null){
            pricesEntity.setPrices_on_group("Non Communiqué");
        }else{
            pricesEntity.setPrices_on_group(TextUtils.join("\n",prices.getPrices_on_group()));
        }

        if(prices.getPrices_free() == null){
            pricesEntity.setPrices_free("Non Communiqué");
        }else{
            pricesEntity.setPrices_free(TextUtils.join("\n",prices.getPrices_free()));
        }
        return pricesEntity;
    }
}
