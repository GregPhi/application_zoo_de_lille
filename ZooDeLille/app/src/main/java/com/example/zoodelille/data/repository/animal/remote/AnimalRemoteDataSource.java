package com.example.zoodelille.data.repository.animal.remote;

import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.api.service.ZooService;

import java.util.List;

import io.reactivex.Single;

public class AnimalRemoteDataSource {
    private final ZooService zooService;

    public AnimalRemoteDataSource(ZooService zooService) {
        this.zooService = zooService;
    }

    public Single<List<Animal>> getAllAnimals(){
        return zooService.getAllAnimals();
    }

    public Single<Animal> getAnAnimalWithId(int id){
        return zooService.getAnAnimalWithId(id);
    }
}
