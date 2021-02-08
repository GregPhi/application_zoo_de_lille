package com.example.zoodelille.view.info.fragment.access;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoodelille.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MetroFragment extends Fragment {
    public final static String name = "Métro";
    public final static int icon = R.drawable.drawable_metro;
    private View rootView;
    private static String metro;

    public static MetroFragment getInstance() {
        return new MetroFragment();
    }

    public static void passMetro(String m) {
        metro = m;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_access_metro, container, false);

        TextView access_metro = rootView.findViewById(R.id.access_metro);
        access_metro.setText(metro);

        return rootView;
    }
}
