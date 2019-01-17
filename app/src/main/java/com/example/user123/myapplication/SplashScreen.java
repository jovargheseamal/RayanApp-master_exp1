package com.example.user123.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {
    private ImageView splash,txt;
    private Boolean isChecked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splash = (ImageView) findViewById(R.id.rayan_logo);
        txt = (ImageView) findViewById(R.id.logotxt);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransitions);
        //splash.startAnimation(myanim);
        txt.startAnimation(myanim);

        SharedPreferences settings1 = getSharedPreferences("PREFS_NAME", 0);
        isChecked = settings1.getBoolean("isChecked", false);
        Log.e("boollllll", "" + isChecked);

        if (isChecked) {
            final Intent i = new Intent(this,HomeActivity.class);
            Thread timer =new Thread(){
                public void run() {
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        }

        else {
            final Intent i = new Intent(this,LoginActivity.class);
            Thread timer =new Thread(){
                public void run() {
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        }


    }
}
