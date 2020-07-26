package com.example.project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.model.Dishes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListProductAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private List<Dishes> dishList;

    public ListProductAdapter(Context context, int layout, List<Dishes> dishList) {
        this.context = context;
        this.layout = layout;
        this.dishList = dishList;
    }

    @Override
    public int getCount() {
        return dishList.size();
    }

    @Override
    public Object getItem(int position) {
        return dishList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageProduct;
        TextView txtProductName;
        TextView txtDetail;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_display, parent, false);

        }
        imageProduct = convertView.findViewById(R.id.image_display);
        txtProductName = convertView.findViewById(R.id.title1);
        txtDetail = convertView.findViewById(R.id.title2);
        Bitmap bm = null;
        try {
            URL url = new URL(dishList.get(position).getUrl());
            bm = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch(Exception e) {

        }
        Glide.with(convertView).load(dishList.get(position).getUrl()).into(imageProduct);
        txtProductName.setText(dishList.get(position).getName());
//        txtDetail.setText(dishList.get(position).getName());
        return convertView;
    }
}
