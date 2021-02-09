package com.example.zoodelille.view.model;

import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.repository.quiz.QuizRepository;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;
import com.example.zoodelille.view.quiz.mapper.QuizEntityToQuizItemViewModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class QuizViewModel extends ViewModel {
    private final QuizRepository quizRepository;
    private final CompositeDisposable compositeDisposable;

    private final MutableLiveData<List<QuizItemViewModel>> quizzes = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoad = new MutableLiveData<>();

    private final QuizEntityToQuizItemViewModel quizEntityToQuizItemViewModel = new QuizEntityToQuizItemViewModel();

    public QuizViewModel(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<QuizItemViewModel>> getQuizzes() {
        compositeDisposable.clear();
        compositeDisposable.add(quizRepository.getAllQuiz()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<QuizEntity>>() {
                    @Override
                    public void onNext(List<QuizEntity> quizEntities) {
                        isLoad.postValue(false);
                        quizzes.setValue(quizEntityToQuizItemViewModel.map(quizEntities));
                    }

                    @Override
                    public void onError(Throwable t) {
                        isLoad.postValue(false);
                    }

                    @Override
                    public void onComplete() {
                        isLoad.postValue(false);
                    }
                }));
        return quizzes;
    }
}
