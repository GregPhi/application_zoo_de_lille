package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.info.InfoRepository;

import androidx.lifecycle.ViewModel;

public class InfoViewModel extends ViewModel {
    private final InfoRepository infoRepository;

    public InfoViewModel(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }
}
