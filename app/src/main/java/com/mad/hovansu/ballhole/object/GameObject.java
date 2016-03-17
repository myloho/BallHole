package com.mad.hovansu.ballhole.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class GameObject {

    protected float x, y;
    protected float velocityX, velocityY;
    protected float width, height;
    protected Bitmap bitmap;
    protected Paint paint;

    public GameObject() {
        paint = new Paint();
    }

    public GameObject(float x, float y, float velocityX, float velocityY){
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        paint = new Paint();
    }

    public void drawBitmap(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public boolean inArea(PointF p) {
        return p.x >= x && p.x <= x + width && p.y >= y && p.y <= y + height;
    }

    public float getHeight() {
        return height;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public float getWidth() {
        return width;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
