package com.example.zoodelille.view.model;

import com.example.zoodelille.data.repository.quiz.QuizRepository;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class QuizViewModel extends ViewModel {
    private final QuizRepository quizRepository;
    private final CompositeDisposable compositeDisposable;

    private final MutableLiveData<List<QuizItemViewModel>> quizzes = new MutableLiveData<>();
    private final MutableLiveData<QuizItemViewModel> quiz = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoad = new MutableLiveData<>();

    private final MutableLiveData<Event<Boolean>> quizMakeIt = new MutableLiveData<>();

    public QuizViewModel(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<Event<Boolean>> getQuizMakeIt() {
        return quizMakeIt;
    }

    public MutableLiveData<List<QuizItemViewModel>> getQuizzes() {
        isLoad.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(quizRepository.getAllQuiz()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<QuizItemViewModel>>() {
                    @Override
                    public void onNext(List<QuizItemViewModel> quizItemViewModels) {
                        isLoad.postValue(false);
                        quizzes.setValue(quizItemViewModels);
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

    public MutableLiveData<List<QuizItemViewModel>> getQuizzesMake() {
        isLoad.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(quizRepository.getAllMakeQuiz()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<QuizItemViewModel>>() {
                    @Override
                    public void onNext(List<QuizItemViewModel> quizItemViewModels) {
                        isLoad.postValue(false);
                        quizzes.setValue(quizItemViewModels);
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

    public MutableLiveData<QuizItemViewModel> getQuiz(int id){
        compositeDisposable.clear();
        compositeDisposable.add(quizRepository.getAQuiz(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<QuizItemViewModel>() {
                    @Override
                    public void onNext(QuizItemViewModel quizItemViewModel) {
                        isLoad.postValue(false);
                        quiz.setValue(quizItemViewModel);
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
        return quiz;
    }

    public void quizMake(final QuizItemViewModel quiz) {
        compositeDisposable.clear();
        compositeDisposable.add(quizRepository.addQuizMake(quiz)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        quizMakeIt.setValue(new Event<>(true));
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}
