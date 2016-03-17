package com.mad.hovansu.ballhole.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mad.hovansu.ballhole.GameThread;
import com.mad.hovansu.ballhole.MainActivity;
import com.mad.hovansu.ballhole.manager.DrawBitmap;
import com.mad.hovansu.ballhole.object.Ball;
import com.mad.hovansu.ballhole.object.BlackHole;
import com.mad.hovansu.ballhole.object.Brick;
import com.mad.hovansu.ballhole.object.MoveBox;

import java.util.ArrayList;

public class MainGameView extends SurfaceView {
    private SurfaceHolder surfaceHolder;
    private GameThread gameThread;
    Ball ball;
    BlackHole blackHole;
    MoveBox moveBoxTop;
    ArrayList<Brick> brickList;
    ArrayList<Brick> disableBrickList;
    Bitmap background;
    Paint paint = new Paint();

    public MainGameView(Context context, AttributeSet attributes) {
        super(context, attributes);
        ball = new Ball(DrawBitmap.width / 2, 240, 15);
        moveBoxTop = new MoveBox(DrawBitmap.width / 2, 200, 150, 20);
        blackHole = new BlackHole(DrawBitmap.width / 4, 400, 50, 50);
        brickList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <7 ; j++){
                int t = 0;
                if (j%2==1) t=50;
                Brick b = new Brick((i * DrawBitmap.width / 10)-t, DrawBitmap.height-50*j, DrawBitmap.width / 10, 50);
                brickList.add(b);
            }
        }

        disableBrickList = new ArrayList<>();

        background = DrawBitmap.background;

        gameThread = new GameThread(this);
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                gameThread.setRunning(false);
                try {
                    gameThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        brickList.removeAll(disableBrickList);
        disableBrickList.clear();

        canvas.drawBitmap(background, 0, 0, paint);
        moveBoxTop.drawBitmap(canvas);
        blackHole.drawBitmap(canvas);
        ball.move();
        blackHole.move(this);
        ball.drawBitmap(canvas);
        for (Brick b : brickList) {
            b.drawBitmap(canvas);
            if (ball.checkCollision(b)) {
                disableBrickList.add(b);
            }
        }

    }

    public void update() {
        if (ball.checkCollisionBackground(this)) {
            MainActivity.soundManager.playHit();
        }
        if (ball.checkCollision(moveBoxTop)) {
            MainActivity.soundManager.playHit();
            if (Math.abs(moveBoxTop.getDeltaX()) > Math.abs(ball.getVelocityX())) {
                ball.setX(ball.getX() + moveBoxTop.getDeltaX());
            }
        }
        if (ball.checkCollision(blackHole)) {
            MainActivity.soundManager.playHit();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        moveBoxTop.onTouchEvent(event);
        return true;
    }
}
