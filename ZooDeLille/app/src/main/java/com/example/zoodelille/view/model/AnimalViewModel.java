package com.example.zoodelille.view.model;

import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.repository.animal.AnimalRepository;
import com.example.zoodelille.data.repository.animal.mapper.AnimalToAnimalEntity;
import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;
import com.example.zoodelille.view.animal.mapper.AnimalToAnimalItemViewModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class AnimalViewModel extends ViewModel {
    private final AnimalRepository animalRepository;
    private final CompositeDisposable compositeDisposable;
    private final MutableLiveData<List<AnimalItemViewModel>> animals = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoad = new MutableLiveData<>();
    private final AnimalToAnimalItemViewModel animalToAnimalItemViewModel = new AnimalToAnimalItemViewModel();

    private final AnimalToAnimalEntity animalToAnimalEntity = new AnimalToAnimalEntity();

    public AnimalViewModel(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<AnimalItemViewModel>> getAnimals() {
        isLoad.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(animalRepository.getAllAnimal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new ResourceSubscriber<List<AnimalEntity>>() {
                @Override
                public void onNext(List<AnimalEntity> animalEntities) {
                    isLoad.postValue(false);
                    animals.setValue(animalToAnimalItemViewModel.map(animalEntities));
                }

                @Override
                public void onError(Throwable t) {
                    isLoad.postValue(false);
                }

                @Override
                public void onComplete() {
                    isLoad.postValue(false);
                }
            }));
        return animals;
    }

    public MutableLiveData<Boolean> getIsLoad() {
        return isLoad;
    }

    public void retrieveAnimals(){
        List<Animal> animals = animalRepository.getAllAnimals().blockingGet();
        List<AnimalEntity> animalEntities = animalToAnimalEntity.map(animals);
        for(AnimalEntity animalEntity : animalEntities){
            animalRepository.addAnimal(animalEntity);
        }
    }
}
