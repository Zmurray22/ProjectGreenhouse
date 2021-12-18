package com.example.projectgreenhouse.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.projectgreenhouse.MainActivity;
import com.example.projectgreenhouse.R;

public class SettingsActivity extends AppCompatActivity {
    //Preferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //Theme radio buttons
    RadioGroup radioGroup_theme;
    RadioButton checkedThemeRadio;
    //gesture variables
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //swipe detector
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        //Theme changes
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        radioGroup_theme = findViewById(R.id.radioGroup_theme);
        checkedThemeRadio = radioGroup_theme.findViewById(radioGroup_theme.getCheckedRadioButtonId());
        radioGroup_theme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                //find selected radio button
                switch (checkedId){
                    case R.id.blue_option:
                        setTheme(R.style.AppTheme_Blue_Green);
                        Toast.makeText(SettingsActivity.this, "Blue theme selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.classic_option:
                        setTheme(R.style.AppTheme_Classic_Green);
                        Toast.makeText(SettingsActivity.this, "Classic theme selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.evergreen_option:
                        setTheme(R.style.AppTheme_Evergreen);
                        Toast.makeText(SettingsActivity.this, "Evergreen theme selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.orange_option:
                        setTheme(R.style.AppTheme_Orange_green);
                        Toast.makeText(SettingsActivity.this, "Orange theme selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pink_option:
                        setTheme(R.style.AppTheme_Pink_Green);
                        Toast.makeText(SettingsActivity.this, "Pink theme selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
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
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }catch (Exception e){
                Toast.makeText(SettingsActivity.this, "Swipe error", Toast.LENGTH_SHORT).show();
            }
            Log.d(DEBUG_TAG, "onFling: " + e1.toString() + e2.toString());
            return false;
        }
    }

}