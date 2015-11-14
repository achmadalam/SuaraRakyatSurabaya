package com.aldoapps.suararakyat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;

/**
 * Created by user on 11/11/2015.
 */
public class DispatchActivity extends AppCompatActivity {

    /**
     * Control class to determine whether the user has logged in or not
     * No view provided.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ParseUser.getCurrentUser() != null){
            Intent intent1 = new Intent(this, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(new Intent(this, MainActivity.class));
        }else{
            Intent intent2 = new Intent(this, LoginActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    public DispatchActivity(){ }
}
