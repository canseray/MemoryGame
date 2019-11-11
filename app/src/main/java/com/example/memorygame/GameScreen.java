package com.example.memorygame;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import static com.example.memorygame.Constants.FAIL;
import static com.example.memorygame.Constants.MESSAGE;
import static com.example.memorygame.Constants.NAME;
import static com.example.memorygame.Constants.SCORE;

public class GameScreen extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = GameScreen.class.getSimpleName();

    private TextView mMessageView;
    private String mMessage;

    private int mLastCard = 0;
    private int mScore = 0;
    private int mFail = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        GridLayout gameCards = findViewById(R.id.gameCards);
        mMessageView = findViewById(R.id.messageView);
        mMessage = getIntent().getStringExtra(MESSAGE);
        Card[] allCards = new Card[16];

        for (int j = 1; j <= 16; j++) {
            allCards[j - 1] = new Card(this, j);
            allCards[j - 1].setOnClickListener(this);
        }
        // TODO fix here, probably it was implemented for shuffling but it throws IndexOutOfBoundsException
//        for (int j = 0; j < 24; j++) {
//            int random = (int) (Math.random() * 16);
//            Card c = allCards[random];
//            allCards[random] = allCards[j];
//            allCards[j] = c;
//        }
        for (int j = 0; j < 16; j++) {
            gameCards.addView(allCards[j]);
        }
    }

    @Override
    public void onClick(View view) {
        final Card selectedCard = (Card) view;
        selectedCard.flip();
        if (mLastCard > 0) {
            final Card c2 = findViewById(mLastCard);
            if (c2.getFrontResId() == selectedCard.getFrontResId() && c2.getId() != selectedCard.getId()) {

                Button u = findViewById(c2.getId());
                u.setClickable(false);
                Button y = findViewById(selectedCard.getId());
                y.setClickable(false);

                c2.setFlippable(false);
                selectedCard.setFlippable(false);
                mScore++;
                mMessageView.setText(getString(R.string.truee));

                if (mScore == 8) {
                    Intent intent = new Intent(GameScreen.this, ResultActivity.class);
                    intent.putExtra(FAIL, mFail);
                    intent.putExtra(SCORE, mScore);
                    intent.putExtra(NAME, mMessage);
                    startActivity(intent);
                }
            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        c2.flip();
                        selectedCard.flip();
                    }
                }, 500);
                mFail++;
                mMessageView.setText(getString(R.string.failing_match, mMessage));

            }
            mLastCard = 0;
        } else {
            mLastCard = selectedCard.getId();
        }
    }
}
