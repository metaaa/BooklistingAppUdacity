package com.example.android.booklistingappudacity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //THIS WILL EXECUTE AFTER 1500 MILLISECONDS
                startActivity(new Intent(SplashScreenActivity.this, BookActivity.class));
                //THIS CLOSES THE SPLASH ACTIVITY
                finish();
            }
        }, SPLASH_TIME);
    }
}
