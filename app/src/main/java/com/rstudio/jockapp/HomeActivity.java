package com.rstudio.jockapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.rstudio.jockapp.adapters.ViewPagerAdapter;
import com.rstudio.jockapp.fragments.DPFragment;
import com.rstudio.jockapp.fragments.HomeFragment;
import com.rstudio.jockapp.fragments.PDFFragment;

/**
 * Developed by Rithik S (tubeviral88@gmail.com)
 * GitHub /itsonegeneral
 */
public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    DrawerLayout drawerLayout;
    String selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);


        TabLayout tabLayout = findViewById(R.id.tabLayout_Home);
        ViewPager viewPager = findViewById(R.id.viewPager_home);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFrag(new HomeFragment(), "Home");
        adapter.addFrag(new DPFragment(), "DP");
        adapter.addFrag(new PDFFragment(), "PDF");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        setToolbar("Section 1");

    }

    private void updateUI(String title) {
        TextView tv = findViewById(R.id.tv_toolbarHeading);
        drawerLayout.closeDrawer(GravityCompat.START);
        tv.setText(title);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_aboutUs: {

                break;
            }
            case R.id.menu_moreApps: {
                break;
            }
            case R.id.menu_rateUs: {
                break;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

        return false;
    }

    private void setToolbar(String title) {

        Toolbar toolbar = findViewById(R.id.tb_activityHome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        TextView tv = findViewById(R.id.tv_toolbarHeading);
        tv.setText("Home");


    }
}



