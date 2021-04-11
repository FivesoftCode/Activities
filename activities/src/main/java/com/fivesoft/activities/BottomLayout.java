package com.fivesoft.activities;

import android.graphics.drawable.Drawable;
import android.view.View;

public abstract class BottomLayout {

    public BottomLayout(){

    }

    public abstract void show();

    public abstract void hide();

    public abstract void setMessage(String message);

    public abstract void setIcon(Drawable drawable);

    public abstract View getView();
}
