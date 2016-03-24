package com.mad.hovansu.ballhole.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

import com.mad.hovansu.ballhole.manager.DrawBitmap;

import java.util.ArrayList;
import java.util.Random;

public class Ball extends GameObject{
    private float radius;
    private float speed;
    // Voi velocityY = velocity * cos(alpha)
    private double alpha;
    private int[] fastBallItem = {3, 10, 36, 22, 54};
    private int[] slowBallItem = {5, 12, 38, 20, 50};


    public Ball(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        speed = 20;
        alpha = Math.PI / 4.0;
        velocityX = (float) (speed * Math.sin(alpha));
        velocityY = (float) (speed * Math.cos(alpha));
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);

        bitmap = DrawBitmap.ball;
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (radius * 2), (int) (radius * 2), false);
    }

    public void move() {
        x = x + velocityX;
        y = y + velocityY;
    }

    public void drawBitmap(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - radius, y - radius, paint);
    }

    public boolean checkCollisionBackground(View view) {
        if (x + radius + velocityX > DrawBitmap.width || x - radius + velocityX < 0) {
            setVelocityX(-velocityX);
            return true;
        } else if (y + radius + velocityY > DrawBitmap.height ) {
            setVelocityY(-velocityY);
            return true;
        }
        return false;
    }

    public boolean isDead() {
        if (y - radius + velocityY < 0){
            return true;
        }
        return false;
    }

    public boolean checkCollision(Brick brick, int j) {
        boolean check = false;
        PointF p1 = new PointF(x + velocityX, y + radius + velocityY);
        PointF p2 = new PointF(x + velocityX, y - radius + velocityY);
        if (brick.inArea(p1) || brick.inArea(p2)) {
            for (int i = 0; i < 5; i++){
                if (j == fastBallItem[i] && getSpeed() < 30){
                    setSpeed(speed+10);
                }
                if (j == slowBallItem[i] && getSpeed() > 20 ){
                    setSpeed(speed-10);
                }
            }
            move();
            updateAlpha(Math.PI - alpha);
            check = true;
        }
        p1 = new PointF(x + radius + velocityX, y + velocityY);
        p2 = new PointF(x - radius + velocityX, y + velocityY);
        if (brick.inArea(p1) || brick.inArea(p2)) {
            for (int i = 0; i < 5; i++){
                if (j == fastBallItem[i] && getSpeed() < 30){
                    setSpeed(speed+10);
                }
                if (j == slowBallItem[i] && getSpeed() > 20 ){
                    setSpeed(speed-10);
                }
            }
            move();
            updateAlpha(-alpha);
            check = true;
        }
        return check;


    }

    public boolean checkCollision(BlackHole blackHole){
        boolean check = false;
        Random rand = new Random();
        PointF p1 = new PointF(x + velocityX, y + radius + velocityY);
        PointF p2 = new PointF(x + velocityX, y - radius + velocityY);
        if (blackHole.inArea(p1) || blackHole.inArea(p2)) {
            /*move();
            updateAlpha(Math.PI - alpha);*/
            int i = rand.nextInt(DrawBitmap.width - 50)+30;
            setX(i);
            int j = rand.nextInt(50) + 70;
            setY(j);
            if(velocityY < 0) setVelocityY(-velocityY);
            check = true;
        }
        p1 = new PointF(x + radius + velocityX, y + velocityY);
        p2 = new PointF(x - radius + velocityX, y + velocityY);
        if (blackHole.inArea(p1) || blackHole.inArea(p2)) {
            /*move();
            updateAlpha(-alpha);*/
            int i = rand.nextInt(DrawBitmap.width - 50)+30;
            setX(i);
            int j = rand.nextInt(50) + 50;
            setY(j);
            if (velocityY < 0) setVelocityY(-velocityY);
            check = true;
        }
        return check;
    }

    public boolean checkCollision(MoveBox moveBox) {
        boolean check = false;

        PointF p1 = new PointF(x + velocityX, y + radius + velocityY);
        PointF p2 = new PointF(x + velocityX, y - radius + velocityY);
        if (moveBox.inArea(p1) || moveBox.inArea(p2)) {
            move();

            double deltaX = (x - (moveBox.getX() + moveBox.getWidth() / 2));
            double sinAlpha = (deltaX * 1.9) / moveBox.getWidth();
            double changeAlpha = Math.asin(sinAlpha) + Math.PI / 18;
            Log.d("GOC ALPHA", "DeltaX : " + (float) deltaX + "--- Alpha : " + (float) (changeAlpha * 180 / Math.PI));
            updateAlpha(changeAlpha);
            check = true;
        }
        p1 = new PointF(x + radius + velocityX, y + velocityY);
        p2 = new PointF(x - radius + velocityX, y + velocityY);
        if (moveBox.inArea(p1) || moveBox.inArea(p2)) {
            move();
            double deltaX = (x - (moveBox.getX() + moveBox.getWidth() / 2));
            double sinAlpha = (deltaX * 1.9) / moveBox.getWidth();
            double changeAlpha = Math.asin(sinAlpha) + Math.PI / 18;
            Log.d("GOC ALPHA", "DeltaX : " + (float) deltaX + "--- Alpha : " + (float) (changeAlpha * 180 / Math.PI));
            updateAlpha(Math.PI - changeAlpha);
            check = true;
        }

        return check;
    }

    private void updateAlpha(double changeAlpha) {
        alpha = changeAlpha;
        velocityX = (float) (speed * Math.sin(alpha));
        velocityY = (float) (speed * Math.cos(alpha));
    }


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
}
