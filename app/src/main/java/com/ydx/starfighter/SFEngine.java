package com.ydx.starfighter;

import android.view.View;

/**
 * Created by Administrator on 2016/11/10.
 */
public class SFEngine {
    /*Constants*/
    public static final int GAME_THREAD_DELAY = 4000;
    public static final int MENU_BUTTON_ALPHA = 0; //启动按钮的透明度
    public static final boolean HAPTIC_BUTTON_FEEDBACK =true ; // 按钮的触感反馈


    /**
     *  Kill game and exit
     * @param v
     * @return
     */
    public boolean  onExit(View v){
        try{
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
