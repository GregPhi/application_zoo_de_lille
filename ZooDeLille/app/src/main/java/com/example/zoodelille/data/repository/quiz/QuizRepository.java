package com.example.zoodelille.data.repository.quiz;

import com.example.zoodelille.data.api.object.quiz.Quiz;
import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;
import com.example.zoodelille.data.repository.quiz.answer.local.AnswerLocalDataSource;
import com.example.zoodelille.data.repository.quiz.answer.mapper.AnswerToAnswerEntity;
import com.example.zoodelille.data.repository.quiz.local.QuizLocalDataSource;
import com.example.zoodelille.data.repository.quiz.mapper.QuizToQuizEntity;
import com.example.zoodelille.data.repository.quiz.question.local.QuestionLocalDataSource;
import com.example.zoodelille.data.repository.quiz.question.mapper.QuestionToQuestionEntity;
import com.example.zoodelille.data.repository.quiz.remote.QuizRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class QuizRepository {
    private final QuizLocalDataSource quizLocalDataSource;
    private final QuizRemoteDataSource quizRemoteDataSource;
    private final QuestionLocalDataSource questionLocalDataSource;
    private final AnswerLocalDataSource answerLocalDataSource;

    private final QuizToQuizEntity quizToQuizEntity = new QuizToQuizEntity();
    private final QuestionToQuestionEntity questionToQuestionEntity = new QuestionToQuestionEntity();
    private final AnswerToAnswerEntity answerToAnswerEntity = new AnswerToAnswerEntity();

    public QuizRepository(QuizLocalDataSource quizLocalDataSource, QuizRemoteDataSource quizRemoteDataSource, QuestionLocalDataSource questionLocalDataSource, AnswerLocalDataSource answerLocalDataSource) {
        this.quizLocalDataSource = quizLocalDataSource;
        this.quizRemoteDataSource = quizRemoteDataSource;
        this.questionLocalDataSource = questionLocalDataSource;
        this.answerLocalDataSource = answerLocalDataSource;
    }

    public Completable addQuiz(QuizEntity quizEntity){
        return quizLocalDataSource.addQuiz(quizEntity);
    }

    public Completable addAllQuiz(List<QuizEntity> quizEntity){
        return quizLocalDataSource.addAllQuiz(quizEntity);
    }

    public Completable deleteQuizWithId(int id){
        return quizLocalDataSource.deleteQuizWithId(id);
    }

    public Single<QuizEntity> getQuizEntity(int id){
        return quizLocalDataSource.getQuizEntity(id);
    }

    public Flowable<List<QuizEntity>> getAllQuiz(){
        return quizLocalDataSource.getAllQuiz();
    }

    public Flowable<List<Integer>> getAllMakeQuiz(){
        return quizLocalDataSource.getAllMakeQuiz();
    }

    public Single<List<Quiz>> getAllQuizzes(){
        return quizRemoteDataSource.getAllQuizzes();
    }

    public Completable addQuestion(QuestionEntity questionEntity){
        return questionLocalDataSource.addQuestion(questionEntity);
    }

    public Completable addAllQuestion(List<QuestionEntity> questionEntity){
        return questionLocalDataSource.addAllQuestion(questionEntity);
    }

    public Completable deleteQuestionWithId(int id){
        return questionLocalDataSource.deleteQuestionWithId(id);
    }

    public Single<QuestionEntity> getQuestionEntity(int id){
        return questionLocalDataSource.getQuestionEntity(id);
    }

    public Flowable<List<QuestionEntity>> getAllQuestion(){
        return questionLocalDataSource.getAllQuestion();
    }

    public Flowable<List<QuestionEntity>> getAllQuestionWithQuizId(int id){
        return questionLocalDataSource.getAllQuestionWithQuizId(id);
    }

    public Completable addAnswer(AnswerEntity answerEntity){
        return answerLocalDataSource.addAnswer(answerEntity);
    }

    public Completable addAllAnswer(List<AnswerEntity> answerEntity){
        return answerLocalDataSource.addAllAnswer(answerEntity);
    }

    public Completable deleteAnswerWithId(int id){
        return answerLocalDataSource.deleteAnswerWithId(id);
    }

    public Single<AnswerEntity> getAnswerEntity(int id){
        return answerLocalDataSource.getAnswerEntity(id);
    }

    public Flowable<List<AnswerEntity>> getAllAnswer(){
        return answerLocalDataSource.getAllAnswer();
    }

    public Flowable<List<AnswerEntity>> getAllAnswerWithQuestionId(int id){
        return answerLocalDataSource.getAllAnswerWithQuestionId(id);
    }
}
