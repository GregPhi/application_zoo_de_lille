package com.example.zoodelille.view.animal.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DoYouKnowAnimalFragment extends Fragment {
    public final static String name = "Le saviez-vous ?";
    private View rootView;
    public static DoYouKnowAnimalFragment newInstance(){
        return new DoYouKnowAnimalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@javax.annotation.Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}
