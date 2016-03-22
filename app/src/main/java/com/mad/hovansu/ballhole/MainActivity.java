package com.mad.hovansu.ballhole;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ViewFlipper;

import com.mad.hovansu.ballhole.manager.DrawBitmap;
import com.mad.hovansu.ballhole.manager.SoundManager;

public class MainActivity extends Activity {

    public static Resources resources;
    public static Display display;
    public static SoundManager soundManager;
    public static ViewFlipper viewFlipper;
    private View viewGame;
    private View viewMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Turn Title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Get display
        display = getWindowManager().getDefaultDisplay();
        // Get resources
        resources = getResources();

        DrawBitmap.load(getWindowManager().getDefaultDisplay(), getResources());
        setContentView(R.layout.activity_main);

        soundManager = new SoundManager(this);

        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        viewGame = (View) findViewById(R.id.game);
        viewMenu = (View) findViewById(R.id.menu);

        viewMenu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (x >= (display.getWidth() - DrawBitmap.play.getWidth()) / 2
                                && x <= (display.getWidth() + DrawBitmap.play.getWidth()) / 2
                                && y >= (display.getHeight() - DrawBitmap.play.getHeight()) / 2 + 200
                                && y <= (display.getHeight() + DrawBitmap.play.getHeight()) / 2 + 200) {
                            viewFlipper.showNext();
//                            viewFlipper.setDisplayedChild( viewFlipper.indexOfChild(findViewById(R.id.third)) );
                        }
                }
                return true;
            }
        });
    }
}
