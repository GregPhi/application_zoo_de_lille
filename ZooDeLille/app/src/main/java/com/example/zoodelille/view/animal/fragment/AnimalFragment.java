package com.example.zoodelille.view.animal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toolbar;

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
    private static final String name = "Animal";
    private View m_view;
    private Toolbar toolbar;
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
        initRecyclerView();
        setupSwitch();
        /*toolbar = m_view.findViewById(R.id.toolbar_animals);
        toolbar.inflateMenu(R.menu.animals_filter);
        toolbar.setTitle(R.string.animals);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.animals_filter_alpha)
                {
                    setupRecyclerViewWithAlphaFilter();
                }
                else if(item.getItemId()== R.id.animals_filter_like)
                {
                    setupRecyclerViewWithLikeFilter();
                }
                else{
                    // do something
                }
                return false;
            }
        });*/
    }

    public void setupSwitch(){
        final Switch switch_az = m_view.findViewById(R.id.switch_az);
        final Switch switch_fav = m_view.findViewById(R.id.switch_fav);
        switch_az.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch_fav.setChecked(false);
                    setupRecyclerViewWithAlphaFilter();
                }else{
                    if(!switch_fav.isChecked()){
                        initRecyclerView();
                    }
                }
            }
        });
        switch_fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch_az.setChecked(false);
                    setupRecyclerViewWithLikeFilter();
                }else{
                    if(!switch_az.isChecked()){
                        initRecyclerView();
                    }
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

    public void initRecyclerView(){
        animalViewModel = new ViewModelProvider(requireActivity(), DepencyInjector.getViewModelFactoryAnimal()).get(AnimalViewModel.class);
        animalViewModel.getAllAnimal().observe(getViewLifecycleOwner(), new Observer<List<AnimalItemViewModel>>() {
            @Override
            public void onChanged(List<AnimalItemViewModel> animalItemViewModels) {
                animalListAdapter.setAnimals(animalItemViewModels);
            }
        });
    }

    public void setupRecyclerViewWithAlphaFilter(){
        animalViewModel = new ViewModelProvider(requireActivity(), DepencyInjector.getViewModelFactoryAnimal()).get(AnimalViewModel.class);
        animalViewModel.getAllAnimalOnAZ_or_ZA(true).observe(getViewLifecycleOwner(), new Observer<List<AnimalItemViewModel>>() {
            @Override
            public void onChanged(List<AnimalItemViewModel> animalItemViewModels) {
                animalListAdapter.setAnimals(animalItemViewModels);
            }
        });
    }

    public void setupRecyclerViewWithLikeFilter(){
        animalViewModel = new ViewModelProvider(requireActivity(), DepencyInjector.getViewModelFactoryAnimal()).get(AnimalViewModel.class);
        animalViewModel.getAllAnimalIsFavorite_or_Not(true).observe(getViewLifecycleOwner(), new Observer<List<AnimalItemViewModel>>() {
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
