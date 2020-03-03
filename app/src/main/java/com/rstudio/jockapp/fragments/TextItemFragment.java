package com.rstudio.jockapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rstudio.jockapp.R;
import com.rstudio.jockapp.models.Jock;

public class TextItemFragment extends Fragment {

    LinearLayout layout;
    TextView tvTitle, tvBody;
    Jock jock;

    public TextItemFragment() {

    }

    public TextItemFragment(Jock jock) {
        this.jock = jock;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_text_scroll_item, container, false);
        tvTitle = layout.findViewById(R.id.tv_listTextTitle);
        tvBody = layout.findViewById(R.id.textView);
        Toast.makeText(getContext(),  "ss", Toast.LENGTH_SHORT).show();

        if (jock.getJockTitle() != null) {
            tvTitle.setText(jock.getJockTitle());
        } else {
            tvTitle.setText("Jock title");
        }
        tvBody.setText(jock.getJockBody());

        return layout;
    }
}
