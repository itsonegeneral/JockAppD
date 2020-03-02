package com.rstudio.jockapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    private RecyclerView recyclerView;
    private ArrayList<JockImage> jockImageArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        recyclerView = findViewById(R.id.rView_imageList);
        jockImageArrayList = new ArrayList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Jock").child("image");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        JockImage jockImage = dataSnapshot.getValue(JockImage.class);
                        jockImageArrayList.add(jockImage);
                    }

                    imageAdapter = new JockImageAdapter(getApplicationContext(),jockImageArrayList);
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
}
