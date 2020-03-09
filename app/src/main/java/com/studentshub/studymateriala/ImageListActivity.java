package com.studentshub.studymateriala;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.studentshub.studymateriala.adapters.JockImageAdapter;
import com.studentshub.studymateriala.models.JockImage;

import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {

    private JockImageAdapter imageAdapter;
    private static final String TAG = "ImageListActivity";
    private RecyclerView recyclerView;
    private ArrayList<JockImage> jockImageArrayList;
    private Toolbar toolbar;
    private String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        recyclerView = findViewById(R.id.rView_imageList);
        jockImageArrayList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setScrollListener();
        }

        if (getIntent().hasExtra("cat")) {
            cat = getIntent().getStringExtra("cat");
        } else {

        }

        setToolbar();

        loadData();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setScrollListener() {
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: " + oldScrollY);
                if (oldScrollY < -20) {
                    toolbar.setVisibility(View.GONE);
                } else if (oldScrollY > 30) {
                    toolbar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void loadData() {
        final ProgressDialog pgDialog = new ProgressDialog(this);
        pgDialog.setMessage("Please Wait");
        pgDialog.show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Jock").child("image");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pgDialog.dismiss();
                if (dataSnapshot.exists()) {
                    int i = 0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        JockImage jockImage = snapshot.getValue(JockImage.class);
                        if (jockImage.getType().equalsIgnoreCase(cat)) {
                            if (i % 6 == 0) {
                                jockImageArrayList.add(null);
                            }
                            jockImageArrayList.add(jockImage);
                        }
                        i++;
                    }

                    if (jockImageArrayList.isEmpty()) {
                        Toast.makeText(ImageListActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    } else {
                        imageAdapter = new JockImageAdapter(ImageListActivity.this, jockImageArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(imageAdapter);
                    }
                } else {
                    //Empty
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pgDialog.dismiss();
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
        TextView tv = findViewById(R.id.tv_toolbarHeading);
        tv.setText(cat + " DP");
    }
}
