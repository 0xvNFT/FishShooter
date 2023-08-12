package com.vvv.fishshooter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    FishView fishView;
    private FishManager fishManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Bitmap[] fishSprites = loadFishSprites();
        fishView = findViewById(R.id.fishView);

        fishManager = new FishManager(fishSprites, screenWidth, screenHeight);
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            float initialSpeedX = 3;
            float initialSpeedY = 1;
            float fishWidth = fishSprites[i].getWidth();
            float fishHeight = fishSprites[i].getHeight();

            float startX = random.nextInt(screenWidth - (int) fishWidth);
            float startY = random.nextInt(screenHeight - (int) fishHeight);

            fishManager.addFish(new Fish(fishSprites[i], startX, startY, initialSpeedX, initialSpeedY, screenWidth, screenHeight));
        }

        fishView.setFishManager(fishManager);
        startAnimationLoop();

    }

    private void startAnimationLoop() {
        Handler handler = new Handler();
        Runnable animationRunnable = new Runnable() {
            @Override
            public void run() {
                fishManager.updateAll();
                fishView.updateBullet();
                fishView.invalidate();
                handler.postDelayed(this, 16);
            }
        };
        handler.post(animationRunnable);
    }


    private Bitmap[] loadFishSprites() {
        Bitmap[] fishSprites = new Bitmap[6];

        fishSprites[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish_1);
        fishSprites[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish_2);
        fishSprites[2] = BitmapFactory.decodeResource(getResources(), R.drawable.fish_3);
        fishSprites[3] = BitmapFactory.decodeResource(getResources(), R.drawable.fish_4);
        fishSprites[4] = BitmapFactory.decodeResource(getResources(), R.drawable.fish_5);
        fishSprites[5] = BitmapFactory.decodeResource(getResources(), R.drawable.fish_6);

        return fishSprites;
    }

}

