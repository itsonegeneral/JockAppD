package com.rstudio.jockapp.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.rstudio.jockapp.DataStore;
import com.rstudio.jockapp.ImagePagerActivity;
import com.rstudio.jockapp.R;
import com.rstudio.jockapp.models.JockImage;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Developed by Rithik S (tubeviral88@gmail.com) on 4/3/2020.
 */

public class JockImageAdapter extends RecyclerView.Adapter<JockImageAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<JockImage> jocks;
    private static final String TAG = "JockImageAdapter";


    public JockImageAdapter(Context context, ArrayList<JockImage> jocks) {

        mContext = context;
        this.jocks = jocks;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView jockImage;
        MaterialCardView cardView;
        ImageButton btnWhatsapp, btnTelegram, btnMore;


        public ItemViewHolder(View view) {
            super(view);
            jockImage = view.findViewById(R.id.imgView_jockList);
            btnTelegram = view.findViewById(R.id.imgBtn_Telegram);
            btnMore = view.findViewById(R.id.imgBtn_More);
            btnWhatsapp = view.findViewById(R.id.imgBtn_Whatsapp);
            cardView = view.findViewById(R.id.card_imageJockList);
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

        Log.d(TAG, "onBindViewHolder: " + product.getDownloadUrl());
        Picasso.get().load(product.getDownloadUrl()).into(holder.jockImage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStore.imageJock = jocks;
                Intent intent = new Intent(mContext, ImagePagerActivity.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });

        holder.btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            55);

                } else {
                    new LongOperation(jocks.get(position).getDownloadUrl(), "wats", "").execute();
                }


            }
        });
        holder.btnTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            55);

                } else {
                    new LongOperation(jocks.get(position).getDownloadUrl(), "download", jocks.get(position).getImageName()).execute();
                }
            }
        });

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            55);

                } else {
                    new LongOperation(jocks.get(position).getDownloadUrl(), "", "").execute();
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return jocks.size();
    }


    private class LongOperation extends AsyncTask<String, Void, String> {
        String url = "";
        Bitmap bm;
        ProgressDialog pd;
        String typeShare;
        String name;

        LongOperation(String url, String typeShare, String name) {
            this.url = url;
            this.typeShare = typeShare;
            this.name = name;
        }

        @Override
        protected String doInBackground(String... params) {

            bm = getBitmapFromURL(url);
            return "Executed";
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(String result) {
            pd.dismiss();
            if (bm != null)
                if (typeShare.equalsIgnoreCase("wats"))
                    onClickApp("com.whatsapp", bm);
                else if (typeShare.equalsIgnoreCase("download")) {
                    saveImageToInternalStorage(bm, name);
                } else {
                    onClickApp("", bm);
                }

        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(mContext);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


    public void onClickApp(String pack, Bitmap bitmap) {
        try {
            PackageManager pm = mContext.getPackageManager();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, "Title", null);
            Uri imageUri = Uri.parse(path);

            if (!pack.equalsIgnoreCase("")) {
                PackageInfo info = pm.getPackageInfo(pack, PackageManager.GET_META_DATA);
            }
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("image/*");
            if (!pack.equalsIgnoreCase("")) {
                waIntent.setPackage(pack);
            }
            waIntent.putExtra(android.content.Intent.EXTRA_STREAM, imageUri);
            waIntent.putExtra(Intent.EXTRA_TEXT, "https://goo.gl/fQhv1M");
            mContext.startActivity(Intent.createChooser(waIntent, "Share with"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public String saveImageToInternalStorage(Bitmap image, String name) {

        try {
            String url = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), image, name + ".png", null);
            try {
                NotificationManager notificationManager = (NotificationManager) mContext
                        .getSystemService(Context.NOTIFICATION_SERVICE);

                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                        m.invoke(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Uri uri = Uri.parse("file://" + mContext.getFilesDir().getAbsolutePath() + "/" + name + ".png");
                Uri uri = Uri.parse(url);
                //intent.setDataAndType(uri,"image/*");
                intent.setDataAndType(uri, "image/*");
                // }

                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                PendingIntent pIntent = PendingIntent.getActivity(mContext, (int) System.currentTimeMillis(), intent, 0);

                Notification notification = new Notification.Builder(mContext)
                        .setContentTitle(
                                mContext.getResources().getString(R.string.app_name))
                        .setContentText(name).setContentIntent(pIntent)
                        .build();

                // hide the notification after its selected
                notification.flags |= Notification.FLAG_AUTO_CANCEL;

                notificationManager.notify(1, notification);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return mContext.getFilesDir().getAbsolutePath() + "/" + "jockapp.png";

        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
            return "";
        }
    }


    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
