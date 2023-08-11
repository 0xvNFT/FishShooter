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

        fishManager = new FishManager();
        float startX1 = 605;
        float startY1 = (float) screenHeight / 2;
        float initialSpeedX1 = 2;
        float initialSpeedY1 = 0;

        float startX2 = 365;
        float startY2 = (float) screenHeight / 2;
        float initialSpeedX2 = 3;
        float initialSpeedY2 = 1;

        float startX3 = 800;
        float startY3 = (float) screenHeight / 2;
        float initialSpeedX3 = 3;
        float initialSpeedY3 = 1;

        float startX4 = 752;
        float startY4 = (float) screenHeight / 2;
        float initialSpeedX4 = 3;
        float initialSpeedY4 = 1;

        float startX5 = 632;
        float startY5 = (float) screenHeight / 2;
        float initialSpeedX5 = 3;
        float initialSpeedY5 = 1;

        float startX6 = 245;
        float startY6 = (float) screenHeight / 2;
        float initialSpeedX6 = 3;
        float initialSpeedY6 = 1;

        fishManager.addFish(new Fish(fishSprites[0], startX1, startY1, initialSpeedX1, initialSpeedY1, screenWidth, screenHeight));
        fishManager.addFish(new Fish(fishSprites[1], startX2, startY2, initialSpeedX2, initialSpeedY2, screenWidth, screenHeight));
        fishManager.addFish(new Fish(fishSprites[2], startX3, startY3, initialSpeedX3, initialSpeedY3, screenWidth, screenHeight));
        fishManager.addFish(new Fish(fishSprites[3], startX4, startY4, initialSpeedX4, initialSpeedY4, screenWidth, screenHeight));
        fishManager.addFish(new Fish(fishSprites[4], startX5, startY5, initialSpeedX5, initialSpeedY5, screenWidth, screenHeight));
        fishManager.addFish(new Fish(fishSprites[5], startX6, startY6, initialSpeedX6, initialSpeedY6, screenWidth, screenHeight));

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

