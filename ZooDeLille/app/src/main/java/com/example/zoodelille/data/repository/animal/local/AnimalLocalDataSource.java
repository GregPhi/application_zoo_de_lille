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

    public Flowable<List<AnimalEntity>> getAllAnimal(){
        return projectDatabase.animalDao().getAllAnimal();
    }

    public Single<AnimalEntity> getAnimalEntity(int id){
        return projectDatabase.animalDao().getAnimalEntity(id);
    }
}
