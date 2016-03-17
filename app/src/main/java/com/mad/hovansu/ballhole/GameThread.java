package com.mad.hovansu.ballhole;

import android.graphics.Canvas;

import com.mad.hovansu.ballhole.view.MainGameView;

public class GameThread extends Thread {

    private int fps = 60;
    private MainGameView view;
    private boolean running;

    public GameThread(MainGameView view) {
        this.view = view;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.update();
                    if (c != null) view.draw(c);
                }

            } finally {
                if (c != null) view.getHolder().unlockCanvasAndPost(c);
            }

            try {
                this.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

