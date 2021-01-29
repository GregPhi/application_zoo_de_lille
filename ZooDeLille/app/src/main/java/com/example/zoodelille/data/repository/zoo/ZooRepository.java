package com.example.zoodelille.data.repository.zoo;

import android.annotation.SuppressLint;

import com.example.zoodelille.data.api.object.Zoo;
import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.repository.animal.local.AnimalLocalDataSource;
import com.example.zoodelille.data.repository.animal.mapper.AnimalToAnimalEntity;
import com.example.zoodelille.data.repository.animal.remote.AnimalRemoteDataSource;
import com.example.zoodelille.data.repository.zoo.local.ZooLocalDataSource;
import com.example.zoodelille.data.repository.zoo.mapper.ZooToZooEntity;
import com.example.zoodelille.data.repository.zoo.remote.ZooRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.functions.Function;

public class ZooRepository {
    private final ZooLocalDataSource zooLocalDataSource;
    private final ZooRemoteDataSource zooRemoteDataSource;

    private final AnimalLocalDataSource animalLocalDataSource;
    private final AnimalRemoteDataSource animalRemoteDataSource;

    private final ZooToZooEntity zooToZooEntity = new ZooToZooEntity();
    private final AnimalToAnimalEntity animalToAnimalEntity = new AnimalToAnimalEntity();

    public ZooRepository(ZooLocalDataSource zooLocalDataSource, ZooRemoteDataSource zooRemoteDataSource, AnimalLocalDataSource animalLocalDataSource, AnimalRemoteDataSource animalRemoteDataSource) {
        this.zooLocalDataSource = zooLocalDataSource;
        this.zooRemoteDataSource = zooRemoteDataSource;
        this.animalLocalDataSource = animalLocalDataSource;
        this.animalRemoteDataSource = animalRemoteDataSource;
    }

    public Completable checkVersion(){
        System.out.println("checkVersion");
        return zooLocalDataSource.findZooVersion()
                .flatMapCompletable(new Function<ZooEntity, CompletableSource>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public CompletableSource apply(final ZooEntity zooEntityLocal) throws Exception {
                        zooRemoteDataSource.getVersion()
                                .map(new Function<Zoo, ZooEntity>() {
                                    @Override
                                    public ZooEntity apply(Zoo zoo) throws Exception {
                                        System.out.println("map");
                                        return zooToZooEntity.map(zoo);
                                    }
                                })
                                .flatMapCompletable(new Function<ZooEntity, CompletableSource>() {
                                    @Override
                                    public CompletableSource apply(ZooEntity zooEntityApi) throws Exception {
                                        System.out.println("compare");
                                        if(!zooEntityApi.equals(zooEntityLocal)){
                                            if(zooEntityApi.getAnimal_version() != zooEntityLocal.getAnimal_version()){
                                                System.out.println("here");
                                                animalRemoteDataSource.getAllAnimals()
                                                        .map(new Function<List<Animal>, List<AnimalEntity>>() {
                                                            @Override
                                                            public List<AnimalEntity> apply(List<Animal> animals) throws Exception {
                                                                return animalToAnimalEntity.map(animals);
                                                            }
                                                        })
                                                        .flatMapCompletable(new Function<List<AnimalEntity>, CompletableSource>() {
                                                            @Override
                                                            public CompletableSource apply(List<AnimalEntity> animalEntities) throws Exception {
                                                                return animalLocalDataSource.addAllAnimals(animalEntities);
                                                            }
                                                        });
                                            }
                                            return zooLocalDataSource.update(zooEntityApi);
                                        }else{
                                            return Completable.complete();
                                        }

                                    }
                                });
                        return Completable.complete();
                    }
                });
    }
}
