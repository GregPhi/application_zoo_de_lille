package com.example.zoodelille.view.quiz.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.zoodelille.R;
import com.example.zoodelille.data.di.DepencyInjector;
import com.example.zoodelille.view.model.QuizViewModel;
import com.example.zoodelille.view.quiz.adapter.Action;
import com.example.zoodelille.view.quiz.adapter.QuizListAdapter;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;

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
    private Toolbar toolbar;

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
        toolbar = m_view.findViewById(R.id.toolbar_quizzes);
        toolbar.inflateMenu(R.menu.quizzes_filter);
        toolbar.setTitle(R.string.quizzes);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.quizzes_filter_alpha)
                {
                    // do something
                }
                else if(item.getItemId()== R.id.quizzes_filter_make)
                {
                    // do something
                }
                else{
                    // do something
                }
                return false;
            }
        });
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
    public void playAQuiz(QuizItemViewModel quizItemViewModel) {

    }
}
