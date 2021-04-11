package com.fivesoft.activities.toolbars;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.fivesoft.activities.R;
import com.fivesoft.activities.Toolbar;
import com.fivesoft.activities.ToolbarActivity;

import me.grantland.widget.AutofitTextView;

public class DefaultToolbar extends Toolbar {

    private final View main;

    private final CardView leftBtn;
    private final CardView rightBtn;
    private final AutofitTextView title;
    private final CardView base;

    private ImageView leftBtnIcon;
    private ImageView rightBtnIcon;

    public DefaultToolbar(ToolbarActivity activity){
        main = activity.getLayoutInflater().inflate(R.layout.toolbar_default, activity.getToolbarParent(), false);
        leftBtn = main.findViewById(R.id.left_button);
        rightBtn = main.findViewById(R.id.right_button);
        title = main.findViewById(R.id.title);
        base = main.findViewById(R.id.main);

        base.setCardBackgroundColor(Color.parseColor("#EFEFEF"));

        leftBtnIcon = main.findViewById(R.id.left_btn_icon);
        rightBtnIcon = main.findViewById(R.id.right_btn_icon);
    }

    public DefaultToolbar setLeftButtonIcon(int resId){
        leftBtnIcon.setImageResource(resId);
        return this;
    }

    public DefaultToolbar setRightButtonIcon(int resId){
        rightBtnIcon.setImageResource(resId);
        return this;
    }

    @Override
    public View getLeftButton() {
        return leftBtn;
    }

    @Override
    public View getRightButton() {
        return rightBtn;
    }

    @Override
    public TextView getTitle() {
        return title;
    }

    @Override
    public CardView getBase() {
        return base;
    }

    @Override
    public View getView() {
        return main;
    }

    @Override
    public void setLeftButtonIcon(Drawable drawable) {
        leftBtnIcon.setImageDrawable(drawable);
    }

    @Override
    public void setRightButtonIcon(Drawable drawable) {
        rightBtnIcon.setImageDrawable(drawable);
    }

}
