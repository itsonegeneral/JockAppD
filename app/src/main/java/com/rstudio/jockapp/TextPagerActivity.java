package com.rstudio.jockapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.rstudio.jockapp.fragments.TextItemFragment;
import com.rstudio.jockapp.models.Jock;

import java.util.ArrayList;

public class TextPagerActivity extends AppCompatActivity {


    ArrayList<Jock> jockArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_pager);
        jockArrayList = DataStore.jocks;


        PagerAdapter pagerAdapter;
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager_text);
        viewPager.setAdapter(pagerAdapter);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new TextItemFragment(jockArrayList.get(position));
        }

        @Override
        public int getCount() {
            return jockArrayList.size();
        }
    }


}
