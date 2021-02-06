package com.example.zoodelille.view.info.fragment.hours;

import com.example.zoodelille.data.entity.info.hours.SummerEntity;
import com.example.zoodelille.data.entity.info.hours.WinterEntity;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterHours extends FragmentStateAdapter {
    private static WinterEntity winterEntity;
    private static SummerEntity summerEntity;
    public ViewPagerAdapterHours(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, WinterEntity winter, SummerEntity summer) {
        super(fragmentManager, lifecycle);
        winterEntity = winter;
        summerEntity = summer;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            WinterFragment.passWinter(winterEntity);
            return WinterFragment.getInstance();
        }
        SummerFragment.passSummer(summerEntity);
        return SummerFragment.getInstance();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
