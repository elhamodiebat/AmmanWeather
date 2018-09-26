package com.odiebat.elham.amman;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView imageView;
    Animation animFadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        imageView = (ImageView) findViewById(R.id.logo);

        // load the animation
        animFadein = AnimationUtils.loadAnimation(Splash.this, R.anim.anim);
        imageView.startAnimation(animFadein);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(intent);
                Splash.this.finish();
                ;
            }
        }, 3000);
    }
}
