package com.example.zoodelille.view.home.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zoodelille.R;
import com.example.zoodelille.view.info.fragment.InfoActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private static final String name = "Home";
    private View m_view;
    private TextView welcome_to_zoo_date;
    private Button button_to_info;

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
        button_to_info = m_view.findViewById(R.id.button_to_info);
        setWelcome_to_zoo_date();
        setButton_to_info();
    }

    @SuppressLint("SimpleDateFormat")
    public void setWelcome_to_zoo_date(){
        SimpleDateFormat formater = null;
        Date aujourdhui = new Date();
        formater = new SimpleDateFormat("'le' dd MMMM yyyy 'à' hh:mm:ss");
        welcome_to_zoo_date.setText(formater.format(aujourdhui));
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
