package com.kdao.realtor_meet_tinder;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                //nothing
                ImageView imageView = (ImageView) findViewById(R.id.image_logo);
                Animation pulse = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.pulse);
                imageView.startAnimation(pulse);
            }
            public void onFinish() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }
}