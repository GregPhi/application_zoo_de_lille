package com.example.zoodelille.view.animal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.zoodelille.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.AnimalListViewHolder> {
    private final List<AnimalItemViewModel> animals;
    private final Action action;

    public AnimalListAdapter(Action action) {
        this.animals = new ArrayList<>();
        this.action = action;
    }

    @NonNull
    @Override
    public AnimalListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_animal,parent,false);
        return new AnimalListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalListViewHolder holder, int position) {
        holder.setupViewHolder(animals.get(position));
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public void setAnimals(List<AnimalItemViewModel> animals){
        this.animals.clear();
        this.animals.addAll(animals);
        notifyDataSetChanged();
    }

    public class AnimalListViewHolder extends RecyclerView.ViewHolder{
        private final View view;
        private final ConstraintLayout cardview_animal;
        private final ImageView animal_icon;
        private final TextView animal_name;
        private final ImageButton animal_favorite;
        private AnimalItemViewModel animalItem;

        public AnimalListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.cardview_animal = view.findViewById(R.id.cardview_animal);
            this.animal_icon = view.findViewById(R.id.animal_icon);
            this.animal_name = view.findViewById(R.id.animal_name);
            this.animal_favorite = view.findViewById(R.id.animal_favorite);
        }

        public void setupButton(){
            this.cardview_animal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    action.displayAnimal(animalItem);
                }
            });
            this.animal_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animalItem.setOppoFav();
                    setAnimalFavoriteBackground();
                    action.changeStatutOfFavorite(animalItem);
                }
            });
        }

        public void setAnimalFavoriteBackground(){
            if(animalItem.isFavorite()){
                this.animal_favorite.setBackgroundResource(R.drawable.drawable_favorite);
            }else{
                this.animal_favorite.setBackgroundResource(R.drawable.drawable_unfavorite);
            }
        }

        public void setupViewHolder(AnimalItemViewModel animal){
            this.animalItem = animal;
            Glide.with(view)
                    .load(animalItem.getPicture())
                    .centerCrop()
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(animal_icon);
            animal_name.setText(animalItem.getName());
            setAnimalFavoriteBackground();
            setupButton();
        }
    }
}
