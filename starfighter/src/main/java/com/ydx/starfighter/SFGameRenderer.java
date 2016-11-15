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
    private SFBackground background2 = new SFBackground();
    private float bgScroll1;
    private float bgScroll2;

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

        background2.loadTexture(gl10,SFEngine.BACKGROUND_LAYER_TWO,SFEngine.context);
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
        try{
            Thread.sleep(SFEngine.GAME_THREAD_FPS_SLEEP);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        scrollBackground1(gl10);
        scrollBackground2(gl10);

        gl10.glEnable(GL10.GL_BLEND);
        gl10.glBlendFunc(GL10.GL_ONE,GL10.GL_ONE);
    }

    private void scrollBackground1(GL10 gl){
        if(bgScroll1 ==Float.MAX_VALUE){
            bgScroll1 = 0f;
        }

        /*  */
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();
        gl.glScalef(1f,1f,1f);
        gl.glTranslatef(0f,0f,0f);

        //移动纹理
        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,bgScroll1,0.0f);

        background.draw(gl);
        gl.glPopMatrix();
        bgScroll1 += SFEngine.SCROLL_BACKGROUND_1;
        gl.glLoadIdentity();
    }

    private void scrollBackground2(GL10 gl){
        if(bgScroll2 == Float.MAX_VALUE){
            bgScroll2 = 0f;
        }
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();
        gl.glScalef(.5f,1f,1f);
        gl.glTranslatef(1.5f,0f,0f);

        //移动纹理
        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,bgScroll2,0.0f);

        background2.draw(gl);
        gl.glPopMatrix();
        bgScroll2 += SFEngine.SCROLL_BACKGROUND_2;
        gl.glLoadIdentity();
    }
}
