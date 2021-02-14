package com.example.zoodelille.view.quiz.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zoodelille.R;
import com.example.zoodelille.data.di.DepencyInjector;
import com.example.zoodelille.view.model.QuizViewModel;
import com.example.zoodelille.view.quiz.adapter.Action;
import com.example.zoodelille.view.quiz.adapter.QuizListAdapter;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;
import com.example.zoodelille.view.quiz.fragment.makequiz.MakeAQuizActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuizFragment extends Fragment implements Action {
    public static final String name = "Quiz";
    public static final int icon = R.drawable.drawable_quiz;

    private View m_view;

    private QuizViewModel quizViewModel;
    private QuizListAdapter quizListAdapter;

    public static QuizFragment newInstance(){
        return new QuizFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        m_view = inflater.inflate(R.layout.fragment_quizzes, container, false);
        return m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        initRecyclerView();
    }

    public void setupRecyclerView(){
        RecyclerView recyclerView = m_view.findViewById(R.id.recyclerview_quizzes);
        quizListAdapter = new QuizListAdapter(this);
        recyclerView.setAdapter(quizListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void initRecyclerView(){
        quizViewModel = new ViewModelProvider(requireActivity(), DepencyInjector.getViewModelFactoryQuiz()).get(QuizViewModel.class);
        quizViewModel.getQuizzes().observe(getViewLifecycleOwner(), new Observer<List<QuizItemViewModel>>() {
            @Override
            public void onChanged(List<QuizItemViewModel> quizItemViewModels) {
                quizListAdapter.setQuizzes(quizItemViewModels);
            }
        });
    }

    @Override
    public void playAQuiz(final int id) {
        Intent intent = new Intent(getActivity(), MakeAQuizActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
