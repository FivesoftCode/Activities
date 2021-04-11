package com.fivesoft.activities.bottom_layouts;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fivesoft.activities.BottomLayout;
import com.fivesoft.activities.R;
import com.fivesoft.activities.ToolbarActivity;

public class DefaultBottomLayout extends BottomLayout {

    private TextView message;
    private ImageView icon;
    private View main;

    public DefaultBottomLayout(ToolbarActivity activity) {
        main = activity.getLayoutInflater().inflate(R.layout.bottom_layout_default, activity.getBottomLayoutParent(), false);
        message = main.findViewById(R.id.message);
        icon = main.findViewById(R.id.icon);
    }

    @Override
    public void show() {
        main.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        main.setVisibility(View.GONE);
    }

    @Override
    public void setMessage(String message) {
        this.message.setText(message);
    }

    @Override
    public void setIcon(Drawable drawable) {
        this.icon.setImageDrawable(drawable);
    }

    @Override
    public View getView() {
        return main;
    }
}
