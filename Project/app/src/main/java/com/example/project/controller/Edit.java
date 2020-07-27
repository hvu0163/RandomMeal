package com.example.project.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.model.Disk;

public class Edit extends AppCompatActivity {
    ImageView imageView;
    EditText title, ingre_detail, recipe_detail;
    Button but_ap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        DBContext db = new DBContext(this);
        Disk d = db.getCurrentDisk();
        imageView = findViewById(R.id.image_edit);
        Glide.with(this).load(d.getDescription()).into(imageView);
        title = findViewById(R.id.edit_title);
        title.setText(d.getDiskName());
        ingre_detail = findViewById(R.id.ingre_edit);
        ingre_detail.setText(d.getMaterial());
        recipe_detail = findViewById(R.id.recipe_edit);
        recipe_detail.setText(d.getContent());
    }

    public void onClickApply(View view) {
        DBContext db = new DBContext(this);
        Disk d = db.getCurrentDisk();

        d.setDiskName(title.getText().toString());
        d.setMaterial(ingre_detail.getText().toString());
        d.setContent(recipe_detail.getText().toString());

        db.updateDisk(d);
        Intent intent = new Intent(this, Detail.class);
        intent.putExtra("id", String.valueOf(d.getDiskID()));
        startActivity(intent);
    }
}
