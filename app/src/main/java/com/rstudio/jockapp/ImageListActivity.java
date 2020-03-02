package com.rstudio.jockapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rstudio.jockapp.adapters.JockImageAdapter;
import com.rstudio.jockapp.models.JockImage;

import java.security.cert.CertificateRevokedException;
import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {

    private JockImageAdapter imageAdapter;
    private static final String TAG = "ImageListActivity";
    private RecyclerView recyclerView;
    private ArrayList<JockImage> jockImageArrayList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        recyclerView = findViewById(R.id.rView_imageList);
        jockImageArrayList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setScrollListener();
        }

        setToolbar();

        loadData();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setScrollListener() {
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: " +oldScrollY);
                if(oldScrollY<-20){
                    toolbar.setVisibility(View.GONE);
                }else if(oldScrollY>30){
                    toolbar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void loadData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Jock").child("image");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        JockImage jockImage = snapshot.getValue(JockImage.class);
                        jockImageArrayList.add(jockImage);
                    }

                    imageAdapter = new JockImageAdapter(ImageListActivity.this,jockImageArrayList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(imageAdapter);

                }else{
                    //Empty
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.tb_activityImageList);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv= findViewById(R.id.tv_toolbarHeading);
        tv.setText("DP");
    }
}
