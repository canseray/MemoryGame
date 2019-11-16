package com.example.memorygame;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameFragment extends Fragment implements View.OnClickListener {
    private static final String LOG_TAG = GameFragment.class.getSimpleName();
    private TextView getUserName;
    private TextView scoreTextView;
    private int mScore;
    private int mLastCard;
    private int FAIL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_game, container, false);

        getUserName = view.findViewById(R.id.getUserNameTextView);
        scoreTextView = view.findViewById(R.id.scoreTextView);
        GridLayout gameCardsGridLayout = view.findViewById(R.id.gameCardsGridLayout);

        assert getArguments() != null;
        getUserName.setText(getArguments().getString("userName"));

        Card[] allCards = new Card[16];

        //createOneCard
        for (int j=1; j<=16; j++){
            allCards[j - 1] = new Card(getActivity(),j);
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

        return view;
    }

    @Override
    public void onClick(View view) {
        final Card selectedCard = (Card) view;
        selectedCard.flip();
        if (mLastCard > 0){
            final Card card2 = view.findViewById(mLastCard);
            if (card2.getmFrontResId() == selectedCard.getmFrontResId() && card2.getId() != selectedCard.getId()){
                Button firsImageView = view.findViewById(card2.getId());
                firsImageView.setClickable(false);
                Button secondImageView = view.findViewById(selectedCard.getId());
                secondImageView.setClickable(false);
                card2.setFlippable(false);
                selectedCard.setFlippable(false);
                mScore++;
                scoreTextView.setText(String.valueOf(mScore));
                Log.i(LOG_TAG,"score" + mScore);
                Toast.makeText(getActivity(),"Yaşasın",Toast.LENGTH_SHORT).show();

                if (mScore == 8){
                    Toast.makeText(getActivity(),"oyun bitti",Toast.LENGTH_SHORT).show();
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
                FAIL++;
            }
            mLastCard = 0;
        } else {
            mLastCard = selectedCard.getId();
        }
    }
}
