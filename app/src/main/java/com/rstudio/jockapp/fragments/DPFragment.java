package com.rstudio.jockapp.fragments;

import android.content.Context;
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
import com.rstudio.jockapp.ImageListActivity;
import com.rstudio.jockapp.R;

public class DPFragment extends Fragment {

    private LinearLayout layout;
    private MaterialCardView cardOne;

    DPFragment(){

    }

    DPFragment(Context context){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_dp, container, false);

        cardOne = layout.findViewById(R.id.card_oneDP);

        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ImageListActivity.class));
            }
        });

        return layout;
    }
}
