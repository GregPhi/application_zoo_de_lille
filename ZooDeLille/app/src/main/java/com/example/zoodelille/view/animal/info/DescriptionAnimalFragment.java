package com.example.zoodelille.view.animal.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoodelille.R;
import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DescriptionAnimalFragment extends Fragment {
    public final static String name = "Description";
    private View m_view;
    private static AnimalItemViewModel animalItemViewModel = new AnimalItemViewModel();

    public static DescriptionAnimalFragment newInstance(){
        return new DescriptionAnimalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        m_view = inflater.inflate(R.layout.fragment_animal_info_description, container, false);

        TextView latin_name = m_view.findViewById(R.id.latin_name);
        latin_name.setText(animalItemViewModel.getLatin_name());

        TextView classification = m_view.findViewById(R.id.classification);
        classification.setText(animalItemViewModel.getClassification());

        TextView menaced = m_view.findViewById(R.id.menaced);
        menaced.setText(animalItemViewModel.getMenaced());

        TextView description = m_view.findViewById(R.id.description);
        description.setText(animalItemViewModel.getDescription());
        return m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static void passAnimal(AnimalItemViewModel animal){
        animalItemViewModel = animal;
    }
}
