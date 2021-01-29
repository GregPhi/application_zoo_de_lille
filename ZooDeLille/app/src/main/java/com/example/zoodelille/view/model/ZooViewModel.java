package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.zoo.ZooRepository;

import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;

public class ZooViewModel extends ViewModel {
    private final ZooRepository zooRepository;

    public ZooViewModel(ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
    }

    public Completable checkVersion(){
        return zooRepository.checkVersion();
    }
}
