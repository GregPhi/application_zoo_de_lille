package com.example.zoodelille.data.di;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.zoodelille.data.api.service.ZooService;
import com.example.zoodelille.data.db.ProjectDatabase;
import com.example.zoodelille.data.repository.animal.AnimalRepository;
import com.example.zoodelille.data.repository.animal.local.AnimalLocalDataSource;
import com.example.zoodelille.data.repository.animal.remote.AnimalRemoteDataSource;
import com.example.zoodelille.data.repository.zoo.ZooRepository;
import com.example.zoodelille.data.repository.zoo.local.ZooLocalDataSource;
import com.example.zoodelille.data.repository.zoo.remote.ZooRemoteDataSource;
import com.example.zoodelille.view.model.ViewModelFactoryAnimal;
import com.example.zoodelille.view.model.ViewModelFactoryZoo;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import androidx.room.Room;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepencyInjector {
    private static ZooService zooService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static AnimalRepository animalRepository;
    private static ZooRepository zooRepository;
    private static ProjectDatabase projectDatabase;
    @SuppressLint("StaticFieldLeak")
    private static Context applyContext;
    private static ViewModelFactoryAnimal viewModelFactoryAnimal;
    private static ViewModelFactoryZoo viewModelFactoryZoo;

    public static ViewModelFactoryAnimal getViewModelFactoryAnimal() {
        if (viewModelFactoryAnimal == null) {
            viewModelFactoryAnimal = new ViewModelFactoryAnimal(getAnimalRepository());
        }
        return viewModelFactoryAnimal;
    }

    public static ViewModelFactoryZoo getViewModelFactoryZoo() {
        if (viewModelFactoryZoo == null) {
            viewModelFactoryZoo = new ViewModelFactoryZoo(getZooRepository(), getAnimalRepository());
        }
        return viewModelFactoryZoo;
    }

    public static AnimalRepository getAnimalRepository(){
        if(animalRepository == null){
            animalRepository = new AnimalRepository(new AnimalLocalDataSource(getProjectDatabase()), new AnimalRemoteDataSource(getZooService()));
        }
        return animalRepository;
    }

    public static ZooRepository getZooRepository(){
        if(zooRepository == null){
            zooRepository = new ZooRepository(new ZooLocalDataSource(getProjectDatabase()), new ZooRemoteDataSource(getZooService()));
        }
        return zooRepository;
    }

    public static ZooService getZooService(){
        if(zooService == null){
            zooService = getRetrofit().create(ZooService.class);
        }
        return zooService;
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ZooService.URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applyContext = context;
    }

    public static ProjectDatabase getProjectDatabase(){
        if(projectDatabase == null){
            projectDatabase = Room.databaseBuilder(applyContext, ProjectDatabase.class,"zoo-database").build();
        }
        return projectDatabase;
    }
}

