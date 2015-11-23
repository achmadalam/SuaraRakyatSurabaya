package com.aldoapps.suararakyat;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.parse.Parse;

/**
 * Created by user on 03/11/2015.
 */
public class SuaraRakyatApplication extends Application {

    public static final String TAG = SuaraRakyatApplication.class.getSimpleName();
    private static SuaraRakyatApplication mApplication;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "AdceJKkt7p7M00NIB72GbtgQZOWkE4x13Mr8f3dk",
                "6hRqK43i9SJfhtcH5e70YYc0wrIztim4FlBP99V3");

        mApplication = this;
    }

    public static synchronized SuaraRakyatApplication getInstance(){
        return mApplication;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelAllPendingRequest(){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(TAG);
        }
    }
}
