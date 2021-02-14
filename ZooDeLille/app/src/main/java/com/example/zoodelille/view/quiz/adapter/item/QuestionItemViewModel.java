package com.example.zoodelille.view.quiz.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class QuestionItemViewModel implements Parcelable {
    private int id;
    private String question;
    private String url_extra;
    private List<AnswerItemViewModel> answers;
    private boolean right_answer;

    public QuestionItemViewModel() {
        this.answers = new ArrayList<>();
    }

    protected QuestionItemViewModel(Parcel in) {
        id = in.readInt();
        question = in.readString();
        url_extra = in.readString();
        answers = in.createTypedArrayList(AnswerItemViewModel.CREATOR);
        right_answer = in.readByte() != 0;
    }

    public static final Creator<QuestionItemViewModel> CREATOR = new Creator<QuestionItemViewModel>() {
        @Override
        public QuestionItemViewModel createFromParcel(Parcel in) {
            return new QuestionItemViewModel(in);
        }

        @Override
        public QuestionItemViewModel[] newArray(int size) {
            return new QuestionItemViewModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUrl_extra() {
        return url_extra;
    }

    public void setUrl_extra(String url_extra) {
        this.url_extra = url_extra;
    }

    public List<AnswerItemViewModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerItemViewModel> answers) {
        this.answers = answers;
    }

    public boolean isRight_answer() {
        return right_answer;
    }

    public void setRight_answer(boolean right_answer) {
        this.right_answer = right_answer;
    }

    public void setRight_answer(){
        this.right_answer = true;
    }

    public void addAnswer(AnswerItemViewModel answerItemViewModel){
        if(this.answers == null){
            this.answers = new ArrayList<>();
        }
        this.answers.add(answerItemViewModel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(question);
        parcel.writeString(url_extra);
        parcel.writeTypedList(answers);
        parcel.writeByte((byte) (right_answer ? 1 : 0));
    }
}
