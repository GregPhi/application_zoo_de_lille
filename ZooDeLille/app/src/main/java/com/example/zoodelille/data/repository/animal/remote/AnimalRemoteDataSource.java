package com.example.zoodelille.data.repository.animal.remote;

import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.api.service.ZooService;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.repository.animal.mapper.AnimalToAnimalEntity;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class AnimalRemoteDataSource {
    private final ZooService zooService;

    private final AnimalToAnimalEntity animalToAnimalEntity = new AnimalToAnimalEntity();

    public AnimalRemoteDataSource(ZooService zooService) {
        this.zooService = zooService;
    }

    public Single<List<Animal>> getAllAnimals(){
        return zooService.getAllAnimals();
    }

    public Single<List<AnimalEntity>> getAllAnimalsEntities(){
        return zooService.getAllAnimals()
                .map(new Function<List<Animal>, List<AnimalEntity>>() {
                    @Override
                    public List<AnimalEntity> apply(List<Animal> animals) {
                        return animalToAnimalEntity.map(animals);
                    }
                });
    }

    public Single<Animal> getAnAnimalWithId(int id){
        return zooService.getAnAnimalWithId(id);
    }
}
