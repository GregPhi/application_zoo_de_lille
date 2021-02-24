package com.example.zoodelille.view;

import com.example.zoodelille.view.animal.fragment.AnimalFragment;
import com.example.zoodelille.view.home.fragment.HomeFragment;
import com.example.zoodelille.view.map.fragment.MapFragment;
import com.example.zoodelille.view.qrcode.fragment.QRCodeFragment;
import com.example.zoodelille.view.quiz.fragment.QuizFragment;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterApplication extends FragmentStateAdapter {

    public ViewPagerAdapterApplication(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
/*
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            return RouteFragment.newInstance();
        }
        if(position == 2){
            return AnimalFragment.newInstance();
        }
        if(position == 3){
            return MapFragment.newInstance();
        }
        if(position == 4){
            return QuizFragment.newInstance();
        }
        if(position == 5){
            return QRCodeFragment.newInstance();
        }
        return HomeFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 6;
    }
*/
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            return AnimalFragment.newInstance();
        }
        if(position == 2){
            return MapFragment.newInstance();
        }
        if(position == 3){
            return QuizFragment.newInstance();
        }
        /*if(position == 4){
            return QRCodeFragment.newInstance();
        }*/
        return HomeFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
