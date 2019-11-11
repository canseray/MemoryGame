package com.example.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.memorygame.Constants.FAIL;
import static com.example.memorygame.Constants.NAME;
import static com.example.memorygame.Constants.SCORE;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreView, failView, nameView;
        int score, fail;
        nameView = findViewById(R.id.textView4);
        scoreView = findViewById(R.id.textView5);
        failView = findViewById(R.id.textView6);

        Intent intent = getIntent();
        String name = intent.getStringExtra(NAME);
        score = intent.getIntExtra(SCORE, 0);
        fail = intent.getIntExtra(FAIL, 0);

        nameView.setText(getString(R.string.cong, name));
        scoreView.setText(getString(R.string.score, score));
        failView.setText(getString(R.string.fail, fail));
    }
}
