package com.fivesoft.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class EmptyActivity extends AppCompatActivity {

    private View contentView;

    public EmptyActivity(){
        
    }

    protected static OnActivityFinishListener activityFinishListener;
    protected static OnActivityResultsListener activityResultsListener;
    protected static OnActivityDestroyListener activityDestroyListener;
    protected static Map<String, Object> params = new HashMap<>();


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        contentView = getWindow().getDecorView().findViewById(android.R.id.content);
    }

    protected void sendResults(Object results){
        if(activityResultsListener != null)
            activityResultsListener.onResults(results);
    }

    protected Context context(){
        return getApplicationContext();
    }

    protected String getStringParam(String key){
        try{
            return (String) params.get(key);
        } catch (Exception e){
            return null;
        }
    }

    protected int getIntParam(String key){
        try{
            return (int) params.get(key);
        } catch (Exception e){
            return Integer.MAX_VALUE;
        }
    }

    /**
     *
     * @param key - name of param you want to get
     * @return - returns given int param or MAX_VALUE of int
     * if key doesn't exist or value is not an integer.
     *
     */

    protected Object getParam(String key){
        try{
            return params.get(key);
        } catch (Exception e){
            return Integer.MAX_VALUE;
        }
    }

    protected Map<String, Object> getParams(){
        return params;
    }

    /**
     * Sets the status bar color.
     * @param color The color of status bar you want to set.
     */

    protected void setStatusBarColor(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(color);
    }

    /**
     * Sets the navigation bar color.
     * @param color The color of navigation bar you want to set.
     */

    protected void setNavigationBarColor(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(color);
    }

    protected void showMessage(String message){
        Snackbar snackbar = Snackbar.make(contentView, message, Math.max(Math.min(7000, message.length() * 100), 1500));
        snackbar.show();
    }

    protected void showMessage(String message, String buttonText, View.OnClickListener onClickListener){
        Snackbar snackbar = Snackbar.make(contentView, message, Math.max(Math.min(7000, message.length() * 100), 1500) + 1000);
        snackbar.setAction(buttonText, onClickListener);
        snackbar.show();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        if(activityDestroyListener != null)
            activityDestroyListener.onDestroy();
        if(activityFinishListener != null)
            activityFinishListener.onFinish();
        super.onDestroy();
    }

    /**
     * Starts the activity. Do not forget to declare
     * it in your AndroidManifest.xml !
     */

    public void start(Activity activity){
        activity.startActivity(new Intent().setClass(activity, this.getClass()));
    }

    public EmptyActivity setFinishListener(OnActivityFinishListener listener){
        activityFinishListener = listener;
        return this;
    }

    public EmptyActivity waitForResults(OnActivityResultsListener listener){
        activityResultsListener = listener;
        return this;
    }

    public EmptyActivity setDestroyListener(OnActivityDestroyListener listener){
        activityDestroyListener = listener;
        return this;
    }

    public EmptyActivity withParams(Map<String, Object> params){
        EmptyActivity.params = params;
        return this;
    }

    public EmptyActivity withParam(String key, String param){
        params.put(key, param);
        return this;
    }

    public interface OnActivityFinishListener {
        void onFinish();
    }

    public interface OnActivityDestroyListener {
        void onDestroy();
    }

    public interface OnActivityResultsListener {
        void onResults(Object results);
    }

}
