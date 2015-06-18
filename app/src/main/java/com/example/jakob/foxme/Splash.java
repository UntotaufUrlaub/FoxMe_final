package com.example.jakob.foxme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.jakob.foxme.Backend.MainController;

/**
 * Created by Andi on 17.06.2015.
 */
public class Splash extends Activity {
    private static int zeit = 4000;

    protected void onCreate(Bundle savedInstanceState) {
        new MainController().execute("Select");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        }, zeit);
    }
}
