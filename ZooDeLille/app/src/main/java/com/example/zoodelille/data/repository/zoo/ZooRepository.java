package com.example.zoodelille.data.repository.zoo;

import com.example.zoodelille.data.api.object.quiz.Quiz;
import com.example.zoodelille.data.entity.ZooEntity;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.entity.quiz.QuizEntity;
import com.example.zoodelille.data.entity.quiz.answer.AnswerEntity;
import com.example.zoodelille.data.entity.quiz.question.QuestionEntity;
import com.example.zoodelille.data.repository.animal.local.AnimalLocalDataSource;
import com.example.zoodelille.data.repository.animal.remote.AnimalRemoteDataSource;
import com.example.zoodelille.data.repository.info.local.InfoLocalDataSource;
import com.example.zoodelille.data.repository.info.remote.InfoRemoteDataSource;
import com.example.zoodelille.data.repository.quiz.answer.local.AnswerLocalDataSource;
import com.example.zoodelille.data.repository.quiz.local.QuizLocalDataSource;
import com.example.zoodelille.data.repository.quiz.mapper.QuizToQuizEntity;
import com.example.zoodelille.data.repository.quiz.question.local.QuestionLocalDataSource;
import com.example.zoodelille.data.repository.quiz.remote.QuizRemoteDataSource;
import com.example.zoodelille.data.repository.zoo.local.ZooLocalDataSource;
import com.example.zoodelille.data.repository.zoo.remote.ZooRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

public class ZooRepository {
    private final ZooLocalDataSource zooLocalDataSource;
    private final ZooRemoteDataSource zooRemoteDataSource;

    private final AnimalLocalDataSource animalLocalDataSource;
    private final AnimalRemoteDataSource animalRemoteDataSource;

    private final InfoLocalDataSource infoLocalDataSource;
    private final InfoRemoteDataSource infoRemoteDataSource;

    private final QuizLocalDataSource quizLocalDataSource;
    private final QuestionLocalDataSource questionLocalDataSource;
    private final AnswerLocalDataSource answerLocalDataSource;
    private final QuizRemoteDataSource quizRemoteDataSource;

    public ZooRepository(ZooLocalDataSource zooLocalDataSource, ZooRemoteDataSource zooRemoteDataSource, AnimalLocalDataSource animalLocalDataSource, AnimalRemoteDataSource animalRemoteDataSource, InfoLocalDataSource infoLocalDataSource, InfoRemoteDataSource infoRemoteDataSource, QuizLocalDataSource quizLocalDataSource, QuestionLocalDataSource questionLocalDataSource, AnswerLocalDataSource answerLocalDataSource, QuizRemoteDataSource quizRemoteDataSource) {
        this.zooLocalDataSource = zooLocalDataSource;
        this.zooRemoteDataSource = zooRemoteDataSource;
        this.animalLocalDataSource = animalLocalDataSource;
        this.animalRemoteDataSource = animalRemoteDataSource;
        this.infoLocalDataSource = infoLocalDataSource;
        this.infoRemoteDataSource = infoRemoteDataSource;
        this.quizLocalDataSource = quizLocalDataSource;
        this.questionLocalDataSource = questionLocalDataSource;
        this.answerLocalDataSource = answerLocalDataSource;
        this.quizRemoteDataSource = quizRemoteDataSource;
    }

