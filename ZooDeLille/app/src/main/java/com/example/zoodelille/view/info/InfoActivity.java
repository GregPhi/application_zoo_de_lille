package com.example.zoodelille.view.info;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zoodelille.R;
import com.example.zoodelille.data.di.DepencyInjector;
import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.entity.info.access.AccessEntity;
import com.example.zoodelille.data.entity.info.hours.HoursEntity;
import com.example.zoodelille.data.entity.info.prices.PricesEntity;
import com.example.zoodelille.view.info.fragment.access.AutoFragment;
import com.example.zoodelille.view.info.fragment.access.BusFragment;
import com.example.zoodelille.view.info.fragment.access.MetroFragment;
import com.example.zoodelille.view.info.fragment.access.VLilleFragment;
import com.example.zoodelille.view.info.fragment.access.ViewPagerAdapterAccess;
import com.example.zoodelille.view.info.fragment.contact.AddressFragment;
import com.example.zoodelille.view.info.fragment.contact.PhoneFragment;
import com.example.zoodelille.view.info.fragment.contact.SocialNetworkFragment;
import com.example.zoodelille.view.info.fragment.contact.ViewPagerAdapterContact;
import com.example.zoodelille.view.info.fragment.hours.SummerFragment;
import com.example.zoodelille.view.info.fragment.hours.ViewPagerAdapterHours;
import com.example.zoodelille.view.info.fragment.hours.WinterFragment;
import com.example.zoodelille.view.info.fragment.prices.PricesOnGroupFragment;
import com.example.zoodelille.view.info.fragment.prices.PricesOneDayFragment;
import com.example.zoodelille.view.info.fragment.prices.ViewPagerAdapterPrices;
import com.example.zoodelille.view.model.InfoViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class InfoActivity extends AppCompatActivity {
    private ConstraintLayout button_to_home;
    private InfoViewModel infoViewModel;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);
        this.resources = getResources();
        button_to_home = findViewById(R.id.button_to_home);
        setButton_to_home();
        setupViewModel();

        TextView open_or_not = findViewById(R.id.open_or_not);
        //boolean open = infoViewModel.zooIsOpen();
        boolean open = false;
        if(open){
            open_or_not.setText("OUVERT");
            open_or_not.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }else{
            open_or_not.setText("FERME");
            open_or_not.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
        }

        infoViewModel.getInfoEntityMutableLiveData().observeForever(new Observer<InfoEntity>() {
            @Override
            public void onChanged(InfoEntity infoEntity) {
                if(infoEntity.getInfo_id()!=-1){
                    setupTimetable(infoEntity);
                    pricesInfo(infoEntity);
                    setupViewPagerAccess(infoEntity);
                    setupViewPagerContact(infoEntity);
                }
            }
        });
    }

    public void setButton_to_home(){
        button_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setupViewModel(){
        if(infoViewModel == null){
            infoViewModel = new ViewModelProvider(this, DepencyInjector.getViewModelFactoryInfo()).get(InfoViewModel.class);
        }
    }

    @SuppressLint("StringFormatInvalid")
    public void setupTimetable(InfoEntity infoEntity){
        HoursEntity hoursEntity = infoEntity.getHoursEntity();
        TextView hour_exceptional_opening = findViewById(R.id.hour_exceptional_opening);
        hour_exceptional_opening.setText(String.format(this.resources.getString(R.string.exceptional_opening), hoursEntity.getExceptional_opening()));

        TextView hour_closure = findViewById(R.id.hour_closure);
        hour_closure.setText(String.format(this.resources.getString(R.string.closure), hoursEntity.getAnnual_closure_oldYear(),hoursEntity.getAnnual_closure_newYear()));
        setupViewPagerTimetable(hoursEntity);
    }

    public void setupViewPagerTimetable(HoursEntity hoursEntity){
        final String[] tabTitles = new String[]{SummerFragment.name, WinterFragment.name};
        final int[] tabIcons = new int[]{SummerFragment.icon, WinterFragment.icon};
        final ViewPager2 viewPager = findViewById(R.id.fragments_viewpager_timetable);
        final ViewPagerAdapterHours viewPagerAdapter = new ViewPagerAdapterHours(getSupportFragmentManager(), getLifecycle(), hoursEntity.getWinterEntity(),hoursEntity.getSummerEntity());
        viewPager.setAdapter(viewPagerAdapter);
        final TabLayout tabLayout = findViewById(R.id.frag_tab_timetable);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabTitles[position]);
                        tab.setIcon(tabIcons[position]);
                    }
                }).attach();
    }

    public void pricesInfo(InfoEntity infoEntity){
        PricesEntity pricesEntity = infoEntity.getPricesEntity();
        //final String[] tabTitles = new String[]{PricesOneDayFragment.name, PricesOneYearFragment.name, PricesOnGroupFragment.name};
        //final int[] tabIcons = new int[]{PricesOneDayFragment.icon, PricesOneYearFragment.icon, PricesOnGroupFragment.icon};
        final String[] tabTitles = new String[]{PricesOneDayFragment.name, PricesOnGroupFragment.name};
        final int[] tabIcons = new int[]{PricesOneDayFragment.icon, PricesOnGroupFragment.icon};
        final ViewPager2 viewPager = findViewById(R.id.fragments_viewpager_prices);
        final ViewPagerAdapterPrices viewPagerAdapter = new ViewPagerAdapterPrices(getSupportFragmentManager(), getLifecycle(), pricesEntity.getPrices_one_day(), pricesEntity.getPrices_one_year(), pricesEntity.getPrices_on_group());
        viewPager.setAdapter(viewPagerAdapter);
        final TabLayout tabLayout = findViewById(R.id.frag_tab_prices);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabTitles[position]);
                        tab.setIcon(tabIcons[position]);
                    }
                }).attach();
    }

    public void setupViewPagerAccess(InfoEntity infoEntity){
        AccessEntity accessEntity = infoEntity.getAccessEntity();
        final String[] tabTitles = new String[]{AutoFragment.name, BusFragment.name, MetroFragment.name, VLilleFragment.name};
        final int[] tabIcons = new int[]{AutoFragment.icon, BusFragment.icon, MetroFragment.icon, VLilleFragment.icon};
        final ViewPager2 viewPager = findViewById(R.id.fragments_viewpager_shifting);
        final ViewPagerAdapterAccess viewPagerAdapter = new ViewPagerAdapterAccess(getSupportFragmentManager(), getLifecycle(), accessEntity.getAccess_auto(), accessEntity.getAccess_bus(), accessEntity.getAccess_metro(), accessEntity.getAccess_vlille());
        viewPager.setAdapter(viewPagerAdapter);
        final TabLayout tabLayout = findViewById(R.id.frag_tab_shifting);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabTitles[position]);
                        tab.setIcon(tabIcons[position]);
                    }
                }).attach();
    }

    public void setupViewPagerContact(InfoEntity infoEntity){
        String address = infoEntity.getAddress()+"\n"+infoEntity.getZip_code()+" - "+infoEntity.getStreet();
        String mail = infoEntity.getMail();
        String number = infoEntity.getNumber();
        final String[] tabTitles = new String[]{AddressFragment.name, PhoneFragment.name, SocialNetworkFragment.name};
        final int[] tabIcons = new int[]{AddressFragment.icon, PhoneFragment.icon, SocialNetworkFragment.icon};
        final ViewPager2 viewPager = findViewById(R.id.fragments_viewpager_contact);
        final ViewPagerAdapterContact viewPagerAdapter = new ViewPagerAdapterContact(getSupportFragmentManager(), getLifecycle(), address, number, mail);
        viewPager.setAdapter(viewPagerAdapter);
        final TabLayout tabLayout = findViewById(R.id.frag_tab_contact);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabTitles[position]);
                        tab.setIcon(tabIcons[position]);
                    }
                }).attach();
    }
}
