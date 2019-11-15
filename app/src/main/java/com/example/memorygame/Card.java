package com.example.memorygame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatDrawableManager;

@SuppressLint("ViewConstructor")
public class Card extends AppCompatButton {

    private static final String LOG_TAG = Card.class.getSimpleName();

    private boolean mIsFlipped = false;
    private boolean mIsFlippable = true;
    private Drawable mFrontDrawable;
    private Drawable mBackDrawable;
    private int mFrontResId = 0;

    public Card(Context context, int id){
        super(context);
        setId(id);
        mBackDrawable = AppCompatDrawableManager.get().getDrawable(context,R.drawable.vader);
        mFrontDrawable = AppCompatDrawableManager.get().getDrawable(context, mFrontResId);
        setBackground(mBackDrawable);

        ViewGroup.LayoutParams params = getLayoutParams();
        if (params != null){
            params.width = 250;
            params.height = 250;
        } else {
            params = new ViewGroup.LayoutParams(250,250);
        }
        setLayoutParams(params);
    }

    public void setId(int id){
        super.setId(id);
        if (id % 8 == 0)
            mFrontResId = R.drawable.c1;
        if (id % 8 == 1)
            mFrontResId = R.drawable.c2;
        if (id % 8 == 2)
            mFrontResId = R.drawable.c3;
        if (id % 8 == 3)
            mFrontResId = R.drawable.c4;
        if (id % 8 == 4)
            mFrontResId = R.drawable.c5;
        if (id % 8 == 5)
            mFrontResId = R.drawable.c6;
        if (id % 8 == 6)
            mFrontResId = R.drawable.c7;
        if (id % 8 == 7)
            mFrontResId = R.drawable.c8;
    }

    public int getmFrontResId(){
        return mFrontResId;
    }

    public boolean isFlipped(){
        return mIsFlipped;
    }

    public boolean isFlippable(){
        return mIsFlippable;
    }

    public void setFlippable(boolean flippable){
        mIsFlippable = flippable;
    }

    public void flip(){
        if (!mIsFlippable)
            return;
        if (!mIsFlipped){
            setBackground(mFrontDrawable);
            mIsFlipped = true;
        } else {
            setBackground(mBackDrawable);
            mIsFlipped = false;
        }
    }








}

