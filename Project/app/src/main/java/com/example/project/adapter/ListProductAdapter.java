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
import com.example.project.model.Disk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListProductAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private List<Disk> dishList;

    public ListProductAdapter(Context context, int layout, List<Disk> dishList) {
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

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_display, parent, false);
        }
        imageProduct = convertView.findViewById(R.id.image_display);
        txtProductName = convertView.findViewById(R.id.title1);
        Glide.with(convertView).load(dishList.get(position).getDescription()).into(imageProduct);
        txtProductName.setText(dishList.get(position).getDiskName());
        return convertView;
    }
}
