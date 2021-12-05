package com.example.projectgreenhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class PlantProfileActivity extends AppCompatActivity {

    //gesture variable
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        //Get plant info----------------------
        Intent intent = getIntent();
        String newNickname = intent.getStringExtra("newNickname");

        TextView nickname = findViewById(R.id.nickname);
        nickname.setText(newNickname);

        TextView wiki = findViewById(R.id.speciesNameWiki);
        wiki.setClickable(true);
        wiki.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://en.wikipedia.org/wiki/" + newNickname + "'> Wikipedia </a>";
        wiki.setText(Html.fromHtml(text));

        //swipe detector-------------
        mDetector = new GestureDetectorCompat(this, new PlantProfileActivity.MyGestureListener());

    }

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
}