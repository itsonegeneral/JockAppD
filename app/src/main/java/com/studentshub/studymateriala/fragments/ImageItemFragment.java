package com.studentshub.studymateriala.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.studentshub.studymateriala.R;
import com.studentshub.studymateriala.models.JockImage;
import com.squareup.picasso.Picasso;

public class ImageItemFragment extends Fragment {

    LinearLayout layout;
    ImageView imageView;

    JockImage jock;

    public ImageItemFragment() {

    }

    public ImageItemFragment(JockImage jock) {
        this.jock = jock;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_image_scroll_item, container, false);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        imageView = layout.findViewById(R.id.imgView_jockPager);

        Picasso.get().load(jock.getDownloadUrl()).into(imageView);

        return layout;
    }
}
