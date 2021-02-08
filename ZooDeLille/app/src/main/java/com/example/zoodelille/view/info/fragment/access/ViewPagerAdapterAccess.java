package com.example.zoodelille.view.info.fragment.access;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterAccess extends FragmentStateAdapter {
    private final String auto;
    private final String bus;
    private final String metro;
    private final String vlille;

    public ViewPagerAdapterAccess(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String auto, String bus, String metro, String vlille) {
        super(fragmentManager, lifecycle);
        this.auto = auto;
        this.bus = bus;
        this.metro = metro;
        this.vlille = vlille;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            BusFragment.passBus(bus);
            return BusFragment.getInstance();
        }
        if(position == 2){
            MetroFragment.passMetro(metro);
            return MetroFragment.getInstance();
        }
        if(position == 3){
            VLilleFragment.passVLille(vlille);
            return VLilleFragment.getInstance();
        }
        AutoFragment.passAuto(auto);
        return AutoFragment.getInstance();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
