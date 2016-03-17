package com.mad.hovansu.ballhole.object;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.mad.hovansu.ballhole.manager.AssetLoader;

public class MoveBox extends GameObject {

    private float previousX;
    private float deltaX = 0;

    public MoveBox(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);

        previousX = x;
        bitmap = Bitmap.createScaledBitmap(AssetLoader.movebox, (int) getWidth(), (int) getHeight(), false);
    }

    public MoveBox(MoveBox moveBox) {
        x = moveBox.getX();
        y = moveBox.getY();
        width = moveBox.getWidth();
        height = moveBox.getHeight();
    }

    public void onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                deltaX = currentX - previousX;
                if (getX() + getWidth() + deltaX < AssetLoader.width && getX() + deltaX > 0)
                    setX(getX() + deltaX);
                break;
        }
        previousX = currentX;
    }

    public boolean inArea(PointF p) {
        if (p.x >= x && p.x <= x + getWidth() && p.y >= y && p.y <= y + getHeight()) {
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


    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }
}
