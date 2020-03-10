package com.studentshub.studymateriala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.studentshub.studymateriala.adapters.ViewPagerAdapter;
import com.studentshub.studymateriala.fragments.DPFragment;
import com.studentshub.studymateriala.fragments.HomeFragment;
import com.studentshub.studymateriala.fragments.PDFFragment;

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
     //   MobileAds.initialize(this,"ca-app-pub-9352960187047894~3597130661");

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
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id="+getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            }
            case R.id.menu_moreApps: {
                Uri uri = Uri.parse("market://dev?id=" + "PSIT Services");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/developer?id=PSIT%20Services&hl=en")));
                }
                break;
            }
            case R.id.menu_rateUs: {

                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
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



