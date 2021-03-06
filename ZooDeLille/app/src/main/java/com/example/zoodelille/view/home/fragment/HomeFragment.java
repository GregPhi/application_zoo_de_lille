package com.example.zoodelille.view.home.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoodelille.R;
import com.example.zoodelille.data.di.DepencyInjector;
import com.example.zoodelille.view.info.InfoActivity;
import com.example.zoodelille.view.model.InfoViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {
    public static final String name = "Accueil";
    public static final int icon = R.drawable.drawable_home;

    private View m_view;
    private TextView welcome_to_zoo_date;
    private ConstraintLayout button_to_info;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        m_view = inflater.inflate(R.layout.fragment_home, container, false);
        return m_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        welcome_to_zoo_date = m_view.findViewById(R.id.welcome_to_zoo_date);
        button_to_info = m_view.findViewById(R.id.more_info);
        setWelcome_to_zoo_date();
        setButton_to_info();
    }

    @SuppressLint("SimpleDateFormat")
    public void setWelcome_to_zoo_date(){
        SimpleDateFormat formater;
        Date aujourdhui = new Date();
        formater = new SimpleDateFormat("'Le' dd MMMM yyyy 'à' HH:mm:ss");
        welcome_to_zoo_date.setText(formater.format(aujourdhui));
        InfoViewModel infoViewModel = new ViewModelProvider(this, DepencyInjector.getViewModelFactoryInfo()).get(InfoViewModel.class);
        infoViewModel.zooIsOpen().observe(getViewLifecycleOwner(),new Observer<Boolean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Boolean aBoolean) {
                TextView open_or_not = m_view.findViewById(R.id.open_or_not);
                if(aBoolean){
                    open_or_not.setText("Le zoo est actuellement ouvert.");
                    open_or_not.setBackgroundColor(getResources().getColor(R.color.LightGreen));
                }else{
                    open_or_not.setText("Le zoo est actuellement fermé.");
                    open_or_not.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
                }
            }
        });
    }

    public void setButton_to_info(){
        button_to_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_view.getContext(), InfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
