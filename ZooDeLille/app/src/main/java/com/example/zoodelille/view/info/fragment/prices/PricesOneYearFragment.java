package com.example.zoodelille.view.info.fragment.prices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoodelille.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PricesOneYearFragment extends Fragment {
    public final static String name = "Pass un an";
    public final static int icon = R.drawable.drawable_phone;
    private View rootView;
    private static String prices;

    public static PricesOneYearFragment getInstance() {
        return new PricesOneYearFragment();
    }

    public static void passPrices(String p) {
        prices = p;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_prices_one_year, container, false);

        TextView prices_one_year = rootView.findViewById(R.id.prices_one_year);
        prices_one_year.setText(prices);

        return rootView;
    }
}
