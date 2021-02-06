package com.example.zoodelille.data.repository.zoo;

import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.repository.animal.local.AnimalLocalDataSource;
import com.example.zoodelille.data.repository.animal.remote.AnimalRemoteDataSource;
import com.example.zoodelille.data.repository.info.local.InfoLocalDataSource;
import com.example.zoodelille.data.repository.info.remote.InfoRemoteDataSource;
import com.example.zoodelille.data.repository.zoo.local.ZooLocalDataSource;
import com.example.zoodelille.data.repository.zoo.remote.ZooRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class ZooRepository {
    private final ZooLocalDataSource zooLocalDataSource;
    private final ZooRemoteDataSource zooRemoteDataSource;

    private final AnimalLocalDataSource animalLocalDataSource;
    private final AnimalRemoteDataSource animalRemoteDataSource;

    private final InfoLocalDataSource infoLocalDataSource;
    private final InfoRemoteDataSource infoRemoteDataSource;

    public ZooRepository(ZooLocalDataSource zooLocalDataSource, ZooRemoteDataSource zooRemoteDataSource, AnimalLocalDataSource animalLocalDataSource, AnimalRemoteDataSource animalRemoteDataSource, InfoLocalDataSource infoLocalDataSource, InfoRemoteDataSource infoRemoteDataSource) {
        this.zooLocalDataSource = zooLocalDataSource;
        this.zooRemoteDataSource = zooRemoteDataSource;
        this.animalLocalDataSource = animalLocalDataSource;
        this.animalRemoteDataSource = animalRemoteDataSource;
        this.infoLocalDataSource = infoLocalDataSource;
        this.infoRemoteDataSource = infoRemoteDataSource;
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
                                       return (zooEntities.isEmpty()) ? new ZooEntity() : zooEntities.get(0);
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
                                            Single<List<AnimalEntity>> animal = animalRemoteDataSource.getAllAnimalsEntities();
                                            Single<InfoEntity> info = infoRemoteDataSource.getInfoEntity();
                                            return Single.zip(animal, info,
                                                    new BiFunction<List<AnimalEntity>, InfoEntity, ZooVersion>() {
                                                        @Override
                                                        public ZooVersion apply(List<AnimalEntity> animalEntities, InfoEntity infoEntity) throws Exception {

                                                            return new ZooVersion(animalEntities,infoEntity);
                                                        }
                                                    })
                                                    .flatMapCompletable(new Function<ZooVersion, CompletableSource>() {
                                                        @Override
                                                        public CompletableSource apply(ZooVersion zooVersion) throws Exception {
                                                            return animalLocalDataSource.addAllAnimals(zooVersion.animalEntities)
                                                                    .andThen(infoLocalDataSource.addInfo(zooVersion.infoEntity));
                                                        }
                                                    });
                                        }
                                        return Completable.complete();
                                    }
                                })
                                .andThen(zooLocalDataSource.insert(zooEntityAPI));
                        }
                });
    }

    protected class ZooVersion{
        public List<AnimalEntity> animalEntities;
        public InfoEntity infoEntity;
        public ZooVersion(List<AnimalEntity> animalEntities, InfoEntity infoEntity) {
            this.animalEntities = animalEntities;
            this.infoEntity = infoEntity;
        }
    }

}

