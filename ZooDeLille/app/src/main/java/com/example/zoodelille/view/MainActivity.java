package com.example.zoodelille.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zoodelille.BuildConfig;
import com.example.zoodelille.R;
import com.example.zoodelille.data.di.DepencyInjector;
import com.example.zoodelille.view.animal.fragment.AnimalFragment;
import com.example.zoodelille.view.home.fragment.HomeFragment;
import com.example.zoodelille.view.map.fragment.MapFragment;
import com.example.zoodelille.view.model.Event;
import com.example.zoodelille.view.model.ZooViewModel;
import com.example.zoodelille.view.qrcode.fragment.QRCodeFragment;
import com.example.zoodelille.view.quiz.fragment.QuizFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    private ZooViewModel zooViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permissions nécessaires");
                alertBuilder.setMessage("Merci d'accepter les différentes permissions afin de profiter au mieux de l'application.");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.INTERNET,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA},4);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA},4);
            }
        }

        setupZooVersion();

        if (BuildConfig.MAPS_API_KEY.isEmpty()) {
            Toast.makeText(this, "Add your own API key in local.properties as MAPS_API_KEY=YOUR_API_KEY", Toast.LENGTH_LONG).show();
        }
    }

    public void setupZooVersion(){
        zooViewModel = new ViewModelProvider(this, DepencyInjector.getViewModelFactoryZoo()).get(ZooViewModel.class);
        if(isConnected()){
            setContentView(R.layout.waiting_screen);
            //display layout in layout with progress bar -> mettre grande photo animal + progress bar + text view (text immersif -> marrant)
            zooViewModel.checkVersion();
            zooViewModel.getCheckVersionEvent().observeForever(new Observer<Event<String>>() {
                @Override
                public void onChanged(Event<String> stringEvent) {
                    setContentView(R.layout.activity_main);
                    setupViewPager();
                    //Do nothing
                }
            });
        }else{
            setContentView(R.layout.activity_main);
            setupViewPager();
        }
    }

    public void setupViewPager(){
        //final String[] tabTitles = new String[]{HomeFragment.name, RouteFragment.name, AnimalFragment.name, MapFragment.name, QuizFragment.name, QRCodeFragment.name};
        //final int[] tabIcons = new int[]{HomeFragment.icon, RouteFragment.icon, AnimalFragment.icon, MapFragment.icon, QuizFragment.icon, QRCodeFragment.icon};
        //final String[] tabTitles = new String[]{HomeFragment.name, AnimalFragment.name, MapFragment.name, QuizFragment.name, QRCodeFragment.name};
        //final int[] tabIcons = new int[]{HomeFragment.icon, AnimalFragment.icon, MapFragment.icon, QuizFragment.icon, QRCodeFragment.icon};
        final String[] tabTitles = new String[]{HomeFragment.name, AnimalFragment.name, MapFragment.name, QuizFragment.name};
        final int[] tabIcons = new int[]{HomeFragment.icon, AnimalFragment.icon, MapFragment.icon, QuizFragment.icon};
        final ViewPager2 viewPager = findViewById(R.id.fragments_viewpager);
        final ViewPagerAdapterApplication viewPagerAdapter = new ViewPagerAdapterApplication(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(viewPagerAdapter);
        final TabLayout tabLayout = findViewById(R.id.frag_tab);
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
        viewPager.setUserInputEnabled(false);
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}