package com.studentshub.studymateriala;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import com.studentshub.studymateriala.adapters.JocksTextAdapter;
import com.studentshub.studymateriala.models.Jock;

import java.util.ArrayList;

public class JockTextListActivity extends AppCompatActivity {


    private JocksTextAdapter adapter;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private static final String TAG = "JockTextListActivity";
    private ArrayList<Jock> jockArrayList;
    private String cat;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jock_text_list);

        initValues();

        setToolbar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setScrollListener();
        }

        loadData();

    }

    private void initValues() {
        jockArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.rView_textList);
        searchView = findViewById(R.id.searchView);
        cat = getIntent().getStringExtra("cat");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

    }

    private void filter(String text) {
        ArrayList<Jock> filter = new ArrayList<>();
        for (Jock jock : jockArrayList) {
            if (jock == null) {
                filter.add(null);
            } else if (jock.getJockTitle().toLowerCase().contains(text) || jock.getJockBody().toLowerCase().contains(text)) {
                filter.add(jock);
            }
        }

        adapter.setFilter(filter);

    }

    private void loadData() {
        final ProgressDialog pgDialog = new ProgressDialog(this);
        pgDialog.setMessage("Loading");
        pgDialog.show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Jock").child("text");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pgDialog.dismiss();
                if (dataSnapshot.exists()) {
                    int i = 0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Jock jock = snapshot.getValue(Jock.class);
                        if (jock.getType().toLowerCase().equalsIgnoreCase(cat)) {
                            if (i % 5 == 0) {
                                jockArrayList.add(null);
                            }
                            jockArrayList.add(jock);
                        }
                        i++;
                    }
                    if (jockArrayList.size() == 1 || jockArrayList.isEmpty()) {
                        Toast.makeText(JockTextListActivity.this, "No Texts", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter = new JocksTextAdapter(JockTextListActivity.this, jockArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(JockTextListActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pgDialog.dismiss();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setScrollListener() {
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: " + oldScrollY);
                if (oldScrollY < -40) {
                    toolbar.setVisibility(View.GONE);
                } else if (oldScrollY > 40) {
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
        tv.setText(cat + " Texts");
    }
}
