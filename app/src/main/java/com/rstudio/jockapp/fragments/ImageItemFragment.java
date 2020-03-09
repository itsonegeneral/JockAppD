package com.rstudio.jockapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rstudio.jockapp.R;
import com.rstudio.jockapp.models.Jock;
import com.rstudio.jockapp.models.JockImage;
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
        imageView = layout.findViewById(R.id.imgView_jockPager);

        Picasso.get().load(jock.getDownloadUrl()).into(imageView);

        return layout;
    }
}
