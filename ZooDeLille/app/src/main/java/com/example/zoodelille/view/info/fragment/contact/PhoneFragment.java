package com.example.zoodelille.view.info.fragment.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zoodelille.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PhoneFragment extends Fragment {
    public final static String name = "Téléphone";
    public final static int icon = R.drawable.drawable_phone;
    private View rootView;
    private static String number;

    public static PhoneFragment getInstance() {
        return new PhoneFragment();
    }

    public static void passNumber(String n) {
        number = n;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_contact_phone, container, false);

        TextView contact_phone = rootView.findViewById(R.id.contact_phone);
        contact_phone.setText(number);

        return rootView;
    }
}
