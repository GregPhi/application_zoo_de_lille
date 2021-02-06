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

public class MailFragment extends Fragment {
    public final static String name = "Mail";
    public final static int icon = R.drawable.drawable_mail;
    private View rootView;
    private static String mail;

    public static MailFragment getInstance() {
        return new MailFragment();
    }

    public static void passMail(String m) {
        mail = m;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_info_contact_mail, container, false);

        TextView contact_mail = rootView.findViewById(R.id.contact_mail);
        contact_mail.setText(mail);

        return rootView;
    }
}