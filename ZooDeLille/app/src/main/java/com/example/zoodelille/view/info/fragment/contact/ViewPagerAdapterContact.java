package com.example.zoodelille.view.info.fragment.contact;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterContact extends FragmentStateAdapter {
    private final String address;
    private final String number;
    private final String mail;

    public ViewPagerAdapterContact(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String address, String number, String mail) {
        super(fragmentManager, lifecycle);
        this.address = address;
        this.number = number;
        this.mail = mail;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            PhoneFragment.passNumber(number);
            return PhoneFragment.getInstance();
        }
        if(position == 2){
            MailFragment.passMail(mail);
            return MailFragment.getInstance();
        }
        AddressFragment.passAddress(address);
        return AddressFragment.getInstance();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
