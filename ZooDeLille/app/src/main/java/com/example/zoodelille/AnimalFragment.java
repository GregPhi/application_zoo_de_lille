package com.example.zoodelille;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AnimalFragment extends Fragment {
    private static final String name = "Animal";
    private View m_view;
    private Toolbar toolbar;

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
        toolbar = (Toolbar) m_view.findViewById(R.id.toolbar_animals);
        toolbar.inflateMenu(R.menu.animals_filter);
        toolbar.setTitle(R.string.animals);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.animals_filter_alpha)
                {
                    // do something
                }
                else if(item.getItemId()== R.id.animals_filter_like)
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
}
