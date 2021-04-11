package com.fivesoft.activities;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

class Metrics {

    public static int dpToPx(int dp, Context ctx){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp, Context ctx){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, ctx.getResources().getDisplayMetrics());
    }

    public static int pxToDp(int px, Context ctx){
        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int spToDp(int sp, Context ctx){
        return pxToDp(spToPx(sp, ctx), ctx);
    }

    public static int dpToSp(int dp, Context context) {
        return (int) (dpToPx(dp, context) / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static float getDip(Context context, int input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, input, context.getResources().getDisplayMetrics());
    }

    public static int getDisplayWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


}
