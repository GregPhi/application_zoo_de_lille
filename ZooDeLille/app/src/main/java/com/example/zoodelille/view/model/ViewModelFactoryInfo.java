package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.info.InfoRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactoryInfo implements ViewModelProvider.Factory{
    private final InfoRepository infoRepository;

    public ViewModelFactoryInfo(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InfoViewModel.class)) {
            return (T) new InfoViewModel(infoRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
