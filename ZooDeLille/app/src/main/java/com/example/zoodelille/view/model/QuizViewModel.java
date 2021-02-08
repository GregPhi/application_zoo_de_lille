package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.quiz.QuizRepository;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class QuizViewModel extends ViewModel {
    private final QuizRepository quizRepository;
    private final CompositeDisposable compositeDisposable;

    public QuizViewModel(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        compositeDisposable = new CompositeDisposable();
    }
}
