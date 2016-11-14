package com.ydx.starfighter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by Administrator on 2016/11/10.
 */
public class SFEngine {
    /*Constants*/
    public static final int GAME_THREAD_DELAY = 4000;
    public static final int MENU_BUTTON_ALPHA = 0; //启动按钮的透明度
    public static final boolean HAPTIC_BUTTON_FEEDBACK =true ; // 按钮的触感反馈
    public static final int SPLASH_SCREEN_MUSIC = R.raw.warfieldedit;
    public static final int R_VOLUME = 100;
    public static final int L_VOLUME = 100;
    public static final boolean LOOP_BACKGROUND_MUSIC = true ;
    public static  Context context ;
    public static Thread musicThread;
    public static final int BACKGROUND_LAYER_ONE = R.drawable.backgroundstars;
    /**
     *  Kill game and exit
     * @param v
     * @return
     */
    public boolean  onExit(View v){
        try{
            Intent bgmusic = new Intent(context,SFMusic.class);
            context.stopService(bgmusic);
            if(musicThread!=null){
                musicThread.interrupt();
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
