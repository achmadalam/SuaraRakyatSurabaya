package com.aldoapps.suararakyat;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by user on 03/11/2015.
 */
public class SuaraRakyatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "AdceJKkt7p7M00NIB72GbtgQZOWkE4x13Mr8f3dk",
                "6hRqK43i9SJfhtcH5e70YYc0wrIztim4FlBP99V3");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

}
