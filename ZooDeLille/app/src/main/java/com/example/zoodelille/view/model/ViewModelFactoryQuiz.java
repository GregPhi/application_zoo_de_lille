package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.quiz.QuizRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactoryQuiz implements ViewModelProvider.Factory {
    private final QuizRepository quizRepository;

    public ViewModelFactoryQuiz(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ZooViewModel.class)) {
            return (T) new QuizViewModel(quizRepository);
        }
        //Handle favorite view model case
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
