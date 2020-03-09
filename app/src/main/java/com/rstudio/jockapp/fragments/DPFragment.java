package com.rstudio.jockapp.fragments;

import android.content.Context;
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
import com.rstudio.jockapp.ImageListActivity;
import com.rstudio.jockapp.JockTextListActivity;
import com.rstudio.jockapp.R;

public class DPFragment extends Fragment {

    private LinearLayout layout;
    private MaterialCardView cardOne, two, three, four, five, six, seven, eight;

    public DPFragment() {

    }

    public DPFragment(Context context) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_dp, container, false);

        cardOne = layout.findViewById(R.id.card_oneDP);
        two = layout.findViewById(R.id.card_twoDP);

        three = layout.findViewById(R.id.card_threeDP);

        four = layout.findViewById(R.id.card_fourDP);
        five = layout.findViewById(R.id.card_fiveDP);
        six = layout.findViewById(R.id.card_sixDP);
        seven = layout.findViewById(R.id.card_sevenDP);
        eight = layout.findViewById(R.id.card_eightDP);


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


        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(getString(R.string.cat1));
            }
        });

        return layout;
    }

    private void startNewActivity(String cat) {
        if(checkInternetConnection()){

            Intent intent = new Intent(getContext(), ImageListActivity.class);
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
