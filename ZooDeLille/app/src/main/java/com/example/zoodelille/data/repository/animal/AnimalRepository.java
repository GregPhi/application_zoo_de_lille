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

    public Flowable<AnimalEntity> getAnimalEntityWithName(String name){
        return animalLocalDataSource.getAnimalEntityWithName(name);
    }

    public Flowable<List<AnimalEntity>> getAllAnimalOnAZ_or_ZA(boolean isAsc){
        return animalLocalDataSource.getAllAnimalOnAZ_or_ZA(isAsc);
    }

    public Flowable<List<AnimalEntity>> getAllAnimalOnAZ_or_ZA_WhenISFavorite_or_Not(boolean isAsc,boolean isFavorite){
        return animalLocalDataSource.getAllAnimalOnAZ_or_ZA_WhenISFavorite_or_Not(isAsc,isFavorite);
    }

    public Single<List<Animal>> getAllAnimals(){
        return animalRemoteDataSource.getAllAnimals();
    }

    public Single<Animal> getAnAnimalWithId(int id){
        return animalRemoteDataSource.getAnAnimalWithId(id);
    }
}
