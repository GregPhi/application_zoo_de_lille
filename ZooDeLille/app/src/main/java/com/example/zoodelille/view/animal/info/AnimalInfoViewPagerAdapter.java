package com.example.zoodelille.view.animal.info;

import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AnimalInfoViewPagerAdapter extends FragmentStateAdapter {
    private static AnimalItemViewModel animalItemViewModel;
    public AnimalInfoViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, AnimalItemViewModel animal) {
        super(fragmentManager, lifecycle);
        animalItemViewModel = animal;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            ZoneGeoAnimalFragment.passAnimal(animalItemViewModel);
            return ZoneGeoAnimalFragment.newInstance();
        }
        if(position == 2){
            DoYouKnowAnimalFragment.passAnimal(animalItemViewModel);
            return DoYouKnowAnimalFragment.newInstance();
        }
        DescriptionAnimalFragment.passAnimal(animalItemViewModel);
        return DescriptionAnimalFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
