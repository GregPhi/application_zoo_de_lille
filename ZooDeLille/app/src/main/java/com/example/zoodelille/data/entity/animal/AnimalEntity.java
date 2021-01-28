package com.example.zoodelille.data.entity.animal;


import com.example.zoodelille.data.entity.VisibleEntity;
import com.example.zoodelille.data.entity.quiz.QuizEntity;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity
public class AnimalEntity extends VisibleEntity {
    private String situation_geo_picture_url;
    private String mp3_url;
    private String latin_name;
    private String do_you_know;
    private String description;
    private String classification;
    private String menaced;
    private boolean favorite;
    @Embedded
    private QuizEntity quiz;
    private String picture;

    public AnimalEntity() {
        super();
        this.favorite = false;
    }

    public String getSituation_geo_picture_url() {
        return situation_geo_picture_url;
    }

    public void setSituation_geo_picture_url(String situation_geo_picture_url) {
        this.situation_geo_picture_url = situation_geo_picture_url;
    }

    public String getMp3_url() {
        return mp3_url;
    }

    public void setMp3_url(String mp3_url) {
        this.mp3_url = mp3_url;
    }

    public String getLatin_name() {
        return latin_name;
    }

    public void setLatin_name(String latin_name) {
        this.latin_name = latin_name;
    }

    public String getDo_you_know() {
        return do_you_know;
    }

    public void setDo_you_know(String do_you_know) {
        this.do_you_know = do_you_know;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getMenaced() {
        return menaced;
    }

    public void setMenaced(String menaced) {
        this.menaced = menaced;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
