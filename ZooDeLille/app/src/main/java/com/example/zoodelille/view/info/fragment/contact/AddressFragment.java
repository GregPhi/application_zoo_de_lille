package com.example.zoodelille.view.info.fragment.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zoodelille.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddressFragment extends Fragment {
    public final static String name = "Adresse";
    public final static int icon = R.drawable.drawable_address;
    private View rootView;
    private static String address;

    public static AddressFragment getInstance() {
        return new AddressFragment();
    }

    public static void passAddress(String a) {
        address = a;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_contact_address, container, false);

        TextView contact_address = rootView.findViewById(R.id.contact_address);
        contact_address.setText(address);

        Button start_route = rootView.findViewById(R.id.start_route);
        start_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=50.638200,3.047915 (Zoo de Lille)")));
            }
        });
        return rootView;
    }
}
