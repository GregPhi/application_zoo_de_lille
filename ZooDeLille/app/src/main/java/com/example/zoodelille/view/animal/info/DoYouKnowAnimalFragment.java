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

public class DoYouKnowAnimalFragment extends Fragment {
    public final static String name = "Le saviez-vous ?";
    private View m_view;
    private static AnimalItemViewModel animalItemViewModel = new AnimalItemViewModel();

    public static DoYouKnowAnimalFragment newInstance(){
        return new DoYouKnowAnimalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        m_view = inflater.inflate(R.layout.fragment_animal_info_doyouknow, container, false);

        TextView do_you_know = m_view.findViewById(R.id.do_you_know);
        do_you_know.setText(animalItemViewModel.getDo_you_know());

        return m_view;
    }

    @Override
    public void onActivityCreated(@javax.annotation.Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public static void passAnimal(AnimalItemViewModel animal){
        animalItemViewModel = animal;
    }
}
