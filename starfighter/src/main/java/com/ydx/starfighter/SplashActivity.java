package com.ydx.starfighter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        new Handler().postDelayed(new Thread(){
            @Override
            public void run() {
                Intent mainMenu = new Intent(SplashActivity.this,SFMainMenu.class);
                SplashActivity.this.startActivity(mainMenu);
                SplashActivity.this.finish();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        },SFEngine.GAME_THREAD_DELAY);
    }
}
