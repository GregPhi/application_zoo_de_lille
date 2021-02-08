package com.example.zoodelille.view.animal.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.zoodelille.R;
import com.example.zoodelille.view.animal.adapter.AnimalItemViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

public class AnimalInfoActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private final String[] tabTitles = new String[]{DescriptionAnimalFragment.name, ZoneGeoAnimalFragment.name, DoYouKnowAnimalFragment.name};
    private AnimalItemViewModel animal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        animal = intent.getParcelableExtra("animal");
        setContentView(R.layout.activity_animal_info);
        setupActionAnimal();
        setupAnimal();
        setupViewPager();
    }

    public void setupActionAnimal(){
        ConstraintLayout listen_info = findViewById(R.id.listen_info);
        listen_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Fiche audio",Toast.LENGTH_LONG).show();
            }
        });
        ConstraintLayout play_quiz = findViewById(R.id.play_quiz_in_animal);
        play_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Quiz",Toast.LENGTH_LONG).show();
            }
        });
        ConstraintLayout where_in_zoo = findViewById(R.id.where_in_zoo);
        where_in_zoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Map",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setupAnimal(){
        ImageButton animal_info_back = findViewById(R.id.animal_info_back);
        animal_info_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView animal_info_name = findViewById(R.id.animal_info_name);
        animal_info_name.setText(animal.getName());

        ImageView animal_info_picture = findViewById(R.id.animal_info_picture);
        Glide.with(this)
                .load(animal.getPicture())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(animal_info_picture);
    }

    public void setupViewPager() {
        viewPager = findViewById(R.id.animal_info_viewpager);
        final AnimalInfoViewPagerAdapter viewPagerAdapter = new AnimalInfoViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), animal);

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.animal_info_tab);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
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
                    }
                }).attach();
    }
}
