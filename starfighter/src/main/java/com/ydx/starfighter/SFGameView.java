package com.ydx.starfighter;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/11/14.
 */
public class SFGameView extends GLSurfaceView {
    public SFGameView(Context context) {
        this(context,null);
    }

    public SFGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
