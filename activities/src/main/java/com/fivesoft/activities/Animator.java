package com.fivesoft.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;

class Animator {

    public static int getViewHeight(final View view, final Window window) {
        Display display = window.getWindowManager().getDefaultDisplay();
        view.measure(display.getWidth(), display.getHeight());

        return view.getMeasuredHeight();
    }


    public static int getViewWidth(final View view, final Window window) {
        Display display = window.getWindowManager().getDefaultDisplay();
        view.measure(display.getWidth(), display.getHeight());

        return view.getMeasuredWidth();
    }

    public static void shakeAnimation(View view){
        ObjectAnimator
                .ofFloat(view, "translationX", 0, 25, -25, 25, -25,15, -15, 6, -6, 0)
                .setDuration(400)
                .start();
    }

    public static void simpleShowAnimation(final View view, Window window) {

        view.setVisibility(View.VISIBLE);
        int viewHeight = getViewHeight(view, window);
        int viewWidth = getViewWidth(view, window);

        ValueAnimator animator1 = ValueAnimator.ofInt(0, viewHeight);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
            }
        });

        ValueAnimator animator2 = ValueAnimator.ofInt(0, viewWidth);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewWidth(view, (int) animation.getAnimatedValue());
            }
        });

        animator1.start();
        animator2.start();
    }

    public static void simpleShowAnimation(final View view, int finalDpHeight, int finalDpWidth, Context context) {

        view.setVisibility(View.VISIBLE);
        int viewHeight = Metrics.dpToPx(finalDpHeight, context);
        int viewWidth = Metrics.dpToPx(finalDpWidth, context);

        ValueAnimator animator1 = ValueAnimator.ofInt(0, viewHeight);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
            }
        });

        ValueAnimator animator2 = ValueAnimator.ofInt(0, viewWidth);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewWidth(view, (int) animation.getAnimatedValue());
            }
        });

        animator1.start();
        animator2.start();
    }

    public static void simpleHideAnimation(final View view, Window window) {

        view.setVisibility(View.VISIBLE);
        int viewHeight = getViewHeight(view, window);
        int viewWidth = getViewWidth(view, window);

        ValueAnimator animator1 = ValueAnimator.ofInt(viewHeight, 0);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
                if ((int) animation.getAnimatedValue() == 0) {
                    view.setVisibility(View.GONE);
                }
            }
        });


        ValueAnimator animator2 = ValueAnimator.ofInt(viewWidth, 0);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewWidth(view, (int) animation.getAnimatedValue());
            }
        });

        animator1.start();
        animator2.start();
    }

    public static void hideAnimationWithoutChangingVisibility(final View view, Window window) {

        int viewHeight = getViewHeight(view, window);
        int viewWidth = getViewWidth(view, window);

        ValueAnimator animator1 = ValueAnimator.ofInt(viewHeight, 0);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
            }
        });


        ValueAnimator animator2 = ValueAnimator.ofInt(viewWidth, 0);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewWidth(view, (int) animation.getAnimatedValue());
            }
        });

        animator1.start();
        animator2.start();
    }

    public static void simpleHideAnimation(final View view, int viewHeightDp, int viewWidthDp, Context context) {

        view.setVisibility(View.VISIBLE);
        int height = Metrics.dpToPx(viewHeightDp, context);
        int width = Metrics.dpToPx(viewWidthDp, context);

        ValueAnimator animator1 = ValueAnimator.ofInt(height, 0);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
                if ((int) animation.getAnimatedValue() == 0) {
                    view.setVisibility(View.GONE);
                }
            }
        });


        ValueAnimator animator2 = ValueAnimator.ofInt(width, 0);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewWidth(view, (int) animation.getAnimatedValue());
            }
        });

        animator1.start();
        animator2.start();
    }

    public static void showUsingOnlyHeight(final View view, Window window) {
        view.setVisibility(View.VISIBLE);
        int viewHeight = getViewHeight(view, window);

        ValueAnimator animator1 = ValueAnimator.ofInt(0, viewHeight);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
            }
        });
        animator1.start();
    }

    public static void showUsingOnlyHeightWithoutChangingVisibility(final View view, Window window) {
        int viewHeight = getViewHeight(view, window);

        ValueAnimator animator1 = ValueAnimator.ofInt(0, viewHeight);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
            }
        });
        animator1.start();
    }

    public static void hideUsingOnlyHeight(final View view, Window window) {
        int viewHeight = getViewHeight(view, window);

        ValueAnimator animator1 = ValueAnimator.ofInt(viewHeight, 0);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
                if ((int) animation.getAnimatedValue() == 0) {
                    view.setVisibility(View.GONE);
                }
            }
        });

        animator1.start();
    }

    public static void hideUsingOnlyHeightWithoutChangingVisibility(final View view, Window window) {
        int viewHeight = getViewHeight(view, window);

        ValueAnimator animator1 = ValueAnimator.ofInt(viewHeight, 0);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setViewHeight(view, (int) animation.getAnimatedValue());
            }
        });

        animator1.start();
    }

    public static int getRedGreenScalePercentageColor(int procent) {

        if (procent > 100) {
            procent = 100;
        } else if (procent < 0) {
            procent = 0;
        }
        int r;
        int g;
        int b = 54;
        int prc = procent;
        if (prc < 50) {
            r = 250;
            g = 40 + (prc * 3);
        } else {
            r = 250 - (prc - 50) * 4;
            g = 190;
        }

        return Color.parseColor(String.format("#%02x%02x%02x", r, g, b));
    }

    public static int getWhiteBlackScalePercentColor(int procent) {

        int c = 255 - (255 * procent / 100);

        return Color.parseColor(String.format("#%02x%02x%02x", c, c, c));
    }

    public static int darkenColor(int color, float darkness){
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * darkness);
        int g = Math.round(Color.green(color) * darkness);
        int b = Math.round(Color.blue(color) * darkness);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }


    public static void setViewHeight(View view, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }

    public static void setViewWidth(View view, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

    public static void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
}
