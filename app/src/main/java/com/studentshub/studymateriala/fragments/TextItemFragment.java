package com.studentshub.studymateriala.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.studentshub.studymateriala.ImageListActivity;
import com.studentshub.studymateriala.R;
import com.studentshub.studymateriala.models.Jock;

public class TextItemFragment extends Fragment {

    LinearLayout layout;
    TextView tvTitle, tvBody;
    Jock jock;
    Button btnViewMore;

    ImageButton btnWhatsapp, btnTelegram, btnMore, btnCopy;

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
        btnViewMore = layout.findViewById(R.id.btn_viewMore);

        btnWhatsapp = layout.findViewById(R.id.imgBtn_Whatsapp);
        btnTelegram = layout.findViewById(R.id.imgBtn_Telegram);
        btnCopy = layout.findViewById(R.id.imgBtn_Copy);
        btnMore = layout.findViewById(R.id.imgBtn_More);


        if (jock.getJockTitle() != null) {
            tvTitle.setText(jock.getJockTitle());
        } else {
            tvTitle.setText("Jock title");
        }
        tvBody.setText(jock.getJockBody());

        btnViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImageListActivity.class);
                intent.putExtra("cat",jock.getType());
                startActivity(intent);
            }
        });



        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.putExtra(Intent.EXTRA_TEXT, jock.getJockBody() + "\n\n\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("share-message", jock.getJockBody() + "\n\n\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + getActivity().getApplicationContext().getPackageName());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Copied to clipboard!", Toast.LENGTH_LONG).show();
            }
        });
        btnTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent share = new Intent(Intent.ACTION_SEND);
                //share.setType("image/*");
                share.setType("text/plain");
                share.setPackage("org.telegram.messenger");
                share.putExtra(Intent.EXTRA_TEXT, jock.getJockBody() + "\n\n\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
                share.setType("text/plain");
                try {
                    startActivity(share);
                } catch (Exception e) {



                }
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIt("JockApp", jock.getJockBody(), "JockApp");
            }
        });



        return layout;
    }




    private void shareIt(String subject, String text, String header) {
        //sharing implementation here
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text + "\n\n\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
        startActivity(Intent.createChooser(sharingIntent, header));
    }


}
