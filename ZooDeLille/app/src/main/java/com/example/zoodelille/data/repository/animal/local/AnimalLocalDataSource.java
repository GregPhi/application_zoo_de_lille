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

    public Completable deleteAnimalWithId(int id){
        return projectDatabase.animalDao().deleteAnimalWithId(id);
    }

    public Single<AnimalEntity> getAnimalEntity(int id){
        return projectDatabase.animalDao().getAnimalEntity(id);
    }

    public Flowable<List<AnimalEntity>> getAllAnimal(){
        return projectDatabase.animalDao().getAllAnimal();
    }

    public Flowable<List<AnimalEntity>> getAllAnimalOnAZ_or_ZA(boolean isAsc){
        return projectDatabase.animalDao().getAllAnimalOnAZ_or_ZA(isAsc);
    }

    public Flowable<List<AnimalEntity>> getAllAnimalIsFavorite_or_Not(boolean isFavorite){
        return projectDatabase.animalDao().getAllAnimalIsFavorite_or_Not(isFavorite);
    }

    public Single<List<Integer>> getAllFavoriteAnimalId(){
        return projectDatabase.animalDao().getAllFavoriteAnimalId();
    }
}
