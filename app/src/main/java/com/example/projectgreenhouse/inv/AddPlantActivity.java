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

        //Submit new item
        final Button addButton = findViewById(R.id.add_button_save);
        addButton.setOnClickListener(new View.OnClickListener(){
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

        //Cancel activity
        final Button cancelButton = findViewById(R.id.add_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                setResult(RESULT_CANCELED, replyIntent);
                finish();
            }
        });
    }
}