package com.rstudio.jockapp.adapters;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.rstudio.jockapp.R;
import com.rstudio.jockapp.models.Jock;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by munchado on 2/1/18.
 */

public class JocksTextAdapter extends RecyclerView.Adapter<JocksTextAdapter.ItemViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Jock> jocks;
    ProgressDialog pd;


    public JocksTextAdapter(Context context, ArrayList<Jock> jocks) {

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        this.jocks = jocks;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {


        TextView jockName,tvTitle;
        LinearLayout deleteJock;
        ImageButton btnWhatsapp, btnTelegram, btnMore,btnCopy;


        public ItemViewHolder(View view) {
            super(view);

            jockName = view.findViewById(R.id.textView);
            btnTelegram = view.findViewById(R.id.imgBtn_Telegram);
            btnMore = view.findViewById(R.id.imgBtn_More);
            btnWhatsapp = view.findViewById(R.id.imgBtn_Whatsapp);
            tvTitle = view.findViewById(R.id.tv_listTextTitle);
            btnCopy= view.findViewById(R.id.imgBtn_Copy);



        }
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_text, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {

        Jock product = jocks.get(position);

        holder.jockName.setText(product.getJockBody());
        if(product.getJockTitle() ==null){
            holder.tvTitle.setText("Title not available, this maybe post from old apk");
        }else{
            holder.tvTitle.setText(product.getJockTitle());
        }

        /*holder.btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext, PagerActivityForJocks.class);
                i.putExtra("Jock_list",jocks);
                i.putExtra("position",position);
                mContext.startActivity(i);
            }
        });*/
        holder.btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.putExtra(Intent.EXTRA_TEXT, jocks.get(position).getJockBody() + "\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + mContext.getPackageName());
                sendIntent.setType("text/plain");
                mContext.startActivity(sendIntent);

            }
        });
        holder.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("share-message", jocks.get(position).getJockBody() + "\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + mContext.getPackageName());
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
                share.setPackage("com.telegram");
                share.putExtra(Intent.EXTRA_TEXT, jocks.get(position).getJockBody() + "\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + mContext.getPackageName());
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


    public void setListeners() {
    }


    private void shareIt(String subject, String text, String header) {
        //sharing implementation here
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text + "\n" + "Hey check out Latest DP And Status at: https://play.google.com/store/apps/details?id=" + mContext.getPackageName());
        mContext.startActivity(Intent.createChooser(sharingIntent, header));
    }


}
