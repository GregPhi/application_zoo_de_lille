package com.example.zoodelille.data.db.dao;

import com.example.zoodelille.data.entity.animal.AnimalEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addAnimal(AnimalEntity animalEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addAllAnimals(List<AnimalEntity> animalEntity);

    @Query("DELETE FROM animalentity WHERE id = :id")
    Completable deleteAnimalWithId(int id);

    @Query("SELECT * FROM animalentity WHERE id = :id")
    Single<AnimalEntity> getAnimalEntity(int id);

    @Query("SELECT * FROM animalentity")
    Flowable<List<AnimalEntity>> getAllAnimal();

    @Query("SELECT * FROM animalentity ORDER BY " +
            "CASE WHEN :isAsc = 1 THEN name END ASC," +
            "CASE WHEN :isAsc = 0 THEN name END DESC")
    Flowable<List<AnimalEntity>> getAllAnimalOnAZ_or_ZA(boolean isAsc);

    @Query("SELECT * FROM animalentity WHERE favorite = :isFavorite")
    Flowable<List<AnimalEntity>> getAllAnimalIsFavorite_or_Not(boolean isFavorite);

    @Query("SELECT id FROM animalentity WHERE favorite = 1")
    Single<List<Integer>> getAllFavoriteAnimalId();
}
