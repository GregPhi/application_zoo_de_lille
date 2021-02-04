package com.example.zoodelille.data.repository.animal.mapper;

import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.entity.ZooPositionEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.repository.quiz.mapper.QuizToQuizEntity;
import com.example.zoodelille.data.repository.zooposition.mapper.ZooPositionToZooPositionEntity;

import java.util.ArrayList;
import java.util.List;

public class AnimalToAnimalEntity {
    private static final QuizToQuizEntity quizToQuizEntity = new QuizToQuizEntity();
    private static final ZooPositionToZooPositionEntity zooPositionToZooPositionEntity = new ZooPositionToZooPositionEntity();

    public AnimalEntity map(Animal animal){
        AnimalEntity animalEntity = new AnimalEntity();

        animalEntity.setId(animal.getId());
        animalEntity.setName(animal.getName());

        ZooPositionEntity zooPositionEntity = zooPositionToZooPositionEntity.map(animal.getZooPosition());
        animalEntity.setZooPositionEntity(zooPositionEntity);
        QuizEntity quizEntity = quizToQuizEntity.map(animal.getQuiz());
        animalEntity.setQuiz(quizEntity);

        animalEntity.setSituation_geo_picture_url(animal.getSituation_geo_picture_url());
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
