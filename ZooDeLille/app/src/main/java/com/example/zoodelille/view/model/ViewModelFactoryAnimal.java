package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.animal.AnimalRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactoryAnimal implements ViewModelProvider.Factory {

    private final AnimalRepository animalRepository;

    public ViewModelFactoryAnimal(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AnimalViewModel.class)) {
            return (T) new AnimalViewModel(animalRepository);
        }
        //Handle favorite view model case
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
