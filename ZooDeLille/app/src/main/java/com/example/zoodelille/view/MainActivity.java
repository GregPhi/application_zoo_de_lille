package com.example.zoodelille.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.example.zoodelille.view.route.fragment.RouteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView m_BottomNav;
    public static final List<Fragment> m_listFragment = new ArrayList<Fragment>() {{
        add(HomeFragment.newInstance());
        add(RouteFragment.newInstance());
        add(AnimalFragment.newInstance());
        add(MapFragment.newInstance());
        add(QuizFragment.newInstance());
        add(QRCodeFragment.newInstance());
    }};
    private static final int positionHomeFragment = 0;
    private static final int positionRouteFragment = 1;
    private static final int positionAnimalFragment = 2;
    private static final int positionMapFragment = 3;
    private static final int positionQuizFragment = 4;
    private static final int positionQRCodeFragment = 5;
    public int m_currentFragment = positionHomeFragment;
    private ZooViewModel zooViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_BottomNav = findViewById(R.id.bottom_navigation);
        m_BottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        m_currentFragment = positionHomeFragment;
                        break;
                    case R.id.nav_routes:
                        m_currentFragment = positionRouteFragment;
                        break;
                    case R.id.nav_animals:
                        m_currentFragment = positionAnimalFragment;
                        break;
                    case R.id.nav_map:
                        m_currentFragment = positionMapFragment;
                        break;
                    case R.id.nav_quizzes:
                        m_currentFragment = positionQuizFragment;
                        break;
                    /*case R.id.nav_qrcode:
                        m_currentFragment = positionQRCodeFragment;
                        break;*/
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, m_listFragment.get(m_currentFragment)).commit();
                return true;
            }
        });

        if(savedInstanceState != null) {
            m_currentFragment = savedInstanceState.getInt("currentPositionFragment");
        }
        else {
            m_currentFragment = positionHomeFragment;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                m_listFragment.get(m_currentFragment)).commit();

        setupZooVersion();

        if (BuildConfig.MAPS_API_KEY.isEmpty()) {
            Toast.makeText(this, "Add your own API key in local.properties as MAPS_API_KEY=YOUR_API_KEY", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("currentPositionFragment", m_currentFragment);
    }

    public void setupZooVersion(){
        if(zooViewModel == null){
            zooViewModel = new ViewModelProvider(this, DepencyInjector.getViewModelFactoryZoo()).get(ZooViewModel.class);
            if(isConnected()){
                zooViewModel.checkVersion();
                zooViewModel.getCheckVersionEvent().observeForever(new Observer<Event<String>>() {
                    @Override
                    public void onChanged(Event<String> stringEvent) {
                        //Do nothing
                    }
                });
            }
        }
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