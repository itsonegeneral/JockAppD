package com.studentshub.studymateriala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.studentshub.studymateriala.fragments.TextItemFragment;
import com.studentshub.studymateriala.models.Jock;

import java.util.ArrayList;

public class TextPagerActivity extends AppCompatActivity {


    private ArrayList<Jock> jockArrayList = new ArrayList<>();
    private ImageButton btnNext, btnPrev;
    private int position;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_pager);
        jockArrayList = DataStore.jocks;
        setToolbar();
        initValues();

        setListeners();


    }

    private void setListeners() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    btnPrev.setVisibility(View.GONE);
                } else if (position == jockArrayList.size() - 1) {
                    btnNext.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                    btnPrev.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
    }

    private void initValues() {
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager_text);
        viewPager.setAdapter(pagerAdapter);

        position = getIntent().getIntExtra("position", 0);
        viewPager.setCurrentItem(position);


        btnPrev = findViewById(R.id.imgBtn_prev);
        btnNext = findViewById(R.id.imgBtn_next);

        if (position == 0) {
            btnPrev.setVisibility(View.GONE);
        } else if (position == jockArrayList.size() - 1) {
            btnNext.setVisibility(View.GONE);
        }

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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.tb_activityTextPager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        TextView tv = findViewById(R.id.tv_toolbarHeading);
        tv.setText("View Text");
    }

}
