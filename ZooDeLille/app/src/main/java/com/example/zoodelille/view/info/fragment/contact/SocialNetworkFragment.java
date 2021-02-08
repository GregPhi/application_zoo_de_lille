package com.example.zoodelille.view.info.fragment.contact;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zoodelille.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SocialNetworkFragment extends Fragment {
    public final static String name = "Réseau";
    public final static int icon = R.drawable.drawable_mail;
    private View rootView;
    private static String mail;

    public static SocialNetworkFragment getInstance() {
        return new SocialNetworkFragment();
    }

    public static void passMail(String m) {
        mail = m;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_contact_network, container, false);

        Button contact_mail = rootView.findViewById(R.id.contact_mail);
        contact_mail.setText(mail);
        contact_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, mail);
                if (intent.resolveActivity(getActivity().getApplicationContext().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        Button facebook = rootView.findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                try {
                    getActivity().getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana",0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/913417535387849"));
                } catch (PackageManager.NameNotFoundException e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/913417535387849"));
                }
                startActivity(intent);
            }
        });

        return rootView;
    }
}