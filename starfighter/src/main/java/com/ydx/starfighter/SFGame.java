package com.ydx.starfighter;


import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/11/14.
 */
public class SFGame  extends Activity{
    SFGameView sfGameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSFGameView();
        setContentView(sfGameView);
    }

    private void initSFGameView(){
        sfGameView = new SFGameView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sfGameView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sfGameView.onPause();
    }
}
