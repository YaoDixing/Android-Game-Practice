package com.ydx.starfighter;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 渲染器
 * Created by Administrator on 2016/11/14.
 */
public class SFGameRenderer implements GLSurfaceView.Renderer{

    private SFBackground background = new SFBackground();

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        /* 启用 OpenGL 的 2D 纹理映射功能 */
        gl10.glEnable(GL10.GL_TEXTURE_2D);

        gl10.glClearDepthf(1.0f);
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        gl10.glDepthFunc(GL10.GL_LEQUAL);
        
        gl10.glEnable(GL10.GL_BLEND);
        gl10.glBlendFunc(GL10.GL_ONE,GL10.GL_ONE);

        background.loadTexture(gl10,SFEngine.BACKGROUND_LAYER_ONE,SFEngine.context);
        background.draw(gl10);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        gl10.glViewport(0,0,width,height);
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        /* 左右上下近远 */
        gl10.glOrthof(0f,1f,0f,1f,-1f,1f);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {

    }
}
