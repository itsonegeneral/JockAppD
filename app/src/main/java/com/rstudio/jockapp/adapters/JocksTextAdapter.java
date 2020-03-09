package com.rstudio.jockapp.adapters;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.card.MaterialCardView;
import com.rstudio.jockapp.DataStore;
import com.rstudio.jockapp.R;
import com.rstudio.jockapp.TextPagerActivity;
import com.rstudio.jockapp.models.Jock;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Rithik S
 * tubeviral88@gmail.com
 */

public class JocksTextAdapter extends RecyclerView.Adapter<JocksTextAdapter.ItemViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Jock> jocks;
    ProgressDialog pd;
    private static final String TAG = "JocksTextAdapter";
    private final int AD_TYPE = 4, CONTENT_TYPE = 13;


    public JocksTextAdapter(Context context, ArrayList<Jock> jocks) {

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        this.jocks = jocks;
    }

    public void setFilter(ArrayList<Jock> filter) {
        this.jocks = filter;
        notifyDataSetChanged();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {


        TextView jockName, tvTitle;
        MaterialCardView layout;
        ImageButton btnWhatsapp, btnTelegram, btnMore, btnCopy;


        public ItemViewHolder(View view) {
            super(view);

            jockName = view.findViewById(R.id.textView);
            btnTelegram = view.findViewById(R.id.imgBtn_Telegram);
            btnMore = view.findViewById(R.id.imgBtn_More);
            btnWhatsapp = view.findViewById(R.id.imgBtn_Whatsapp);
            tvTitle = view.findViewById(R.id.tv_listTextTitle);
            btnCopy = view.findViewById(R.id.imgBtn_Copy);
            layout = view.findViewById(R.id.clickParent);


        }
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        Log.d(TAG, "onCreateViewHolder: " + viewType);
        if (viewType == AD_TYPE) {
            view = new AdView(mContext);
            AdView adView = (AdView) view;
            float density = mContext.getResources().getDisplayMetrics().density;
            int height = Math.round(AdSize.BANNER.getHeight() * density);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, height);
            view.setLayoutParams(params);

            AdRequest adRequest = new AdRequest.Builder().build();
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
            adView.loadAd(adRequest);
            view = adView;
        } else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_text, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        if (jocks.get(position) == null) {
            return;
        }

        Jock product = jocks.get(position);

        holder.jockName.setText(product.getJockBody());
        if (product.getJockTitle() == null) {
            holder.tvTitle.setText("Title not available, this maybe post from old apk");
        } else {
            holder.tvTitle.setText(product.getJockTitle());
        }


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStore.jocks = jocks;
                Intent intent = new Intent(mContext, TextPagerActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });


        holder.btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.putExtra(Intent.EXTRA_TEXT, jocks.get(position).getJockBody() + "\n\n\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + mContext.getPackageName());
                sendIntent.setType("text/plain");
                mContext.startActivity(sendIntent);

            }
        });
        holder.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("share-message", jocks.get(position).getJockBody() + "\n\n\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + mContext.getPackageName());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext, "Copied to clipboard!", Toast.LENGTH_LONG).show();
            }
        });
        holder.btnTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent share = new Intent(Intent.ACTION_SEND);
                //share.setType("image/*");
                share.setType("text/plain");
                share.setPackage("org.telegram.messenger");
                share.putExtra(Intent.EXTRA_TEXT, jocks.get(position).getJockBody() + "\n\n\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + mContext.getPackageName());
                share.setType("text/plain");
                try {
                    mContext.startActivity(share);
                } catch (Exception e) {

                }
            }
        });

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIt("JockApp", jocks.get(position).getJockBody(), "JockApp");
            }
        });

    }

    @Override
    public int getItemCount() {
        return jocks.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (jocks.get(position) == null) {
            return AD_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }

    public void setListeners() {
    }


    private void shareIt(String subject, String text, String header) {
        //sharing implementation here
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text + "\n\n\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + mContext.getPackageName());
        mContext.startActivity(Intent.createChooser(sharingIntent, header));
    }


}
