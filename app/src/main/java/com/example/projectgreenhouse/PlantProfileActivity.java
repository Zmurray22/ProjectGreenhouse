package com.example.projectgreenhouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectgreenhouse.db.Plant;
import com.example.projectgreenhouse.inv.InventoryActivity;

public class PlantProfileActivity extends AppCompatActivity {

    //gesture variable
    private GestureDetectorCompat mDetector;
    final int REQUEST_IMAGE_CAPTURE = 100;
    final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        //Get plant info----------------------
        Intent intent = getIntent();
        String newNickname = intent.getStringExtra("newNickname");
        TextView nickname = findViewById(R.id.nickname);
        nickname.setText(newNickname);

        //Take a picture-------------------------
        ImageButton addPic = findViewById(R.id.add_pic);
        addPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
                }else {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    try {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(PlantProfileActivity.this, "Camera Error", Toast.LENGTH_SHORT).show();
                    }
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

    //Recieve pic and pass to the AddPic activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            assert data != null;
            Bundle extras = data.getExtras();
            Intent intent = new Intent(PlantProfileActivity.this, AddPicActivity.class);
            intent.putExtra("preview_pic", extras);
            startActivity(intent);
        }
    }
}