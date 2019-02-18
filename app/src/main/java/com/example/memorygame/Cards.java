package com.example.memorygame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;

public class Cards extends Button {


    boolean isFlipped = false;
    boolean isFlippable = true;
    int backID;
    int frontID =0;
    Drawable backG;
    Drawable frontG;

    public Cards(Context context,int id) {
        super(context);

        setId(id);

        if(id%8==1)
            frontID=R.drawable.c1;
        if(id%8==2)
            frontID=R.drawable.c2;
        if(id%8==3)
            frontID=R.drawable.c3;
        if(id%8==4)
            frontID=R.drawable.c4;
        if(id%8==5)
            frontID=R.drawable.c5;
        if(id%8==6)
            frontID=R.drawable.c6;
        if(id%8==7)
            frontID=R.drawable.c7;
        if(id%8==8)
            frontID=R.drawable.c8;

         backID = R.drawable.back;
         backG = AppCompatDrawableManager.get().getDrawable(context,backID);
         frontG = AppCompatDrawableManager.get().getDrawable(context,frontID);
         setBackground(backG);




    }
    public void flip(){
        if(!isFlippable)
            return;
        if(!isFlipped) {
            setBackground(frontG);
            isFlipped = true;
        }
        else {
            setBackground(backG);
            isFlipped = false;
        }

    }
}
