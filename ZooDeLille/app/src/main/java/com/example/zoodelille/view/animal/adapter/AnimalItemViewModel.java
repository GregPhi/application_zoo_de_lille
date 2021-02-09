package com.example.zoodelille.view.animal.adapter;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zoodelille.data.entity.quiz.QuizEntity;

public class AnimalItemViewModel implements Parcelable {
    private int id;
    private String name;
    private float longitude;
    private float latitude;
    private String situation_geo_picture_url;
    private String mp3_url;
    private String latin_name;
    private String do_you_know;
    private String description;
    private String classification;
    private String menaced;
    private boolean favorite;
    private QuizEntity quiz;
    private String picture;

    public AnimalItemViewModel() {
    }

    protected AnimalItemViewModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        longitude = in.readFloat();
        latitude = in.readFloat();
        situation_geo_picture_url = in.readString();
        mp3_url = in.readString();
        latin_name = in.readString();
        do_you_know = in.readString();
        description = in.readString();
        classification = in.readString();
        menaced = in.readString();
        favorite = in.readByte() != 0;
        picture = in.readString();
    }

    public static final Creator<AnimalItemViewModel> CREATOR = new Creator<AnimalItemViewModel>() {
        @Override
        public AnimalItemViewModel createFromParcel(Parcel in) {
            return new AnimalItemViewModel(in);
        }

        @Override
        public AnimalItemViewModel[] newArray(int size) {
            return new AnimalItemViewModel[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setSituation_geo_picture_url(String situation_geo_picture_url) {
        this.situation_geo_picture_url = situation_geo_picture_url;
    }

    public void setMp3_url(String mp3_url) {
        this.mp3_url = mp3_url;
    }

    public void setLatin_name(String latin_name) {
        this.latin_name = latin_name;
    }

    public void setDo_you_know(String do_you_know) {
        this.do_you_know = do_you_know;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setMenaced(String menaced) {
        this.menaced = menaced;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getSituation_geo_picture_url() {
        return situation_geo_picture_url;
    }

    public String getMp3_url() {
        return mp3_url;
    }

    public String getLatin_name() {
        return latin_name;
    }

    public String getDo_you_know() {
        return do_you_know;
    }

    public String getDescription() {
        return description;
    }

    public String getClassification() {
        return classification;
    }

    public String getMenaced() {
        return menaced;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public String getPicture() {
        return picture;
    }

    public void setOppoFav(){
        if(favorite){
            favorite = false;
        }else{
            favorite = true;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeFloat(longitude);
        parcel.writeFloat(latitude);
        parcel.writeString(situation_geo_picture_url);
        parcel.writeString(mp3_url);
        parcel.writeString(latin_name);
        parcel.writeString(do_you_know);
        parcel.writeString(description);
        parcel.writeString(classification);
        parcel.writeString(menaced);
        parcel.writeByte((byte) (favorite ? 1 : 0));
        parcel.writeString(picture);
    }
}
