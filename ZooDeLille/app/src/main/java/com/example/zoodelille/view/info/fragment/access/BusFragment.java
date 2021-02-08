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

public class BusFragment extends Fragment {
    public final static String name = "Bus";
    public final static int icon = R.drawable.drawable_bus;
    private View rootView;
    private static String bus;

    public static BusFragment getInstance() {
        return new BusFragment();
    }

    public static void passBus(String b) {
        bus = b;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_access_bus, container, false);

        TextView access_bus = rootView.findViewById(R.id.access_bus);
        access_bus.setText(bus);

        return rootView;
    }
}
