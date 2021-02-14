package com.example.zoodelille.view.quiz.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class QuizItemViewModel implements Parcelable {
    private int id;
    private String name;
    private List<QuestionItemViewModel> questions;
    private boolean make;
    private int best_score;

    public QuizItemViewModel() {
        this.questions = new ArrayList<>();
    }

    protected QuizItemViewModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        questions = in.createTypedArrayList(QuestionItemViewModel.CREATOR);
        make = in.readByte() != 0;
        best_score = in.readInt();
    }

    public static final Creator<QuizItemViewModel> CREATOR = new Creator<QuizItemViewModel>() {
        @Override
        public QuizItemViewModel createFromParcel(Parcel in) {
            return new QuizItemViewModel(in);
        }

        @Override
        public QuizItemViewModel[] newArray(int size) {
            return new QuizItemViewModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionItemViewModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionItemViewModel> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
    }

    public boolean isMake() {
        return make;
    }

    public void setMake(boolean make) {
        this.make = make;
    }

    public void setMake(){
        this.make = true;
    }

    public int getBest_score() {
        return best_score;
    }

    public void setBest_score(int best_score) {
        this.best_score = best_score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeTypedList(questions);
        parcel.writeByte((byte) (make ? 1 : 0));
        parcel.writeInt(best_score);
    }
}
