package com.example.zoodelille.view.model;

import android.util.Log;

import com.example.zoodelille.data.repository.zoo.ZooRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class ZooViewModel extends ViewModel {
    private final ZooRepository zooRepository;
    private final CompositeDisposable compositeDisposable;
    private final MutableLiveData<Event<String>> checkVersionEvent = new MutableLiveData<>();

    public ZooViewModel(ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<Event<String>> getCheckVersionEvent() {
        return checkVersionEvent;
    }

    public void checkVersion(){
        compositeDisposable.clear();
        compositeDisposable.add(zooRepository.checkVersion()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    checkVersionEvent.setValue(new Event<>("zoo-version"));
                }
                @Override
                public void onError(Throwable e) {
                    Log.e("TAG_ERROR_CHECK_VERSION","-> error :",e);
                }
            }));
    }
}