    public Completable checkVersion(){
        return zooRemoteDataSource.getVersionEntity()
                .flatMapCompletable(new Function<ZooEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(final ZooEntity zooEntityAPI) {
                        return zooLocalDataSource.findZooVersion()
                                .map(new Function<List<ZooEntity>, ZooEntity>() {
                                    @Override
                                    public ZooEntity apply(List<ZooEntity> zooEntities) {
                                       return (zooEntities.isEmpty()) ? new ZooEntity() : zooEntities.get(0);
                                    }
                                })
                                .map(new Function<ZooEntity, Boolean>() {
                                    @Override
                                    public Boolean apply(ZooEntity zooEntityLocal) {
                                        return zooEntityAPI.equals(zooEntityLocal);
                                    }
                                })
                                .flatMapCompletable(new Function<Boolean, Completable>() {
                                    @Override
                                    public Completable apply(Boolean versionApiAndLocalAreEquals) {
                                        if(!versionApiAndLocalAreEquals){
                                            Single<List<AnimalEntity>> animal = animalRemoteDataSource.getAllAnimalsEntities()
                                                    .zipWith(animalLocalDataSource.getAllFavoriteAnimalId(), new BiFunction<List<AnimalEntity>, List<Integer>, List<AnimalEntity>>() {
                                                        @Override
                                                        public List<AnimalEntity> apply(List<AnimalEntity> animalEntities, List<Integer> ids) throws Exception {
                                                            for(AnimalEntity animalEntity : animalEntities){
                                                                if(ids.contains(animalEntity.getId())){
                                                                    animalEntity.setFav();
                                                                }
                                                            }
                                                            return animalEntities;
                                                        }
                                                    });
                                            Single<InfoEntity> info = infoRemoteDataSource.getInfoEntity();
                                            final List<QuestionEntity> questionEntities = new ArrayList<>();
                                            final List<AnswerEntity> answerEntities = new ArrayList<>();
                                            final List<QuizEntity> quizEntities = new ArrayList<>();
                                            Single<List<Quiz>> quiz = quizRemoteDataSource.getAllQuizzes();
                                            final Single<QuizVersion> quizVersionSingle = quiz.map(new Function<List<Quiz>, QuizVersion>() {
                                                @Override
                                                public QuizVersion apply(List<Quiz> quizzes) throws Exception {
                                                    QuizToQuizEntity quizToQuizEntity = new QuizToQuizEntity();
                                                    questionEntities.addAll(quizToQuizEntity.mapQuestion(quizzes));
                                                    answerEntities.addAll(quizToQuizEntity.mapAnswer(quizzes));
                                                    quizEntities.addAll(quizToQuizEntity.map(quizzes));
                                                    return new QuizVersion(quizEntities,questionEntities,answerEntities);
                                                }
                                            });
                                            return Single.zip(animal, info,
                                                    new BiFunction<List<AnimalEntity>, InfoEntity, ZooVersion>() {
                                                        @Override
                                                        public ZooVersion apply(List<AnimalEntity> animalEntities, InfoEntity infoEntity) throws Exception {
                                                            return new ZooVersion(animalEntities,infoEntity);
                                                        }
                                                    })
                                                    .flatMapCompletable(new Function<ZooVersion, CompletableSource>() {
                                                        @Override
                                                        public CompletableSource apply(ZooVersion zooVersion) throws Exception {
                                                            return animalLocalDataSource.addAllAnimals(zooVersion.animalEntities)
                                                                    .andThen(infoLocalDataSource.addInfo(zooVersion.infoEntity))
                                                                    .andThen(quizVersionSingle.flatMapCompletable(new Function<QuizVersion, CompletableSource>() {
                                                                        @Override
                                                                        public CompletableSource apply(QuizVersion quizVersion) throws Exception {
                                                                            return quizLocalDataSource.addAllQuiz(quizVersion.quizEntities)
                                                                                    .andThen(questionLocalDataSource.addAllQuestion(quizVersion.questionEntities))
                                                                                    .andThen(answerLocalDataSource.addAllAnswer(quizVersion.answerEntities));
                                                                        }
                                                                    }));
                                                        }
                                                    });
                                        }
                                        return Completable.complete();
                                    }
                                })
                                .andThen(zooLocalDataSource.insert(zooEntityAPI));
                        }
                });
    }

    protected class ZooVersion{
        public List<AnimalEntity> animalEntities;
        public InfoEntity infoEntity;
        public ZooVersion(List<AnimalEntity> animalEntities, InfoEntity infoEntity) {
            this.animalEntities = animalEntities;
            this.infoEntity = infoEntity;
        }
    }

    protected class QuizVersion{
        public List<QuizEntity> quizEntities;
        public List<QuestionEntity> questionEntities;
        public List<AnswerEntity> answerEntities;
        public QuizVersion(List<QuizEntity> quizEntities, List<QuestionEntity> questionEntities, List<AnswerEntity> answerEntities) {
            this.quizEntities = quizEntities;
            this.questionEntities = questionEntities;
            this.answerEntities = answerEntities;
        }
    }
/*
    protected class ZooVersion{
        public List<AnimalEntity> animalEntities;
        public InfoEntity infoEntity;
        public QuizVersion quizVersion;
        public ZooVersion(List<AnimalEntity> animalEntities, InfoEntity infoEntity, QuizVersion quizVersion) {
            this.animalEntities = animalEntities;
            this.infoEntity = infoEntity;
            this.quizVersion = quizVersion;
        }
    }


*/
}

