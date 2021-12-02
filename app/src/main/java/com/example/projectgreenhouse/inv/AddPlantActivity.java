package com.example.projectgreenhouse.inv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectgreenhouse.R;

public class AddPlantActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.roomGHSample.REPLY";
    private EditText mEditNicknameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        mEditNicknameView = findViewById(R.id.create_nickname);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditNicknameView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else{
                    String nickname = mEditNicknameView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, nickname);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}