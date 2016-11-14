package com.ydx.starfighter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/10.
 */
public class SFMainMenu extends Activity {
    ImageView start;
    ImageView stop;
    final SFEngine sfEngine = new SFEngine();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        createMusicThread();
    }

    private  void findView(){
        start = ((ImageView) findViewById(R.id.iv_start));
        stop = ((ImageView) findViewById(R.id.iv_exit));
        setMenuBtnOptions();
        setClickListener();
    }

    private void setMenuBtnOptions(){
        start.getBackground().setAlpha(SFEngine.MENU_BUTTON_ALPHA);
        start.setHapticFeedbackEnabled(SFEngine.HAPTIC_BUTTON_FEEDBACK);

        stop.getBackground().setAlpha(SFEngine.MENU_BUTTON_ALPHA);
        stop.setHapticFeedbackEnabled(SFEngine.HAPTIC_BUTTON_FEEDBACK);
    }

    private void setClickListener(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* start game*/
//                Intent intent = new Intent(SFMainMenu.this,SFGame.class);
//                startActivity(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* stop game */
                boolean clean = false;
                clean = sfEngine.onExit(view);
                if(clean){
                    android.os.Process.killProcess(Process.myPid());
                }
            }
        });
    }

    private void createMusicThread(){
        SFEngine.musicThread = new Thread(){
            @Override
            public void run() {
                Intent bgmusic = new Intent(getApplicationContext(),SFMusic.class);
                startService(bgmusic);
                SFEngine.context = getApplicationContext();
            }
        };
        SFEngine.musicThread.start();
    }

}
