package com.rstudio.jockapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.rstudio.jockapp.JockTextListActivity;
import com.rstudio.jockapp.R;

public class HomeFragment extends Fragment {

    private LinearLayout layout;
    private MaterialCardView cardOne;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_home,container,false);
        cardOne = layout.findViewById(R.id.card_oneHome);


        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), JockTextListActivity.class));
            }
        });

        return layout;
    }
}
