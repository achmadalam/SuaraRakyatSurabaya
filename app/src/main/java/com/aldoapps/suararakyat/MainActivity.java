package com.aldoapps.suararakyat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment mainFragment = (MainFragment)
                fragmentManager.findFragmentById(R.id.fragment_container);

        if(mainFragment == null){
            mainFragment = MainFragment.newInstance();

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, mainFragment)
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        finish();

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_log_out) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Keluar dari akun ini, harap tunggu sebentar..");
            progressDialog.show();

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(MainActivity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mTracker = ((SuaraRakyatApplication) getApplicationContext()).getDefaultTracker();

        Log.d("asdf", "Setting screen name: ");
        mTracker.setScreenName("MainActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
