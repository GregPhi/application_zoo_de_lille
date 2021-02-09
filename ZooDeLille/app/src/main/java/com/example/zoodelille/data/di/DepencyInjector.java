package com.example.zoodelille.data.di;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.zoodelille.data.api.service.ZooService;
import com.example.zoodelille.data.db.ProjectDatabase;
import com.example.zoodelille.data.repository.animal.AnimalRepository;
import com.example.zoodelille.data.repository.animal.local.AnimalLocalDataSource;
import com.example.zoodelille.data.repository.animal.remote.AnimalRemoteDataSource;
import com.example.zoodelille.data.repository.info.InfoRepository;
import com.example.zoodelille.data.repository.info.local.InfoLocalDataSource;
import com.example.zoodelille.data.repository.info.remote.InfoRemoteDataSource;
import com.example.zoodelille.data.repository.quiz.QuizRepository;
import com.example.zoodelille.data.repository.quiz.answer.local.AnswerLocalDataSource;
import com.example.zoodelille.data.repository.quiz.local.QuizLocalDataSource;
import com.example.zoodelille.data.repository.quiz.question.local.QuestionLocalDataSource;
import com.example.zoodelille.data.repository.quiz.remote.QuizRemoteDataSource;
import com.example.zoodelille.data.repository.zoo.ZooRepository;
import com.example.zoodelille.data.repository.zoo.local.ZooLocalDataSource;
import com.example.zoodelille.data.repository.zoo.remote.ZooRemoteDataSource;
import com.example.zoodelille.view.model.ViewModelFactoryAnimal;
import com.example.zoodelille.view.model.ViewModelFactoryInfo;
import com.example.zoodelille.view.model.ViewModelFactoryQuiz;
import com.example.zoodelille.view.model.ViewModelFactoryZoo;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import androidx.room.Room;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepencyInjector {
    private static ZooService zooService;
    private static Retrofit retrofit;
    private static Gson gson;

    private static AnimalRepository animalRepository;
    private static ZooRepository zooRepository;
    private static InfoRepository infoRepository;
    private static QuizRepository quizRepository;

    private static ProjectDatabase projectDatabase;

    @SuppressLint("StaticFieldLeak")
    private static Context applyContext;

    private static ViewModelFactoryAnimal viewModelFactoryAnimal;
    private static ViewModelFactoryZoo viewModelFactoryZoo;
    private static ViewModelFactoryInfo viewModelFactoryInfo;
    private static ViewModelFactoryQuiz viewModelFactoryQuiz;

    public static ViewModelFactoryAnimal getViewModelFactoryAnimal() {
        if (viewModelFactoryAnimal == null) {
            viewModelFactoryAnimal = new ViewModelFactoryAnimal(getAnimalRepository());
        }
        return viewModelFactoryAnimal;
    }

    public static ViewModelFactoryZoo getViewModelFactoryZoo() {
        if (viewModelFactoryZoo == null) {
            viewModelFactoryZoo = new ViewModelFactoryZoo(getZooRepository());
        }
        return viewModelFactoryZoo;
    }

    public static ViewModelFactoryInfo getViewModelFactoryInfo() {
        if (viewModelFactoryInfo == null) {
            viewModelFactoryInfo = new ViewModelFactoryInfo(getInfoRepository());
        }
        return viewModelFactoryInfo;
    }

    public static ViewModelFactoryQuiz getViewModelFactoryQuiz(){
        if(viewModelFactoryQuiz == null){
            viewModelFactoryQuiz = new ViewModelFactoryQuiz(getQuizRepository());
        }
        return viewModelFactoryQuiz;
    }

    public static AnimalRepository getAnimalRepository(){
        if(animalRepository == null){
            animalRepository = new AnimalRepository(new AnimalLocalDataSource(getProjectDatabase()), new AnimalRemoteDataSource(getZooService()));
        }
        return animalRepository;
    }

    public static ZooRepository getZooRepository(){
        if(zooRepository == null){
            zooRepository = new ZooRepository(new ZooLocalDataSource(getProjectDatabase()), new ZooRemoteDataSource(getZooService()), new AnimalLocalDataSource(getProjectDatabase()), new AnimalRemoteDataSource(getZooService()), new InfoLocalDataSource(getProjectDatabase()), new InfoRemoteDataSource(getZooService()),new QuizLocalDataSource(getProjectDatabase()), new QuestionLocalDataSource(getProjectDatabase()), new AnswerLocalDataSource(getProjectDatabase()), new QuizRemoteDataSource(getZooService()));
        }
        return zooRepository;
    }

    public static InfoRepository getInfoRepository(){
        if(infoRepository == null){
            infoRepository = new InfoRepository(new InfoLocalDataSource(getProjectDatabase()), new InfoRemoteDataSource(getZooService()));
        }
        return infoRepository;
    }

    public static QuizRepository getQuizRepository(){
        if(quizRepository == null){
            quizRepository = new QuizRepository(new QuizLocalDataSource(getProjectDatabase()), new QuizRemoteDataSource(getZooService()), new QuestionLocalDataSource(getProjectDatabase()), new AnswerLocalDataSource(getProjectDatabase()));
        }
        return quizRepository;
    }

    public static ZooService getZooService(){
        if(zooService == null){
            zooService = getRetrofit().create(ZooService.class);
        }
        return zooService;
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            // add a time out
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ZooService.URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applyContext = context;
    }

    public static ProjectDatabase getProjectDatabase(){
        if(projectDatabase == null){
            projectDatabase = Room.databaseBuilder(applyContext, ProjectDatabase.class,"zoo-database").build();
        }
        return projectDatabase;
    }
}

