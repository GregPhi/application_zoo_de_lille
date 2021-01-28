package com.example.zoodelille.data.repository.animal.mapper;

import com.example.zoodelille.data.api.object.ZooPosition;
import com.example.zoodelille.data.api.object.animal.Animal;
import com.example.zoodelille.data.entity.ZooPositionEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.repository.quiz.mapper.QuizToQuizEntity;

import java.util.ArrayList;
import java.util.List;

public class AnimalToAnimalEntity {
    private static final QuizToQuizEntity quizToQuizEntity = new QuizToQuizEntity();

    public AnimalEntity map(Animal animal){
        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setId(animal.getId());
        animalEntity.setName(animal.getName());
        ZooPositionEntity zooPositionEntity = new ZooPositionEntity();
        ZooPosition tmp = animal.getZooPosition();
        zooPositionEntity.setZooPosition_id(tmp.getId());
        zooPositionEntity.setLatitude(tmp.getLatitude());
        zooPositionEntity.setLongitude(tmp.getLongitude());
        animalEntity.setZooPositionEntity(zooPositionEntity);
        QuizEntity quizEntity = quizToQuizEntity.map(animal.getQuiz());
        animalEntity.setQuiz(quizEntity);
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
