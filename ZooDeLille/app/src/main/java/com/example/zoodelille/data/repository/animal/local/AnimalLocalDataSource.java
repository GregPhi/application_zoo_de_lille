package com.example.zoodelille.data.repository.animal.local;

import com.example.zoodelille.data.db.ProjectDatabase;
import com.example.zoodelille.data.entity.animal.AnimalEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class AnimalLocalDataSource {
    private final ProjectDatabase projectDatabase;

    public AnimalLocalDataSource(ProjectDatabase database) {
        this.projectDatabase = database;
    }

    public Completable addAnimal(AnimalEntity animalEntity){
        return projectDatabase.animalDao().addAnimal(animalEntity);
    }

    public Completable addAllAnimals(List<AnimalEntity> animalEntities){
        return projectDatabase.animalDao().addAllAnimals(animalEntities);
    }

    public Flowable<AnimalEntity> getAnimalEntityWithName(String name){
        return projectDatabase.animalDao().getAnimalEntityWithName(name);
    }

    public Flowable<List<AnimalEntity>> getAllAnimalOnAZ_or_ZA(boolean isAsc){
        return projectDatabase.animalDao().getAllAnimalOnAZ_or_ZA(isAsc);
    }

    public Flowable<List<AnimalEntity>> getAllAnimalOnAZ_or_ZA_WhenISFavorite_or_Not(boolean isAsc,boolean isFavorite){
        return projectDatabase.animalDao().getAllAnimalOnAZ_or_ZA_WhenISFavorite_or_Not(isAsc,isFavorite);
    }

    public Single<List<Integer>> getAllFavoriteAnimalId(){
        return projectDatabase.animalDao().getAllFavoriteAnimalId();
    }
}
