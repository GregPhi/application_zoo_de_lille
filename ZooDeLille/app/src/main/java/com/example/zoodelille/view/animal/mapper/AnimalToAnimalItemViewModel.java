package com.example.zoodelille.view.animal.mapper;

import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class AnimalToAnimalItemViewModel {
    public AnimalItemViewModel map(AnimalEntity animal){
        AnimalItemViewModel animalItemViewModel = new AnimalItemViewModel();
        animalItemViewModel.setId(animal.getId());
        animalItemViewModel.setName(animal.getName());
        animalItemViewModel.setZooPosition(animal.getZooPositionEntity());
        animalItemViewModel.setSituation_geo_picture_url(animal.getSituation_geo_picture_url());
        animalItemViewModel.setMp3_url(animal.getMp3_url());
        animalItemViewModel.setLatin_name(animal.getLatin_name());
        animalItemViewModel.setDescription(animal.getDescription());
        animalItemViewModel.setClassification(animal.getClassification());
        animalItemViewModel.setMenaced(animal.getMenaced());
        animalItemViewModel.setFavorite(animal.isFavorite());
        animalItemViewModel.setQuiz(animal.getQuiz());
        animalItemViewModel.setPicture(animal.getPicture());
        return animalItemViewModel;
    }

    public List<AnimalItemViewModel> map(List<AnimalEntity> animals){
        List<AnimalItemViewModel> animalItemViewModels = new ArrayList<>();
        for(AnimalEntity animal : animals){
            animalItemViewModels.add(map(animal));
        }
        return animalItemViewModels;
    }
}