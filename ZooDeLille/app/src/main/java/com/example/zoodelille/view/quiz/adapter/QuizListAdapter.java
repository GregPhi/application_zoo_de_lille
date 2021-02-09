package com.example.zoodelille.view.quiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zoodelille.R;
import com.example.zoodelille.view.quiz.adapter.item.QuizItemViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizListViewHolder>{
    private final List<QuizItemViewModel> quizzes;
    private final Action action;

    public QuizListAdapter(Action action) {
        this.action = action;
        this.quizzes = new ArrayList<>();
    }

    @NonNull
    @Override
    public QuizListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_quiz,parent,false);
        return new QuizListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizListViewHolder holder, int position) {
        holder.setupViewHolder(quizzes.get(position));
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public void setQuizzes(List<QuizItemViewModel> quizItemViewModels) {
        this.quizzes.clear();
        this.quizzes.addAll(quizItemViewModels);
        notifyDataSetChanged();
    }

    public class QuizListViewHolder extends RecyclerView.ViewHolder{
        private final View view;
        private final TextView quizzes_name;
        private final TextView quizzes_level;
        private final TextView quizzes_best_score;
        private final ImageButton play_quiz;
        private QuizItemViewModel quizItemViewModel;

        public QuizListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.quizzes_name = view.findViewById(R.id.quizzes_name);
            this.quizzes_level = view.findViewById(R.id.quizzes_level);
            this.quizzes_best_score = view.findViewById(R.id.quizzes_best_score);
            this.play_quiz = view.findViewById(R.id.play_quiz);
        }

        public void setupButton(){
            this.play_quiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    action.playAQuiz(quizItemViewModel);
                }
            });
        }

        public void setupViewHolder(QuizItemViewModel item){
            this.quizItemViewModel = item;
            quizzes_name.setText(quizItemViewModel.getName());
            //quizzes_level.setText(quizItemViewModel.getName());
            //quizzes_best_score.setText(quizItemViewModel.getBest_score());
            setupButton();
        }
    }
}
