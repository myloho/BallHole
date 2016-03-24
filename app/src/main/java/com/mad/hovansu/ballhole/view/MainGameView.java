package com.mad.hovansu.ballhole.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mad.hovansu.ballhole.GameOverActivity;
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
    Brick brick;
    ArrayList<Brick> brickList;
    ArrayList<Brick> disableBrickList;
    Bitmap background;
    Paint paint = new Paint();

    public MainGameView(Context context, AttributeSet attributes) {
        super(context, attributes);
        ball = new Ball(DrawBitmap.width / 2, 240, 30);
        moveBoxTop = new MoveBox(DrawBitmap.width / 2, 200, 170, 20);
        blackHole = new BlackHole(DrawBitmap.width / 4, 400, 100, 100);
        brickList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <5 ; j++){
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
        blackHole.move();
        ball.drawBitmap(canvas);
        for (int i = 0; i < brickList.size(); i++){
            brick = brickList.get(i);
            brick.drawBitmap(canvas);
            if (ball.checkCollision(brick, i)) {
                disableBrickList.add(brick);
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
        if (ball.isDead()){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Context context = getContext();
                    Intent i = new Intent(context, GameOverActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    context.startActivity(i);
                }
            });
            t.start();
            /*Context context = getContext();
            Intent i = new Intent(context, GameOverActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(i);*/
           /* try {
                synchronized (context) {
                    context.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        moveBoxTop.onTouchEvent(event);
        return true;
    }
}
