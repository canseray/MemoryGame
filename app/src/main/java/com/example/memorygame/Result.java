package com.example.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView score,fail,name;
        int scr,fl;
        name=(TextView)findViewById(R.id.textView4);
        score=(TextView)findViewById(R.id.textView5);
        fail=(TextView)findViewById(R.id.textView6);

        Intent intent = getIntent();
        String nameX = intent.getStringExtra("name");
        scr = intent.getIntExtra("score",0);
        fl = intent.getIntExtra("fail",0);

        name.setText("Cong! "+ nameX);
        score.setText("Score " + scr);
        fail.setText("Fail " + fl);
    }
}
