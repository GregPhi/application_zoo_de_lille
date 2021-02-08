package com.example.zoodelille.data.repository.quiz.remote;

import com.example.zoodelille.data.api.object.quiz.Quiz;
import com.example.zoodelille.data.api.service.ZooService;

import java.util.List;

import io.reactivex.Single;

public class QuizRemoteDataSource {
    private final ZooService zooService;

    public QuizRemoteDataSource(ZooService zooService) {
        this.zooService = zooService;
    }

    public Single<List<Quiz>> getAllQuizzes(){
        return zooService.getAllQuizzes();
    }

}
