package com.example.zoodelille.data.api.service;

import com.example.zoodelille.data.api.object.Zoo;
import com.example.zoodelille.data.api.object.animal.Animal;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZooService {
    String URL = Local.URL+"zoo-api/";

    @GET("version/")
    Single<Zoo> getVersion();

    @GET("animals/")
    Single<List<Animal>> getAllAnimals();

    @GET("animals/{id}")
    Single<Animal> getAnAnimalWithId(@Path("id") int id);
}
