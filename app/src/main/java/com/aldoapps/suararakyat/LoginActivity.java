package com.aldoapps.suararakyat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by user on 11/11/2015.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.nama) EditText mNama;
    @Bind(R.id.no_ktp) EditText mNoKtp;
    @Bind(R.id.masuk) Button mMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasInternet()){
                    doLogin();
                }
            }
        });
    }

    private boolean hasInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void doLogin() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mendaftarkan, harap tunggu sebentar..");
        progressDialog.show();

        // First we try to log in
        ParseUser.logInInBackground(mNama.getText().toString(),
                mNoKtp.getText().toString(),
                new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        progressDialog.dismiss();
                        if (user == null) {
                            doRegister();
                            Toast.makeText(LoginActivity.this,
                                    "Mendaftarkan pengguna..",
                                    Toast.LENGTH_SHORT).show();
                            Log.d("asdf", "e: " + e.getMessage());
                        } else {
                            Intent intent = new Intent(LoginActivity.this, DispatchActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                }
        );
    }

    private void doRegister() {
        ParseUser user = new ParseUser();
        user.setUsername(mNama.getText().toString());
        user.setPassword(mNoKtp.getText().toString());
        user.put("no_ktp", mNoKtp.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    doLogin();
                }else{
                    Log.d("asdf", "error: " + e.getMessage());
                }

            }
        });
    }
}
