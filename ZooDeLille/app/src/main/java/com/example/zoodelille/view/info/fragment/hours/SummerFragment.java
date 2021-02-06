package com.example.zoodelille.view.info.fragment.hours;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoodelille.R;
import com.example.zoodelille.data.entity.info.hours.SummerEntity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SummerFragment extends Fragment {
    public final static String name = "Heure d'Eté";
    public final static int icon = R.drawable.drawable_summer;
    private View rootView;
    private static SummerEntity summerEntity;

    public static SummerFragment getInstance() {
        return new SummerFragment();
    }

    public static void passSummer(SummerEntity summer) {
        summerEntity = summer;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_hour_summer, container, false);

        Resources resources = rootView.getResources();

        TextView hour_summer_week = rootView.findViewById(R.id.hour_summer_week);
        hour_summer_week.setText(String.format(resources.getString(R.string.hour_week_summer), summerEntity.getOpen_hour_week_summer(), summerEntity.getClose_hour_week_summer()));

        TextView hour_summer_weekend = rootView.findViewById(R.id.hour_summer_weekend);
        hour_summer_weekend.setText(String.format(resources.getString(R.string.hour_weekend), summerEntity.getOpen_hour_week_summer(), summerEntity.getClose_hour_weekend_summer()));

        TextView hour_summer_close_day = rootView.findViewById(R.id.hour_summer_close_day);
        hour_summer_close_day.setText(String.format(resources.getString(R.string.hour_close_day), summerEntity.getClose_day_summer()));

        return rootView;
    }
}
