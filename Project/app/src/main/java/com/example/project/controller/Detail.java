package com.example.project.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.model.Disk;

public class Detail extends AppCompatActivity {
    ImageView imageView;
    TextView title, ingre_detail, recipe_detail;
    Button but_edit, but_del, but_like, but_unlike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        but_edit = findViewById(R.id.but_detail_edit);
        but_del = findViewById(R.id.but_detail_del);
        but_like = findViewById(R.id.but_detail_like);
        but_unlike = findViewById(R.id.but_detail_unlike);
        but_unlike.setVisibility(View.GONE);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        DBContext db = new DBContext(this);

        if(db.getAccount().getUsername().equals("admin")) {
            but_edit.setVisibility(View.VISIBLE);
            but_del.setVisibility(View.VISIBLE);
        }
        Disk d = db.getADisk(id);
        boolean ck = db.checkLike();
        if(ck) {
            but_like.setVisibility(View.GONE);
            but_unlike.setVisibility(View.VISIBLE);
        }
        imageView = findViewById(R.id.image_edit);
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
        Intent intent = new Intent(this, Edit.class);
        this.startActivity(intent);
    }

    public void onClickDel(View view) {
        DBContext db = new DBContext(this);
        db.delDisk();
        Intent intent = new Intent(this, Home.class);
        this.startActivity(intent);
    }

    public void onClickLike(View view) {
        DBContext db = new DBContext(this);
        Boolean ck = db.addFavourite();
        if(ck) {
//            Toast.makeText(this, "ss", Toast.LENGTH_LONG).show();
            but_like.setVisibility(View.GONE);
            but_unlike.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "ff", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickUnlike(View view) {
        DBContext db = new DBContext(this);
        Boolean ck = db.unFavourite();
        if(ck) {
//            Toast.makeText(this, "ss", Toast.LENGTH_LONG).show();
            but_like.setVisibility(View.VISIBLE);
            but_unlike.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "ff", Toast.LENGTH_LONG).show();
        }
    }
}
