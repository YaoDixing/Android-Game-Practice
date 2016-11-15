package com.ydx.starfighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2016/11/14.
 */
public class SFBackground {

    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;
    private ByteBuffer indexBuffer;


    private int[] textures = new int[1];

    /**
     *在三维 坐标系中的 xoy面 的一个正方形 的4个顶点
     * 在三维坐标系中画图就明白了
     */
    private float vertices[] = {
            0.0f,0.0f,0.0f,
            1.0f,0.0f,0.0f,
            1.0f,1.0f,0.0f,
            0.0f,1.0f,0.0f
    };

    /**
     * 二维坐标系中的 正方形 的4个顶点
     */
    private float texture[] = {
            0.0f,0.0f,
            1.0f,0f,
            1.0f,1.0f,
            0f,1.0f
    };

    private byte indices [] = {
            0,1,2,
            0,2,3
    };

    public SFBackground() {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        byteBuffer = ByteBuffer.allocateDirect(texture.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuffer.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);

        indexBuffer = ByteBuffer.allocate(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    public void loadTexture(GL10 gl, int texture, Context context){
        InputStream imgstream = context.getResources().openRawResource(texture);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(imgstream);
        }catch (Exception e){

        }finally {
            try {
                imgstream.close();
                imgstream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /* 生成纹理 */
        gl.glGenTextures(1,textures,0);

        /* 绑定纹理 */
        gl.glBindTexture(GL10.GL_TEXTURE_2D,textures[0]);

        /* 将纹理映射到顶点上 */
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR);

        /* 沿 S和T方向不断重复背景纹理 */
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_WRAP_S,GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_WRAP_T,GL10.GL_REPEAT);

        /* 将位图输入流关联到第一个纹理上 */
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0,bitmap,0);
        bitmap.recycle();
    }

    public void draw(GL10 gl){
        /* 绑定纹理 */
        gl.glBindTexture(GL10.GL_TEXTURE_2D,textures[0]);
        /* 忽略不在表变的任何顶点 */
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        /* 开启顶点与纹理状态 */
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        /* 将顶点与纹理缓冲区加载到OpenGL中 */
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);
        gl.glTexCoordPointer(2,GL10.GL_FLOAT,0,textureBuffer);
        /* 纹理被绘制到顶点上 */
        gl.glDrawElements(GL10.GL_TRIANGLES,indices.length,GL10.GL_UNSIGNED_BYTE,indexBuffer);
        /* 禁用所有已启用的状态 */
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisableClientState(GL10.GL_CULL_FACE);
    }
}
