package com.example.projectgreenhouse.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectgreenhouse.R;
import com.example.projectgreenhouse.viewmodel.GreenhouseViewModel;

public class PlantProfileActivity extends AppCompatActivity {

    //gesture variable
    private GestureDetectorCompat mDetector;
    private static final int IMAGE_CAPTURE_CODE = 1;
    private ImageView profileImg;
    private ImageButton btnPic;
    private SeekBar zoneSetting;
    private TextView tempZone;
    private Button noteSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        profileImg = findViewById(R.id.profileImage);
        btnPic = findViewById(R.id.add_pic);
        zoneSetting = findViewById(R.id.seekBar);
        tempZone = findViewById(R.id.zone_temp);
        noteSave = findViewById(R.id.note_save);

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

        //Seekbar for hardiness zone------------------------------
        if(zoneSetting != null){
            zoneSetting.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        switch (progress){
                            case 0:
                                tempZone.setText(R.string.hard_zone1);
                                break;
                            case 1:
                                tempZone.setText(R.string.hard_zone2);
                                break;
                            case 2:
                                tempZone.setText(R.string.hard_zone3);
                                break;
                            case 3:
                                tempZone.setText(R.string.hard_zone4);
                                break;
                            case 4:
                                tempZone.setText(R.string.hard_zone5);
                                break;
                            case 5:
                                tempZone.setText(R.string.hard_zone6);
                                break;
                            case 6:
                                tempZone.setText(R.string.hard_zone7);
                                break;
                            case 7:
                                tempZone.setText(R.string.hard_zone8);
                                break;
                            case 8:
                                tempZone.setText(R.string.hard_zone9);
                                break;
                            case 9:
                                tempZone.setText(R.string.hard_zone10);
                                break;
                        }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

        //Save notes listener--------------------------------
        noteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlantProfileActivity.this, "Notes Saved", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
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

    //Receive picture------------
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