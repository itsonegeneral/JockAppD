package com.rstudio.jockapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.rstudio.jockapp.BuildConfig;
import com.rstudio.jockapp.R;
import com.rstudio.jockapp.ViewPdfActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;

public class PDFFragment extends Fragment {

    private LinearLayout layout;
    private MaterialCardView cardOne,two;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_pdf, container, false);
        cardOne = layout.findViewById(R.id.card_pdfOne);

        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),ViewPdfActivity.class);
                intent.putExtra("id","one");
                startActivity(intent);
            }
        });

        two = layout.findViewById(R.id.card_pdfTwo);

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*openPDF("android_programming.pdf");*/
                Intent intent = new Intent(getContext(),ViewPdfActivity.class);
                intent.putExtra("id","one");
                startActivity(intent);
            }
        });
        return layout;
    }


}


