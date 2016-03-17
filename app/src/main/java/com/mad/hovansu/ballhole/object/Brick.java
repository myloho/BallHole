package com.mad.hovansu.ballhole.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mad.hovansu.ballhole.manager.DrawBitmap;

import java.util.ArrayList;

public class Brick extends GameObject {

    public Brick(float x, float y, float width, float height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bitmap = Bitmap.createScaledBitmap(DrawBitmap.brick, (int) getWidth(), (int) getHeight(), false);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), paint);
    }
}