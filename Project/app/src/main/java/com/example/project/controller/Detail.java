package com.example.project.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.model.Disk;

public class Detail extends AppCompatActivity {
    ImageView imageView;
    TextView title, ingre_detail, recipe_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        DBContext db = new DBContext(this);
        Disk d = db.getADisk(id);
        imageView = findViewById(R.id.image_detail);
        Glide.with(this).load(d.getDescription()).into(imageView);
        title = findViewById(R.id.title_detail);
        title.setText(d.getDiskName());
        ingre_detail = findViewById(R.id.ingre_detail);
        ingre_detail.setText(d.getMaterial());
        recipe_detail = findViewById(R.id.recipe_detail);
        recipe_detail.setText(d.getContent());
        getIntent().removeExtra("id");
    }

    public void onCLickEdit(View view) {

    }
}
