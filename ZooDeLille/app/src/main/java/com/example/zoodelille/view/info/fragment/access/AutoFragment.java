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

public class AutoFragment extends Fragment {
    public final static String name = "Voiture";
    public final static int icon = R.drawable.drawable_auto;
    private View rootView;
    private static String auto;

    public static AutoFragment getInstance() {
        return new AutoFragment();
    }

    public static void passAuto(String a) {
        auto = a;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_access_auto, container, false);

        TextView access_auto = rootView.findViewById(R.id.access_auto);
        access_auto.setText(auto);

        return rootView;
    }
}
