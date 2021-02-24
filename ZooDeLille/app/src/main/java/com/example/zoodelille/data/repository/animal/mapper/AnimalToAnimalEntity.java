package com.example.zoodelille.data.repository.animal.mapper;

import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.api.object.quiz.Quiz;
import com.example.zoodelille.data.entity.animal.AnimalEntity;

import java.util.ArrayList;
import java.util.List;

public class AnimalToAnimalEntity {
    public AnimalEntity map(Animal animal){
        AnimalEntity animalEntity = new AnimalEntity();

        animalEntity.setId(animal.getId());
        animalEntity.setName(animal.getName());

        animalEntity.setLongitude(animal.getZooPosition().getLongitude());
        animalEntity.setLatitude(animal.getZooPosition().getLatitude());

        Quiz quiz = animal.getQuiz();
        if(quiz==null){
            animalEntity.setQuizid(-1);
        }else{
            animalEntity.setQuizid(quiz.getId());
        }

        animalEntity.setSituation_geo_picture_url(animal.getSituation_geo_picture_url());
        animalEntity.setSituation_geo_picture_description(animal.getSituation_geo_picture_description());
        animalEntity.setMp3_url(animal.getMp3_url());
        animalEntity.setLatin_name(animal.getLatin_name());
        animalEntity.setDo_you_know(animal.getDo_you_know());
        animalEntity.setDescription(animal.getDescription());
        animalEntity.setClassification(animal.getClassification());
        animalEntity.setMenaced(animal.getMenaced());
        animalEntity.setFavorite(animal.isFavorite());
        animalEntity.setPicture(animal.getPicture());
        return animalEntity;
    }

    public List<AnimalEntity> map(List<Animal> animals){
        List<AnimalEntity> animalEntities = new ArrayList<>();
        for(Animal animal : animals){
            animalEntities.add(map(animal));
        }
        return animalEntities;
    }
}
