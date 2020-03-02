package com.rstudio.jockapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rstudio.jockapp.adapters.JocksTextAdapter;
import com.rstudio.jockapp.models.Jock;

import java.util.ArrayList;

public class JockTextListActivity extends AppCompatActivity {


    private JocksTextAdapter adapter;
    private RecyclerView recyclerView ;
    private Toolbar toolbar;
    private static final String TAG = "JockTextListActivity";
    private ArrayList<Jock> jockArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jock_text_list);

        jockArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.rView_textList);


        setToolbar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setScrollListener();
        }

        loadData();

    }

    private void loadData() {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Jock").child("text");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Jock jock = snapshot.getValue(Jock.class);
                        jockArrayList.add(jock);
                    }
                    adapter= new JocksTextAdapter(JockTextListActivity.this,jockArrayList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setScrollListener() {
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: " +oldScrollY);
                if(oldScrollY<-40){
                    toolbar.setVisibility(View.GONE);
                }else if(oldScrollY>40){
                    toolbar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.tb_activityjocktextlist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        TextView tv = findViewById(R.id.tv_toolbarHeading);
        tv.setText("Texts");
    }
}
