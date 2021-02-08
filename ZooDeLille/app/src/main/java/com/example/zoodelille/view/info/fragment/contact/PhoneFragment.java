package com.example.zoodelille.view.info.fragment.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.zoodelille.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.Manifest.permission.CALL_PHONE;

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

        Button contact_phone = rootView.findViewById(R.id.contact_phone);
        contact_phone.setText(number);
        contact_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                if (requireActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Integer.parseInt(CALL_PHONE)) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "PERMISSION TELEPHONE", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
