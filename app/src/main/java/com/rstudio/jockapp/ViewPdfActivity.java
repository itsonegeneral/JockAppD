package com.rstudio.jockapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ViewPdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        setToolbar();

        String url;
        WebView webView = findViewById(R.id.webView);
        if(getIntent().getStringExtra("id").equals("two")) {
             url = "https://firebasestorage.googleapis.com/v0/b/jockappnew.appspot.com/o/pdf%2Fandroid_programming.pdf?alt=media&token=89a2721c-333d-48e3-a923-7c89dbf9c456";
        }else{
            url = "https://firebasestorage.googleapis.com/v0/b/jockappnew.appspot.com/o/pdf%2Fandroid_tutorial.pdf?alt=media&token=61e63c0d-4670-4e3f-b4e6-6b3e77ea4b4b";
        }
        webView.getSettings().setJavaScriptEnabled(true);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.tb_viewPdf);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = findViewById(R.id.tv_toolbarHeading);
        tv.setText("Android Tutorial");
    }
}
