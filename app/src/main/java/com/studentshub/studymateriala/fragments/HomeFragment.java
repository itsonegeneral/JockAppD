package com.studentshub.studymateriala.fragments;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.studentshub.studymateriala.JockTextListActivity;
import com.studentshub.studymateriala.R;

public class HomeFragment extends Fragment {

    private LinearLayout layout;
    private MaterialCardView cardOne, two, three, four, five, six, seven, eight;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
        cardOne = layout.findViewById(R.id.card_oneHome);
        two = layout.findViewById(R.id.card_twoHome);

        three = layout.findViewById(R.id.card_threeHome);

        four = layout.findViewById(R.id.card_fourHome);
        five = layout.findViewById(R.id.card_fiveHome);
        six = layout.findViewById(R.id.card_sixHome);
        seven = layout.findViewById(R.id.card_sevenHome);
        eight = layout.findViewById(R.id.card_eightHome);


        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewActivity(getString(R.string.cat1));
            }
        });


        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewActivity(getString(R.string.cat2));
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewActivity(getString(R.string.cat3));
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewActivity(getString(R.string.cat4));
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewActivity(getString(R.string.cat5));
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewActivity(getString(R.string.cat6));
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewActivity(getString(R.string.cat7));
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewActivity(getString(R.string.cat8));
            }
        });


        return layout;
    }

    private void startNewActivity(String cat) {
        if (checkInternetConnection()) {

            Intent intent = new Intent(getContext(), JockTextListActivity.class);
            intent.putExtra("cat", cat);
            startActivity(intent);
        }else{
            Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}

