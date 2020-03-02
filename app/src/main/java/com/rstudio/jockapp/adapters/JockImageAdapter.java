package com.rstudio.jockapp.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.rstudio.jockapp.R;
import com.rstudio.jockapp.models.JockImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Developed by Rithik S (tubeviral88@gmail.com) on 4/3/2020.
 */

public class JockImageAdapter extends RecyclerView.Adapter<JockImageAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<JockImage> jocks;


    public JockImageAdapter(Context context, ArrayList<JockImage> jocks) {

        mContext = context;
        this.jocks = jocks;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView jockImage;
        ImageButton btnWhatsapp, btnTelegram, btnMore;


        public ItemViewHolder(View view) {
            super(view);
            jockImage = view.findViewById(R.id.imgView_jockList);
            btnTelegram = view.findViewById(R.id.imgBtn_Telegram);
            btnMore = view.findViewById(R.id.imgBtn_More);
            btnWhatsapp = view.findViewById(R.id.imgBtn_Whatsapp);

        }
    }

    @Override
    public JockImageAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_jock_image, parent, false);
        return new JockImageAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JockImageAdapter.ItemViewHolder holder, final int position) {

        JockImage product = jocks.get(position);
        Picasso.get().load(product.getDownloadUrl()).into(holder.jockImage);

    }


    @Override
    public int getItemCount() {
        return jocks.size();
    }


}
