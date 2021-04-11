package com.fivesoft.activities;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public abstract class Toolbar {


    public Toolbar(){

    }

    public Toolbar setTitle(String title){
        getTitle().setText(title);
        return this;
    }

    public Toolbar setColor(int color){
        getBase().setCardBackgroundColor(color);
        return this;
    }

    public Toolbar setOnLeftButtonClickedListener(View.OnClickListener onClickListener){
        getLeftButton().setOnClickListener(onClickListener);
        return this;
    }

    public Toolbar setOnRightButtonClickedListener(View.OnClickListener onClickListener){
        getRightButton().setOnClickListener(onClickListener);
        return this;
    }

    public int getColor(){
        return getBase().getCardBackgroundColor().getDefaultColor();
    }

    public abstract View getLeftButton();

    public abstract View getRightButton();

    public abstract TextView getTitle();

    public abstract CardView getBase();

    public abstract View getView();

    public abstract void setLeftButtonIcon(Drawable drawable);

    public abstract void setRightButtonIcon(Drawable drawable);

    /**
     * Called when toolbar should be animated.
     * You can use this ex. to change title text size when
     * scrolling in ScrollingActivity.
     * @param animationFactor Animation indicator. Changes ex. when activity
     *                        is being scrolled.
     *                        Max value: 1
     *                        Min value: 0
     */

    public void animate(float animationFactor){

    }
}
