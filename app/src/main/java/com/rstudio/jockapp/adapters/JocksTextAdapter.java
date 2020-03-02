package com.rstudio.jockapp.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rstudio.jockapp.R;
import com.rstudio.jockapp.models.Jock;

import java.util.ArrayList;


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

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_text, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {

        Jock product = jocks.get(position);
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        itemHolder.jockName.setText(product.getJockBody());

    }

    @Override
    public int getItemCount() {
        return jocks.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {


        TextView jockName;
        LinearLayout deleteJock;

        private final View rootView;


        public ItemViewHolder(View view) {
            super(view);
            rootView = view;
          /*  jockName = (TextView) rootView.findViewById(R.id.jockName);
            deleteJock = (LinearLayout) rootView.findViewById(R.id.deleteJock);*/
        }
    }
}
