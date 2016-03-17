package com.mad.hovansu.ballhole.object;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

import com.mad.hovansu.ballhole.manager.AssetLoader;

public class BlackHole extends GameObject {
    private float speedX;

    public BlackHole(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLUE);
        speedX = 5;
        bitmap = Bitmap.createScaledBitmap(AssetLoader.blackhole, (int) getWidth(), (int) getHeight(), false);
    }

    public void move(View view) {
        x = x + speedX;
        if (x + speedX > view.getWidth() + 10 || x + speedX < -10) setSpeedX(-getSpeedX());
    }

    public boolean inArea(PointF p) {
        if (p.x >= x && p.x <= x + width && p.y >= y && p.y <= y + height) {
            return true;
        }
        return false;
    }

    public void setX(float x) {
        this.x = x;
    }


    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}
