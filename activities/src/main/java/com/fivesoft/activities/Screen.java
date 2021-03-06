package com.fivesoft.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;

class Screen {

    /**
     * Returns app usable screen width in px.
     * For the absolute screen width use {@link #getAbsoluteWidth(Context)}
     * @param context Context which is necessary to get this param.
     * @return the width described above.
     */

    public static int getWidth(Context context){
        return getAppUsableScreenSize(context).x;
    }

    /**
     * Returns app usable screen height in px (without status bar and navigation bar size).
     * For the absolute screen height use {@link #getAbsoluteHeight(Context)}
     * @param context Context which is necessary to get this param.
     * @return the height described above.
     */

    public static int getHeight(Context context){
        return getAppUsableScreenSize(context).y;
    }

    /**
     * Returns absolute screen width in px (with status bar and navigation bar size).
     * For the app usable screen width use {@link #getWidth(Context)}
     * @param context Context which is necessary to get this param.
     * @return the width described above.
     */

    public static int getAbsoluteWidth(Context context){
        return getRealScreenSize(context).x;
    }

    /**
     * Returns absolute screen height in px (with status bar and navigation bar size).
     * For the app usable screen height use {@link #getHeight(Context)}
     * @param context Context which is necessary to get this param.
     * @return the height described above.
     */

    public static int getAbsoluteHeight(Context context){
        return getRealScreenSize(context).y;
    }

    /**
     * Returns height of the status bar in px 
     * @param context Context which is necessary to get this param.
     * @return height of the status bar
     */

    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
    }

    /**
     * Returns height of the navigation bar
     * @param context Context which is necessary to get this param.
     * @return height of the navigation bar
     */

    public static int getNavigationBarHeight(Context context){
        return getNavBarSizeInternal(context);
    }

    /**
     * Returns true if the device is a tablet.
     * @param context Context which is necessary to get this param.
     * @return true if the device is a tablet.
     */

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * Returns true if the device has a navigation bar.
     * @param context Context which is necessary to get this param.
     * @return true if the device has a navigation bar.
     */

    public static boolean hasNavigationBar(Context context) {
        return  !(ViewConfiguration.get(context).hasPermanentMenuKey()) &&
                !(KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)) &&
                (getAbsoluteHeight(context) > getHeight(context));
    }


    //Private methods

    private static Point getAppUsableScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        if(windowManager == null)
            return new Point();

        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("ObsoleteSdkInt")
    private static Point getRealScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    private static int getNavBarSizeInternal(Context context){
        int result = 0;

        if(hasNavigationBar(context)) {
            //The device has a navigation bar
            Resources resources = context.getResources();
            int orientation = resources.getConfiguration().orientation;
            int resourceId;
            if (isTablet(context)){
                resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
            }  else {
                resourceId = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_width", "dimen", "android");
            }
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }
}
