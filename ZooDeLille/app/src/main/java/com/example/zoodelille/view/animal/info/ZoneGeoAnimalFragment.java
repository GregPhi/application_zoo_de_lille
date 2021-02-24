package com.example.zoodelille.view.animal.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.zoodelille.R;
import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ZoneGeoAnimalFragment extends Fragment {
    public final static String name = "Zone géographique";
    private View rootView;
    private static AnimalItemViewModel animalItemViewModel = new AnimalItemViewModel();

    public static ZoneGeoAnimalFragment newInstance(){
        return new ZoneGeoAnimalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_animal_info_zone_geo,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@javax.annotation.Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setupZoneGeo();
    }

    public void setupZoneGeo(){
        TextView situation_geo_picture_description = rootView.findViewById(R.id.situation_geo_picture_description);
        situation_geo_picture_description.setText(animalItemViewModel.getSituation_geo_picture_description());
        ImageView situation_geo_picture_url = rootView.findViewById(R.id.situation_geo_picture_url);
        Glide.with(this)
                .load(animalItemViewModel.getSituation_geo_picture_url())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.carte_monde)
                .into(situation_geo_picture_url);
    }

    public static void passAnimal(AnimalItemViewModel animal){
        animalItemViewModel = animal;
    }
}
