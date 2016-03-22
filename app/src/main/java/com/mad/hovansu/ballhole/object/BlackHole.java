package com.mad.hovansu.ballhole.object;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

import com.mad.hovansu.ballhole.manager.DrawBitmap;

import java.util.Random;

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
        bitmap = Bitmap.createScaledBitmap(DrawBitmap.blackhole, (int) getWidth(), (int) getHeight(), false);
    }

    public void move() {

        Random random = new Random();
        int firstNumber = random.nextInt(1000);
        if (firstNumber >= 0 && firstNumber < 25){
            setX(random.nextInt(DrawBitmap.width / 2));
            setY(random.nextInt((DrawBitmap.height-200)/2)+200);
        }
        if (firstNumber >= 25 && firstNumber < 50){
            setX(random.nextInt((DrawBitmap.width / 2)+DrawBitmap.width/2));
            setY(random.nextInt((DrawBitmap.height-200)/2)+200);
        }
//        if (firstNumber >= 50 && firstNumber < 75){
//            setX(random.nextInt(DrawBitmap.width / 2));
//            setY(random.nextInt((DrawBitmap.height-200)/2)+(DrawBitmap.height+200)/2);
//        }
//        if (firstNumber >= 75 && firstNumber < 100){
//            setX(random.nextInt((DrawBitmap.width / 2)+DrawBitmap.width/2));
//            setY(random.nextInt((DrawBitmap.height-200)/2)+(DrawBitmap.height+200)/2);
//        }
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
