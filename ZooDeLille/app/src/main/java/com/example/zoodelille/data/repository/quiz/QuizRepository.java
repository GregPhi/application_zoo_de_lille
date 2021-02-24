package com.example.zoodelille.data.repository.quiz;

import com.example.zoodelille.data.api.object.quiz.Quiz;
import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;
import com.example.zoodelille.data.repository.quiz.answer.local.AnswerLocalDataSource;
import com.example.zoodelille.data.repository.quiz.local.QuizLocalDataSource;
import com.example.zoodelille.data.repository.quiz.question.local.QuestionLocalDataSource;
import com.example.zoodelille.data.repository.quiz.remote.QuizRemoteDataSource;
import com.example.zoodelille.view.quiz.adapter.item.QuestionItemViewModel;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;
import com.example.zoodelille.view.quiz.mapper.QuizEntityToQuizItemViewModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class QuizRepository {
    private final QuizLocalDataSource quizLocalDataSource;
    private final QuizRemoteDataSource quizRemoteDataSource;
    private final QuestionLocalDataSource questionLocalDataSource;
    private final AnswerLocalDataSource answerLocalDataSource;

    private final QuizEntityToQuizItemViewModel quizEntityToQuizItemViewModel = new QuizEntityToQuizItemViewModel();

    public QuizRepository(QuizLocalDataSource quizLocalDataSource, QuizRemoteDataSource quizRemoteDataSource, QuestionLocalDataSource questionLocalDataSource, AnswerLocalDataSource answerLocalDataSource) {
        this.quizLocalDataSource = quizLocalDataSource;
        this.quizRemoteDataSource = quizRemoteDataSource;
        this.questionLocalDataSource = questionLocalDataSource;
        this.answerLocalDataSource = answerLocalDataSource;
    }

    public Completable addQuiz(QuizEntity quizEntity){
        return quizLocalDataSource.addQuiz(quizEntity);
    }

    public Completable addQuizMake(QuizItemViewModel quizItemViewModel){
        return quizLocalDataSource.addQuiz(quizEntityToQuizItemViewModel.reverse(quizItemViewModel));
    }


    public Completable addAllQuiz(List<QuizEntity> quizEntity){
        return quizLocalDataSource.addAllQuiz(quizEntity);
    }

    public Completable deleteQuizWithId(int id){
        return quizLocalDataSource.deleteQuizWithId(id);
    }

    public Flowable<QuizEntity> getQuizEntity(int id){
        return quizLocalDataSource.getQuizEntity(id);
    }

    public Flowable<List<QuizItemViewModel>> getAllQuiz(){
        return quizLocalDataSource.getAllQuiz()
                .map(new Function<List<QuizEntity>, List<QuizItemViewModel>>() {
                    @Override
                    public List<QuizItemViewModel> apply(List<QuizEntity> quizEntities) throws Exception {
                        return quizEntityToQuizItemViewModel.map(quizEntities);
                    }
                });
    }

    public Single<List<Integer>> getAllMakeQuizId(){
        return quizLocalDataSource.getAllMakeQuizId();
    }

    public Flowable<List<QuizItemViewModel>> getAllMakeQuiz(){
        return quizLocalDataSource.getAllMakeQuiz()
                .map(new Function<List<QuizEntity>, List<QuizItemViewModel>>() {
                    @Override
                    public List<QuizItemViewModel> apply(List<QuizEntity> quizEntities) throws Exception {
                        return quizEntityToQuizItemViewModel.map(quizEntities);
                    }
                });
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

    public Flowable<QuizItemViewModel> getAQuiz(int id){
        final Flowable<List<QuestionItemViewModel>> questionItemViewModels = questionLocalDataSource.getAllQuestionWithQuizId(id)
                .map(new Function<List<QuestionEntity>, List<QuestionItemViewModel>>() {
                    @Override
                    public List<QuestionItemViewModel> apply(List<QuestionEntity> questionEntities) throws Exception {
                        return quizEntityToQuizItemViewModel.mapQuestions(questionEntities);
                    }
                }).zipWith(answerLocalDataSource.getAllAnswer(), new BiFunction<List<QuestionItemViewModel>, List<AnswerEntity>, List<QuestionItemViewModel>>() {
                    @Override
                    public List<QuestionItemViewModel> apply(List<QuestionItemViewModel> questionItemViewModels, List<AnswerEntity> answerEntities) throws Exception {
                        for(QuestionItemViewModel questionItemViewModel : questionItemViewModels){
                            for(AnswerEntity answerEntity : answerEntities){
                                if(questionItemViewModel.getId() == answerEntity.getQuestionid()){
                                    questionItemViewModel.addAnswer(quizEntityToQuizItemViewModel.mapAnswer(answerEntity));
                                }
                            }
                        }
                        return questionItemViewModels;
                    }
                });

        final Flowable<QuizItemViewModel> quizItemViewModel = quizLocalDataSource.getQuizEntity(id)
                .map(new Function<QuizEntity, QuizItemViewModel>() {
                    @Override
                    public QuizItemViewModel apply(QuizEntity quizEntity) throws Exception {
                        return quizEntityToQuizItemViewModel.map(quizEntity);
                    }
                });

        return Flowable.zip(quizItemViewModel, questionItemViewModels, new BiFunction<QuizItemViewModel, List<QuestionItemViewModel>, QuizItemViewModel>() {
            @Override
            public QuizItemViewModel apply(QuizItemViewModel quizItemViewModel, List<QuestionItemViewModel> questionItemViewModels) throws Exception {
                quizItemViewModel.setQuestions(questionItemViewModels);
                return quizItemViewModel;
            }
        });
    }

/*
    public Flowable<List<QuizItemViewModel>> getAllQuiz(){
        final Flowable<List<QuestionEntity>> questionEntities = questionLocalDataSource.getAllQuestion();
        final Flowable<List<AnswerEntity>> answerEntities = answerLocalDataSource.getAllAnswer();
        final Flowable<List<QuizEntity>> quizEntities = quizLocalDataSource.getAllQuiz();
        Flowable<List<QuizItemViewModel>> quiz = Flowable.zip(quizEntities, questionEntities, answerEntities, new Function3<List<QuizEntity>, List<QuestionEntity>, List<AnswerEntity>, List<QuizItemViewModel>>() {
            @Override
            public List<QuizItemViewModel> apply(List<QuizEntity> quizEntities, List<QuestionEntity> questionEntities, List<AnswerEntity> answerEntities) throws Exception {
                return quizEntityToQuizItemViewModel.mapAll(quizEntities,questionEntities,answerEntities);
            }
        });
        return quiz;
    }
 */
}
