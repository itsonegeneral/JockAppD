package com.rstudio.jockapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.rstudio.jockapp.adapters.ViewPagerAdapter;
import com.rstudio.jockapp.fragments.HomeFragment;

/**
 * Developed by Rithik S (tubeviral88@gmail.com)
 * GitHub /itsonegeneral
 */
public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);

        TabLayout tabLayout = findViewById(R.id.tabLayout_Home);
        ViewPager viewPager = findViewById(R.id.viewPager_home);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new HomeFragment(), "Home");
        adapter.addFrag(new HomeFragment(), "DP");
        adapter.addFrag(new HomeFragment(), "PDF");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
