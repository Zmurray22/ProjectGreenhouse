package com.example.projectgreenhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AddPicActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pic);

        //Get pic info----------------------
        Intent intent = getIntent();
        Bundle pic_preview = intent.getBundleExtra("preview_pic");
        ImageView tempPicture = findViewById(R.id.temp_image);
        Bitmap imageBitmap = (Bitmap) pic_preview.get("data");
        tempPicture.setImageBitmap(imageBitmap);

        Button btnchangePP = findViewById(R.id.btn_save1);
        Button btnaddToGallery = findViewById(R.id.btn_save2);
        Button btncancelAction = findViewById(R.id.cancel_button);

        btnchangePP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: change profile pic image view and change profile pic path
            }
        });

        btnaddToGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: add photo to gallery array
            }
        });

        btncancelAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    //Change profile picture
    public void changeProfilePic(){

    }

    //Add picture to gallery
    public void addToGallery(){

    }
}