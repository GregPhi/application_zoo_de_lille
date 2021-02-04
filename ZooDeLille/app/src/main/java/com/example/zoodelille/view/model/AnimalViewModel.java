package com.example.zoodelille.view.model;

import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.repository.animal.AnimalRepository;
import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;
import com.example.zoodelille.view.animal.mapper.AnimalToAnimalItemViewModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class AnimalViewModel extends ViewModel {
    private final AnimalRepository animalRepository;
    private final CompositeDisposable compositeDisposable;
    private final MutableLiveData<List<AnimalItemViewModel>> animals = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoad = new MutableLiveData<>();
    private final AnimalToAnimalItemViewModel animalToAnimalItemViewModel = new AnimalToAnimalItemViewModel();

    private final MutableLiveData<Event<Integer>> eventFavorite = new MutableLiveData<>();

    public AnimalViewModel(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void changeFavoriteStatut(final AnimalEntity animal) {
        compositeDisposable.add(animalRepository.addAnimal(animal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        eventFavorite.setValue(new Event<>(animal.getId()));
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public MutableLiveData<List<AnimalItemViewModel>> getAllAnimal() {
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

    public MutableLiveData<List<AnimalItemViewModel>> getAllAnimalOnAZ_or_ZA(boolean isAsc) {
        isLoad.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(animalRepository.getAllAnimalOnAZ_or_ZA(isAsc)
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

    public MutableLiveData<List<AnimalItemViewModel>> getAllAnimalIsFavorite_or_Not(boolean isFavorite) {
        isLoad.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(animalRepository.getAllAnimalIsFavorite_or_Not(isFavorite)
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
}
