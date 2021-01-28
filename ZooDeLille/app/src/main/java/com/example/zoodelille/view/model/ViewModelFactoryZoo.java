package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.animal.AnimalRepository;
import com.example.zoodelille.data.repository.zoo.ZooRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactoryZoo implements ViewModelProvider.Factory {

    private final ZooRepository zooRepository;
    private final AnimalRepository animalRepository;

    public ViewModelFactoryZoo(ZooRepository zooRepository, AnimalRepository animalRepository) {
        this.zooRepository = zooRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ZooViewModel.class)) {
            return (T) new ZooViewModel(zooRepository, animalRepository);
        }
        //Handle favorite view model case
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
