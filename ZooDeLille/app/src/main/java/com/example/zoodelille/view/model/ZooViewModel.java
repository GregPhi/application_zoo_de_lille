package com.example.zoodelille.view.model;

import android.annotation.SuppressLint;

import com.example.zoodelille.data.api.object.Zoo;
import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.repository.animal.AnimalRepository;
import com.example.zoodelille.data.repository.animal.mapper.AnimalToAnimalEntity;
import com.example.zoodelille.data.repository.zoo.ZooRepository;
import com.example.zoodelille.data.repository.zoo.mapper.ZooToZooEntity;

import java.util.List;

import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.functions.Function;

public class ZooViewModel extends ViewModel {
    private final ZooRepository zooRepository;
    private final AnimalRepository animalRepository;

    private final ZooToZooEntity zooToZooEntity = new ZooToZooEntity();
    private final AnimalToAnimalEntity animalToAnimalEntity = new AnimalToAnimalEntity();

    public ZooViewModel(ZooRepository zooRepository, AnimalRepository animalRepository) {
        this.zooRepository = zooRepository;
        this.animalRepository = animalRepository;
    }

    public Completable checkVersion(){
        System.out.println("checkVersion");
        return zooRepository.findZooVersion()
                .flatMapCompletable(new Function<ZooEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(final ZooEntity zooEntityLocal) throws Exception {
                        zooRepository.getVersion().map(new Function<Zoo, ZooEntity>() {
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
                                        animalRepository.getAllAnimals()
                                                .map(new Function<List<Animal>, List<AnimalEntity>>() {
                                                    @Override
                                                    public List<AnimalEntity> apply(List<Animal> animals) throws Exception {
                                                        return animalToAnimalEntity.map(animals);
                                                    }
                                                })
                                                .flatMapCompletable(new Function<List<AnimalEntity>, CompletableSource>() {
                                                    @Override
                                                    public CompletableSource apply(List<AnimalEntity> animalEntities) throws Exception {
                                                        return animalRepository.addAllAnimals(animalEntities);
                                                    }
                                                });
                                    }
                                    return zooRepository.update(zooEntityApi);
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
