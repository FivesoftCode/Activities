package com.fivesoft.activities;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fivesoft.activities.toolbars.DefaultToolbar;

public abstract class ToolbarActivity extends EmptyActivity {


    private LinearLayout content;
    private LinearLayout bottomLayoutPlace;
    private LinearLayout topLayoutPlace;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;
    private BottomLayout bottomLayout;
    private View topLayout;
    protected LinearLayout toolbarSpace;
    protected int contentTopTranslation = Integer.MAX_VALUE;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.content_main_toolbar_activity);

        content = super.findViewById(R.id.content);
        bottomLayoutPlace = super.findViewById(R.id.bottomLayout);
        topLayoutPlace = super.findViewById(R.id.topLayout);
        toolbarSpace = super.findViewById(R.id.toolbar);

        if(contentTopTranslation == Integer.MAX_VALUE)
            contentTopTranslation = Metrics.dpToPx(20, this);

        //Create toolbar
        setToolbar(onToolbarCreated());
        //Create bottom layout
        setBottomLayout(onBottomLayoutCreated());
        //Create top layout
        setTopLayout(onTopLayoutCreated());

        swipeRefreshLayout = super.findViewById(R.id.swiperefresh);

        //Setup system bars
        int toolbarColor = toolbar.getBase().getCardBackgroundColor().getDefaultColor();
        int backgroundColor = getBackgroundColor(super.findViewById(R.id.main));

        setStatusBarColor(toolbarColor);
        setNavigationBarColor(backgroundColor);

        int flags = 0;

        if(ColorUtils.calculateLuminance(toolbarColor) > 0.5 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;


        if(ColorUtils.calculateLuminance(backgroundColor) > 0.5 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            flags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;

        //Setup window flags
        getWindow().getDecorView().setSystemUiVisibility(flags);


        //Disable swipe refresh
        swipeRefreshLayout.setEnabled(true);

        toolbar.getView().post(() -> {

            int toolbarSize = toolbar.getView().getHeight();

            int startProgressViewOffset = toolbarSize - Screen.getStatusBarHeight(this);
            int progressViewMargin = Metrics.dpToPx(20, this);

            content.setPadding(content.getPaddingLeft(), toolbarSize - contentTopTranslation, content.getPaddingRight(), content.getPaddingBottom());
            bottomLayoutPlace.setPadding(bottomLayoutPlace.getPaddingLeft(), toolbarSize - contentTopTranslation, bottomLayoutPlace.getPaddingRight(), bottomLayoutPlace.getPaddingBottom());
            bottomLayoutPlace.setPadding(topLayoutPlace.getPaddingLeft(), toolbarSize - contentTopTranslation, topLayoutPlace.getPaddingRight(), topLayoutPlace.getPaddingBottom());

            swipeRefreshLayout.setProgressViewOffset(true, startProgressViewOffset,
                    toolbarSize + progressViewMargin);

            Animator.setViewHeight(toolbarSpace, toolbarSize + Metrics.dpToPx(5, this));
        });

        swipeRefreshLayout.setOnRefreshListener(() -> onSwipedToRefresh(swipeRefreshLayout));

    }

    /**
     * Setups the title of the activity.
     * It will be displayed on the toolbar.
     * @param title The title you want to set.
     */

    protected void setTitle(String title){
        toolbar.setTitle(title);
    }

    /**
     * Returns toolbar object.
     * @return The toolbar.
     */

    protected Toolbar getToolbar(){
        return toolbar;
    }

    protected BottomLayout getBottomLayout(){
        return bottomLayout;
    }

    protected View getTopLayout(){
        return topLayout;
    }

    /**
     * Setups the background color of the activity.
     * @param color The color you want to set.
     */

    protected void setBackgroundColor(int color){
        super.findViewById(R.id.main).setBackgroundColor(color);
    }

    @Override
    public void setContentView(int layoutResID) {
        content.removeAllViewsInLayout();
        View contentView = this.getLayoutInflater().inflate(layoutResID, content, false);
        if (contentView != null) {
            content.removeAllViewsInLayout();
            content.addView(contentView);
        }
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return content.findViewById(id);
    }


    /**
     * Called when activity is being created.
     * @return The toolbar for your activity.
     */

    @NonNull
    protected abstract Toolbar onToolbarCreated();

    /**
     * Called when user swipes to refresh.
     * Refresh the data displayed in your activity then
     * use {@link androidx.swiperefreshlayout.widget.SwipeRefreshLayout#setRefreshing(boolean)}
     * to stop loading animation.
     * @param swipeRefreshLayout The SwipeRefreshLayout
     */

    protected void onSwipedToRefresh(SwipeRefreshLayout swipeRefreshLayout){

    }

    protected BottomLayout onBottomLayoutCreated(){
        return null;
    }

    protected View onTopLayoutCreated(){
        return null;
    }

    protected SwipeRefreshLayout getSwipeRefreshLayout(){
        return swipeRefreshLayout;
    }

    public LinearLayout getToolbarParent(){
        return toolbarSpace;
    }

    public LinearLayout getBottomLayoutParent(){
        return bottomLayoutPlace;
    }

    public LinearLayout getTopLayoutParent(){
        return topLayoutPlace;
    }

    private void setToolbar(Toolbar toolBar){
        this.toolbar = toolBar;

        toolbarSpace.removeAllViews();

        if(toolBar == null)
            return;

        toolbarSpace.addView(toolbar.getView());
    }

    private void setBottomLayout(BottomLayout bottomLayout){
        this.bottomLayout = bottomLayout;

        bottomLayoutPlace.removeAllViews();

        if(bottomLayout == null)
            return;

        bottomLayoutPlace.addView(bottomLayout.getView());
    }

    private void setTopLayout(View topLayout){
        this.topLayout = topLayout;

        topLayoutPlace.removeAllViews();

        if(topLayout == null)
            return;

        topLayoutPlace.addView(topLayout);
    }

    private static int getBackgroundColor(View view) {
        Drawable drawable = view.getBackground();
        if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            return colorDrawable.getColor();
        }
        return 0;
    }

}
