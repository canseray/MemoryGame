package com.example.memorygame;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import java.util.Random;
import static com.example.memorygame.Constants.NAME;

public class GameScreen extends AppCompatActivity implements View.OnClickListener{

    private static final String LOG_TAG = GameScreen.class.getSimpleName();
    private TextView scoreText;
    private Context context = GameScreen.this;
    private int mScore = 0;
    private int mLastCard = 0;
    private int mFail = 0;

//    int clickCount = 0;
//    private int currentPosition = -1;
//    ImageView currentView;

//    private static final int[] CARD_IMAGE_ARRAY = new int[] {
//            R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6,
//            R.drawable.c7, R.drawable.c8, R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4,
//            R.drawable.c5, R.drawable.c6, R.drawable.c7, R.drawable.c8
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        GridLayout gameCardsGridLayout = findViewById(R.id.gameCardsGridLayout);
        TextView userName = findViewById(R.id.userName);
        scoreText = findViewById(R.id.scoreText);
        String mUserNameText = getIntent().getStringExtra(NAME);
        userName.setText(mUserNameText);

        Card[] allCards = new Card[16];

        //cardsOnClick
        for (int j=1; j<=16; j++){
            allCards[j - 1] = new Card(context,j);
            allCards[j - 1].setOnClickListener(this);
        }
        //shuffle
        for (int j=0; j<16; j++){
            int random = (int) (Math.random() * 16);
            Card randomCard = allCards[random];
            allCards[random] = allCards[j];
            allCards[j] = randomCard;
        }
        //gridview add cards
        for (int j=0; j<16; j++){
            gameCardsGridLayout.addView(allCards[j]);
            gameCardsGridLayout.setColumnCount(4);
            gameCardsGridLayout.setRowCount(4);
        }

//        final GridViewAdapter gridViewAdapter = new GridViewAdapter(context);
//        gameCardsGridView.setAdapter(gridViewAdapter);
//        shuffleArray(CARD_IMAGE_ARRAY);
//        gameCardsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view, int newPosition, long id) {
//                int oldPosition = currentPosition;
//                int clickCountMod = clickCount % 2;
//                //if getItemResourse != wallpaper
//                    if (clickCountMod != 1) {
//                        currentView = (ImageView) view;
//                        ((ImageView) view).setImageResource(CARD_IMAGE_ARRAY[newPosition]);
//                    } else {
//                        if (CARD_IMAGE_ARRAY[newPosition] != CARD_IMAGE_ARRAY[oldPosition]) {
//                            ((ImageView) view).setImageResource(CARD_IMAGE_ARRAY[newPosition]);
//                            currentView.setImageResource(CARD_IMAGE_ARRAY[oldPosition]);
//                            final Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    ((ImageView) view).setImageResource(R.drawable.wallpaper);
//                                    currentView.setImageResource(R.drawable.wallpaper);
//                                }
//                            }, 1000);
//                            Toast.makeText(context, "eşleşmedi", Toast.LENGTH_SHORT).show();
//                        } else if (newPosition == oldPosition) {
//                            Toast.makeText(context, "farklı karta tıkla", Toast.LENGTH_SHORT).show();
//                            clickCount--;
//                        } else {
//                            ((ImageView) view).setImageResource(CARD_IMAGE_ARRAY[newPosition]);
//                            Toast.makeText(context, "yaşasın", Toast.LENGTH_SHORT).show();
//                            scoreText = findViewById(R.id.scoreText);
//                            score++;
//                            scoreText.setText(String.valueOf("score:" + score));
//                        }
//                    }
//                    clickCount++;
//                    currentPosition = newPosition;
//                }
//        });
    }

    @Override
    public void onClick(View view) {
        final Card selectedCard = (Card) view;
        selectedCard.flip();
        if (mLastCard > 0){
            final Card card2 = findViewById(mLastCard);
            if (card2.getmFrontResId() == selectedCard.getmFrontResId() && card2.getId() != selectedCard.getId()){
                Button firsImageView = findViewById(card2.getId());
                firsImageView.setClickable(false);
                Button secondImageView = findViewById(selectedCard.getId());
                secondImageView.setClickable(false);
                card2.setFlippable(false);
                selectedCard.setFlippable(false);
                mScore++;
                scoreText.setText(String.valueOf(mScore));
                Log.i(LOG_TAG,"score" + mScore);
                Toast.makeText(context,"Yaşasın",Toast.LENGTH_SHORT).show();

                if (mScore == 8){
                    Toast.makeText(context,"oyun bitti",Toast.LENGTH_SHORT).show();
                    //resultActivity
                }
            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        card2.flip();
                        selectedCard.flip();
                    }
                },1000);
                mFail++;
            }
            mLastCard = 0;
        } else {
            mLastCard = selectedCard.getId();
        }
    }

    static void shuffleArray(int[] CARD_IMAGE_ARRAY){
        Random random = new Random();
        for (int i = CARD_IMAGE_ARRAY.length - 1 ; i>0 ; i--){
            int index = random.nextInt(i+1);
            if ( i== index){
                i++;
            } else {
                int a = CARD_IMAGE_ARRAY[index];
                CARD_IMAGE_ARRAY[index] = CARD_IMAGE_ARRAY[i];
                CARD_IMAGE_ARRAY[i] = a;
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //dialog-oyundan çıkmak istiyor musun?
    }
}
