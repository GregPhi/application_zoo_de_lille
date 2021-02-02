package com.example.zoodelille.data.repository.zoo;

import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.repository.animal.local.AnimalLocalDataSource;
import com.example.zoodelille.data.repository.animal.remote.AnimalRemoteDataSource;
import com.example.zoodelille.data.repository.zoo.local.ZooLocalDataSource;
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

    public ZooRepository(ZooLocalDataSource zooLocalDataSource, ZooRemoteDataSource zooRemoteDataSource, AnimalLocalDataSource animalLocalDataSource, AnimalRemoteDataSource animalRemoteDataSource) {
        this.zooLocalDataSource = zooLocalDataSource;
        this.zooRemoteDataSource = zooRemoteDataSource;
        this.animalLocalDataSource = animalLocalDataSource;
        this.animalRemoteDataSource = animalRemoteDataSource;
    }

    public Completable checkVersion(){
        return zooRemoteDataSource.getVersionEntity()
                .flatMapCompletable(new Function<ZooEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(final ZooEntity zooEntityAPI) {
                        return zooLocalDataSource.findZooVersion()
                                .map(new Function<List<ZooEntity>, ZooEntity>() {
                                    @Override
                                    public ZooEntity apply(List<ZooEntity> zooEntities) {
                                       return (zooEntities.isEmpty()) ? null : zooEntities.get(0);
                                    }
                                })
                                .map(new Function<ZooEntity, Boolean>() {
                                    @Override
                                    public Boolean apply(ZooEntity zooEntityLocal) {
                                        return zooEntityAPI.equals(zooEntityLocal);
                                    }
                                })
                                .flatMapCompletable(new Function<Boolean, CompletableSource>() {
                                    @Override
                                    public CompletableSource apply(Boolean versionApiAndLocalAreEquals) {
                                        if(!versionApiAndLocalAreEquals){
                                            return animalRemoteDataSource.getAllAnimalsEntities()
                                                    .flatMapCompletable(new Function<List<AnimalEntity>, CompletableSource>() {
                                                        @Override
                                                        public CompletableSource apply(List<AnimalEntity> animalEntities) {
                                                            return animalLocalDataSource.addAllAnimals(animalEntities);
                                                        }
                                                    });
                                        }
                                        return Completable.complete();
                                    }
                                })
                                .andThen(zooLocalDataSource.update(zooEntityAPI));
                        }
                });
    }

}

