package com.studentshub.studymateriala.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.studentshub.studymateriala.R;
import com.studentshub.studymateriala.ViewPdfActivity;

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


