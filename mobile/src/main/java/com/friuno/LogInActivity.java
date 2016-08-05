package com.friuno;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

/**
 * Created by GodwinRoseSamuel on 17-07-2016.
 */
public class LogInActivity extends Activity {
    private static final String TAG = "LogInActivity";
    private SignInButton googleSignIn;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        googleSignIn = (SignInButton) findViewById(R.id.googleSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginThread().execute();
            }
        });

        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Not Available for Demo!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class LoginThread extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LogInActivity.this);
            progressDialog.setMessage("Logging in....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
