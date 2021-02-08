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

public class VLilleFragment extends Fragment {
    public final static String name = "VLille";
    public final static int icon = R.drawable.drawable_bike;
    private View rootView;
    private static String vLille;

    public static VLilleFragment getInstance() {
        return new VLilleFragment();
    }

    public static void passVLille(String v) {
        vLille = v;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_access_vlille, container, false);

        TextView access_vlille = rootView.findViewById(R.id.access_vlille);
        access_vlille.setText(vLille);

        return rootView;
    }
}
