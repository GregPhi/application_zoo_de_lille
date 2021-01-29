package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.zoo.ZooRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactoryZoo implements ViewModelProvider.Factory {

    private final ZooRepository zooRepository;

    public ViewModelFactoryZoo(ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ZooViewModel.class)) {
            return (T) new ZooViewModel(zooRepository);
        }
        //Handle favorite view model case
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
