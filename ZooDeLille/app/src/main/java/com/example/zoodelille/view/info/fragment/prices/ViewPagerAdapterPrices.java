package com.example.zoodelille.view.info.fragment.prices;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterPrices extends FragmentStateAdapter {
    private final String prices_one_day;
    private final String prices_one_year;
    private final String prices_on_group;

    public ViewPagerAdapterPrices(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String prices_one_day, String prices_one_year, String prices_on_group) {
        super(fragmentManager, lifecycle);
        this.prices_one_day = prices_one_day;
        this.prices_one_year = prices_one_year;
        this.prices_on_group = prices_on_group;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        /*if(position == 1){
            PhoneFragment.passNumber(prices_one_year);
            return PhoneFragment.getInstance();
        }*/
        if(position == 1){
            PricesOnGroupFragment.passPrices(prices_on_group);
            return PricesOnGroupFragment.getInstance();
        }
        PricesOneDayFragment.passPrices(prices_one_day);
        return PricesOneDayFragment.getInstance();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
