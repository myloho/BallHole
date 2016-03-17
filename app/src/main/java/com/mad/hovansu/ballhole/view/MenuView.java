package com.mad.hovansu.ballhole.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.mad.hovansu.ballhole.manager.AssetLoader;

public class MenuView extends View {

    Bitmap menu, play;
    Paint paint;

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        menu = Bitmap.createScaledBitmap(AssetLoader.menu, AssetLoader.width, AssetLoader.height, false);
        play = AssetLoader.play;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(menu, 0, 0, paint);
        canvas.drawBitmap(play, (AssetLoader.width - play.getWidth()) / 2,
                (AssetLoader.height - play.getHeight()) / 2, paint);
    }
}