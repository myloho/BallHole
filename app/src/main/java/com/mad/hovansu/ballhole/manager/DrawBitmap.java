package com.mad.hovansu.ballhole.manager;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;

import com.mad.hovansu.ballhole.R;

public class DrawBitmap {
    public static int width;
    public static int height;
    public static Resources resources;
    public static SoundManager soundManager;

    public static Bitmap background, menu, play;
    public static Bitmap ball, movebox, blackhole;
    public static Bitmap brick;

    public static void load(Display dis, Resources res){
        Point size = new Point();
        dis.getSize(size);
        width = size.x;
        height = size.y;
        resources = res;
        ball = BitmapFactory.decodeResource(resources, R.drawable.ball);
        movebox = BitmapFactory.decodeResource(resources, R.drawable.bat);
        background = BitmapFactory.decodeResource(resources, R.drawable.background);
        menu = BitmapFactory.decodeResource(resources, R.drawable.menu);
        play = BitmapFactory.decodeResource(resources, R.drawable.play);
        brick = BitmapFactory.decodeResource(resources, R.drawable.brick);
        blackhole = BitmapFactory.decodeResource(resources, R.drawable.blackhole);

        //Scale
        background = Bitmap.createScaledBitmap(background, width, height, false);
        menu = Bitmap.createScaledBitmap(menu, width, height, false);

    }
}
