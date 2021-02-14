package com.example.zoodelille.view.quiz.fragment.makequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zoodelille.R;
import com.example.zoodelille.data.di.DepencyInjector;
import com.example.zoodelille.view.model.Event;
import com.example.zoodelille.view.model.QuizViewModel;
import com.example.zoodelille.view.quiz.adapter.item.AnswerItemViewModel;
import com.example.zoodelille.view.quiz.adapter.item.QuestionItemViewModel;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MakeAQuizActivity extends AppCompatActivity {
    private QuizViewModel quizViewModel;
    private QuizItemViewModel quizItemViewModel;
    private static int TAG_QUIZ_ID = -1;

    private TextView question;
    private ImageView url_extra;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    int nbQuestion = 0;
    int nbGoodAnswer = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);
        question = findViewById(R.id.question);
        url_extra = findViewById(R.id.url_extra);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        Intent intent = getIntent();
        TAG_QUIZ_ID = intent.getIntExtra("id",-1);

        if(TAG_QUIZ_ID==-1){
            finish();
        }

        quizViewModel = new ViewModelProvider(this, DepencyInjector.getViewModelFactoryQuiz()).get(QuizViewModel.class);
        quizViewModel.getQuiz(TAG_QUIZ_ID).observeForever(new Observer<QuizItemViewModel>() {
            @Override
            public void onChanged(QuizItemViewModel item) {
                quizItemViewModel = item;
                showQuestion();
            }
        });
    }

    public void showQuestion(){
        QuestionItemViewModel questionItemViewModel = quizItemViewModel.getQuestions().get(nbQuestion);
        question.setText(questionItemViewModel.getQuestion());
        List<AnswerItemViewModel> answerItemViewModels = questionItemViewModel.getAnswers();
        setupAnswer(answerItemViewModels.get(0),answer1);
        setupAnswer(answerItemViewModels.get(1),answer2);
        setupAnswer(answerItemViewModels.get(2),answer3);
        setupAnswer(answerItemViewModels.get(3),answer4);
    }

    public void setupAnswer(final AnswerItemViewModel answerItemViewModel, Button answer){
        answer.setText(answerItemViewModel.getAnswer());
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerItemViewModel.isGood()){
                    nbGoodAnswer++;
                }
                nbQuestion++;
                if(nbQuestion >= quizItemViewModel.getQuestions().size()){
                    quizItemViewModel.setMake();
                    quizItemViewModel.setBest_score(nbGoodAnswer);
                    quizViewModel.quizMake(quizItemViewModel);
                    quizViewModel.getQuizMakeIt().observeForever(new Observer<Event<Boolean>>() {
                        @Override
                        public void onChanged(Event<Boolean> event) {
                            //Do nothing
                        }
                    });
                    finish();
                }else{
                    showQuestion();
                }
            }
        });
    }
}
