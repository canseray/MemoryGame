package com.example.memorygame;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    int lastCard = 0;
    int score = 0;
    int fail = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Intent intent = getIntent();
        GridLayout gameCards = (GridLayout) findViewById(R.id.gameCards);
        final TextView message = (TextView)findViewById(R.id.textView2);
        Cards allCards[] = new Cards[16];
        final String name = intent.getStringExtra("message");

        for(int j=1 ; j<16 ; j++){
            allCards [j-1] = new Cards(this,j);
            allCards[j-1].setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick (View view){
                    final Cards c = (Cards)view;
                    c.flip();
                    if(lastCard>0){
                        final Cards c2 = (Cards)findViewById(lastCard);
                        if(c2.frontID==c.frontID && c2.getId() !=c.getId()){

                            Button u = (Button)findViewById(c2.getId());
                            u.setClickable(false);
                            Button y = (Button)findViewById(c.getId());
                            y.setClickable(false);

                            c2.isFlippable=false;
                            c.isFlippable=false;
                            score++;
                            message.setText("True!");

                            if(score==8){
                                Intent intent = new Intent(GameScreen.this,Result.class);
                                intent.putExtra("Fail",fail);
                                intent.putExtra("Score",score);
                                intent.putExtra("Name",name);
                                startActivity(intent);
                            }
                        }
                        else{
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    c2.flip();
                                    c.flip();
                                }
                            },500);
                            fail++;
                            message.setText("Fail eşleşme " + name);

                        }
                        lastCard=0;
                    }
                    else{

                        lastCard=c.getId();
                    }
                }
            });
        }
        for(int j=0 ; j<24 ; j++){
            int rndm = (int)(Math.random()*16);
            Cards c = allCards[rndm];
            allCards[rndm]=allCards[j];
            allCards[j]=c;
        }
        for(int j=0 ; j<16 ; j++){
            gameCards.addView(allCards[j]);
        }
    }
}
