package com.example.zoodelille.view.animal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.zoodelille.R;
import com.example.zoodelille.data.di.DepencyInjector;
import com.example.zoodelille.data.entity.animal.AnimalEntity;
import com.example.zoodelille.view.animal.adapter.Action;
import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;
import com.example.zoodelille.view.animal.adapter.AnimalListAdapter;
import com.example.zoodelille.view.animal.info.AnimalInfoActivity;
import com.example.zoodelille.view.animal.mapper.AnimalToAnimalItemViewModel;
import com.example.zoodelille.view.model.AnimalViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalFragment extends Fragment implements Action {
    public static final String name = "Animal";
    public static final int icon = R.drawable.drawable_animals;

    private View m_view;
    private AnimalViewModel animalViewModel;
    private AnimalListAdapter animalListAdapter;

    public static AnimalFragment newInstance(){
        return new AnimalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        m_view = inflater.inflate(R.layout.fragment_animal, container, false);
        return m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        setupRecyclerViewWith_AZ_and_NotFavorite();
        setupSwitch();
    }

    public void setupSwitch(){
        final Switch switch_fav = m_view.findViewById(R.id.switch_fav);
        switch_fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    setupRecyclerViewWith_AZ_and_Favorite();
                }else{
                    setupRecyclerViewWith_AZ_and_NotFavorite();
                }
            }
        });
    }

    public void setupRecyclerView(){
        RecyclerView recyclerView = m_view.findViewById(R.id.recyclerview_animals);
        animalListAdapter = new AnimalListAdapter(this);
        recyclerView.setAdapter(animalListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void setupRecyclerViewWith_AZ_and_NotFavorite(){
        animalViewModel = new ViewModelProvider(requireActivity(), DepencyInjector.getViewModelFactoryAnimal()).get(AnimalViewModel.class);
        animalViewModel.getAllAnimalOnAZ_or_ZA(true).observe(getViewLifecycleOwner(), new Observer<List<AnimalItemViewModel>>() {
            @Override
            public void onChanged(List<AnimalItemViewModel> animalItemViewModels) {
                animalListAdapter.setAnimals(animalItemViewModels);
            }
        });
    }

    public void setupRecyclerViewWith_AZ_and_Favorite(){
        animalViewModel = new ViewModelProvider(requireActivity(), DepencyInjector.getViewModelFactoryAnimal()).get(AnimalViewModel.class);
        animalViewModel.getAllAnimalOnAZ_or_ZA_WhenISFavorite_or_Not(true,true).observe(getViewLifecycleOwner(), new Observer<List<AnimalItemViewModel>>() {
            @Override
            public void onChanged(List<AnimalItemViewModel> animalItemViewModels) {
                animalListAdapter.setAnimals(animalItemViewModels);
            }
        });
    }

    @Override
    public void changeStatutOfFavorite(AnimalItemViewModel animalItem) {
        AnimalToAnimalItemViewModel animalToAnimalItemViewModel = new AnimalToAnimalItemViewModel();
        AnimalEntity animalEntity = animalToAnimalItemViewModel.reverse(animalItem);
        animalViewModel.changeFavoriteStatut(animalEntity);
    }

    @Override
    public void displayAnimal(AnimalItemViewModel animal) {
        Intent intent = new Intent(getContext(), AnimalInfoActivity.class);
        intent.putExtra("animal",animal);
        startActivity(intent);
    }
}
