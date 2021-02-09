package com.example.zoodelille.view.model;

import android.util.Log;

import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.repository.info.InfoRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class InfoViewModel extends ViewModel {
    private final InfoRepository infoRepository;
    private final CompositeDisposable compositeDisposable;

    private final MutableLiveData<InfoEntity> infoEntityMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> zooIsOpen = new MutableLiveData<>();

    public InfoViewModel(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<InfoEntity> getInfoEntityMutableLiveData(){
        compositeDisposable.add(infoRepository.getInfoEntity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<InfoEntity>>() {
                    @Override
                    public void onNext(List<InfoEntity> infoEntities) {
                        InfoEntity infoEntity = (infoEntities.isEmpty()) ? new InfoEntity(-1) : infoEntities.get(0);
                        infoEntityMutableLiveData.setValue(infoEntity);
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onComplete() {}
                }));
        return infoEntityMutableLiveData;
    }

    public MutableLiveData<Boolean> zooIsOpen(){
        compositeDisposable.clear();
        compositeDisposable.add(infoRepository.zooIsOpen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(Boolean open) {
                        System.out.println("OPEN? :"+open);
                        zooIsOpen.setValue(open);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG_ZOO_IS_OPEN?","error -> ",e);
                    }
                }));
        return zooIsOpen;
    }
}
