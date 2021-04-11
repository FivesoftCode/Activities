package com.fivesoft.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public abstract class ScrollingActivity extends ToolbarActivity {


    private NestedScrollView content;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int minToolBarSize = 100;
    private int maxToolbarSize = 200;
    private float startCardElevation = 0;
    protected boolean animateToolbarLines = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.content_main_scrolling_activity);

        contentTopTranslation = 0;

        content = super.findViewById(R.id.scroll);

        swipeRefreshLayout = getSwipeRefreshLayout();

        startCardElevation = getToolbar().getBase().getCardElevation();

        getToolbar().getView().post(() -> {
            minToolBarSize = getToolbar().getBase().getHeight();
            maxToolbarSize = minToolBarSize * 3;

            //Disable swipe refresh
            swipeRefreshLayout.setEnabled(true);

            int startProgressViewOffset = maxToolbarSize - Screen.getStatusBarHeight(this);
            int progressViewMargin = Metrics.dpToPx(20, this);

            content.setPadding(content.getPaddingLeft(), maxToolbarSize - minToolBarSize, content.getPaddingRight(), content.getPaddingBottom());

            if(animateToolbarLines)
                getToolbar().getTitle().setMaxLines(3);

            getToolbar().animate(1);

            Animator.setViewHeight(getToolbar().getView(), maxToolbarSize);
            Animator.setViewWidth(getToolbar().getView(), Screen.getWidth(this));
            Animator.setViewHeight(toolbarSpace, maxToolbarSize + Metrics.dpToPx((int) startCardElevation, this));

            getToolbar().getBase().setCardElevation(0);

            swipeRefreshLayout.setProgressViewOffset(true, startProgressViewOffset,
                    maxToolbarSize + progressViewMargin);

            swipeRefreshLayout.setOnRefreshListener(() -> onSwipedToRefresh(swipeRefreshLayout));

            //Animate the toolbar
            content.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                    (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                        int toolbarHeight = Math.max(maxToolbarSize - content.getScrollY(), minToolBarSize);
                        Animator.setViewHeight(getToolbar().getView(), toolbarHeight);
                        Animator.setViewHeight(toolbarSpace, toolbarHeight +  Metrics.dpToPx((int) startCardElevation, this));

                        getToolbar().getBase().setCardElevation(startCardElevation * ((maxToolbarSize - toolbarHeight) / (float) minToolBarSize));

                        getToolbar().animate((float) toolbarHeight / maxToolbarSize);

                        if(animateToolbarLines)
                            getToolbar().getTitle().setMaxLines(toolbarHeight / minToolBarSize);

                    });
        });

        content.post(() -> content.scrollTo(0, 0));
    }

    @Override
    public void setContentView(int layoutResID) {
        View contentView = this.getLayoutInflater().inflate(layoutResID, null);
        if (contentView != null) {
            content.removeAllViewsInLayout();
            content.addView(contentView);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return content.findViewById(id);
    }
}
