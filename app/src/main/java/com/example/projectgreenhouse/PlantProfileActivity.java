package com.example.projectgreenhouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectgreenhouse.db.Plant;
import com.example.projectgreenhouse.inv.InventoryActivity;

public class PlantProfileActivity extends AppCompatActivity {

    //gesture variable
    private GestureDetectorCompat mDetector;
    private static final int IMAGE_CAPTURE_CODE = 1;
    private ImageView profileImg;
    private ImageButton btnPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        profileImg = findViewById(R.id.profileImage);
        btnPic = findViewById(R.id.add_pic);

        //Get plant info----------------------
        Intent intent = getIntent();
        String newNickname = intent.getStringExtra("newNickname");
        TextView nickname = findViewById(R.id.nickname);
        nickname.setText(newNickname);

        //Take a picture-------------------------
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    try {
                        startActivityForResult(takePictureIntent, IMAGE_CAPTURE_CODE);

                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(PlantProfileActivity.this, "Camera Error", Toast.LENGTH_SHORT).show();
                    }
            }
        });


        //Generate wikipedia link----------------------
        TextView wiki = findViewById(R.id.speciesNameWiki);
        wiki.setClickable(true);
        wiki.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://en.wikipedia.org/wiki/" + newNickname + "'> Wikipedia </a>";
        wiki.setText(Html.fromHtml(text));

        //swipe detector----------------------
        mDetector = new GestureDetectorCompat(this, new PlantProfileActivity.MyGestureListener());

    }

    //Swipe back detection-----------------------------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(DEBUG_TAG, "onDown: " + e.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try{
                if(e2.getX() - e1.getX() > 120 && Math.abs(velocityX) > 200){
                    finish();
                }
            }catch (Exception e){
                Toast.makeText(PlantProfileActivity.this, "Swipe error", Toast.LENGTH_SHORT).show();
            }
            Log.d(DEBUG_TAG, "onFling: " + e1.toString() + e2.toString());
            return false;
        }
    }

    //Receive picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_CAPTURE_CODE && resultCode == RESULT_OK){
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            profileImg.setImageBitmap(bp);
        }else if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        }
    }
}