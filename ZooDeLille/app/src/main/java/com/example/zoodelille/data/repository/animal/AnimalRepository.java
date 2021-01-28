package com.example.zoodelille.data.repository.animal;


import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.repository.animal.local.AnimalLocalDataSource;
import com.example.zoodelille.data.repository.animal.remote.AnimalRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class AnimalRepository {
    private final AnimalLocalDataSource animalLocalDataSource;
    private final AnimalRemoteDataSource animalRemoteDataSource;

    public AnimalRepository(AnimalLocalDataSource animalLocalDataSource, AnimalRemoteDataSource animalRemoteDataSource) {
        this.animalLocalDataSource = animalLocalDataSource;
        this.animalRemoteDataSource = animalRemoteDataSource;
    }

    public Completable addAnimal(AnimalEntity animalEntity){
        return animalLocalDataSource.addAnimal(animalEntity);
    }

    public Completable addAllAnimals(List<AnimalEntity> animalEntities){
        return animalLocalDataSource.addAllAnimals(animalEntities);
    }

    public Completable deleteAnimalWithId(int id){
        return animalLocalDataSource.deleteAnimalWithId(id);
    }

    public Flowable<List<AnimalEntity>> getAllAnimal(){
        return animalLocalDataSource.getAllAnimal();
    }

    public Single<AnimalEntity> getAnimalEntity(int id){
        return animalLocalDataSource.getAnimalEntity(id);
    }

    public Single<List<Animal>> getAllAnimals(){
        return animalRemoteDataSource.getAllAnimals();
    }

    public Single<Animal> getAnAnimalWithId(int id){
        return animalRemoteDataSource.getAnAnimalWithId(id);
    }
}
