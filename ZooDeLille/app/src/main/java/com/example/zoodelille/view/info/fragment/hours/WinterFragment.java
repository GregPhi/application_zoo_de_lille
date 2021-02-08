package com.example.zoodelille.view.info.fragment.hours;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoodelille.R;
import com.example.zoodelille.data.entity.info.hours.WinterEntity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WinterFragment extends Fragment {
    public final static String name = "Heure d'Hiver";
    public final static int icon = R.drawable.drawable_winter;
    private View rootView;
    private static WinterEntity winterEntity;

    public static WinterFragment getInstance() {
        return new WinterFragment();
    }

    public static void passWinter(WinterEntity winter) {
        winterEntity = winter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_hour_winter, container, false);

        Resources resources = rootView.getResources();

        TextView hour_winter_week = rootView.findViewById(R.id.hour_winter_week);
        hour_winter_week.setText(String.format(resources.getString(R.string.hour_week_summer), winterEntity.getOpen_hour_week_winter(), winterEntity.getClose_hour_week_winter()));

        TextView hour_winter_close_day = rootView.findViewById(R.id.hour_winter_close_day);
        hour_winter_close_day.setText(String.format(resources.getString(R.string.hour_close_day), winterEntity.getClose_day_winter()));

        return rootView;
    }
}
