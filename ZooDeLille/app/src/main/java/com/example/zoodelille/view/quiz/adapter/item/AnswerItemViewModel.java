package com.example.zoodelille.view.quiz.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;

public class AnswerItemViewModel implements Parcelable {
    private int id;
    private String answer;
    private String url_picture;
    private boolean isGood;

    public AnswerItemViewModel() {
    }

    protected AnswerItemViewModel(Parcel in) {
        id = in.readInt();
        answer = in.readString();
        url_picture = in.readString();
        isGood = in.readByte() != 0;
    }

    public static final Creator<AnswerItemViewModel> CREATOR = new Creator<AnswerItemViewModel>() {
        @Override
        public AnswerItemViewModel createFromParcel(Parcel in) {
            return new AnswerItemViewModel(in);
        }

        @Override
        public AnswerItemViewModel[] newArray(int size) {
            return new AnswerItemViewModel[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUrl_picture() {
        return url_picture;
    }

    public void setUrl_picture(String url_picture) {
        this.url_picture = url_picture;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public void isGoodTrue(){
        isGood = true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(answer);
        parcel.writeString(url_picture);
        parcel.writeByte((byte) (isGood ? 1 : 0));
    }
}
