package com.ydx.starfighter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/11/14.
 */
public class SFMusic extends Service {
    public static boolean isRunning = false ;
    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        setMusicOptions(this,SFEngine.LOOP_BACKGROUND_MUSIC,SFEngine.R_VOLUME,SFEngine.L_VOLUME,SFEngine.SPLASH_SCREEN_MUSIC);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            player.start();
            isRunning = true ;

        }catch (Exception e){
            isRunning = false;
            player.stop();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {
        player.stop();
    }


    private void setMusicOptions(Context context,boolean isLooped,int rVolume,int lVolume,int soundFile){
        player = MediaPlayer.create(context,soundFile);
        player.setLooping(isLooped);
        player.setVolume(lVolume,rVolume);
    }
}
