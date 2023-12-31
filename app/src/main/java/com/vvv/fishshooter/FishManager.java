package com.vvv.fishshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FishManager {
    private final List<Fish> fishes = new ArrayList<>();
    private static final int MAX_FISH_COUNT = 6;
    private static final float INITIAL_FISH_SPEED_X = 5.0f;
    private static final float INITIAL_FISH_SPEED_Y = 5.0f;
    private final Random random = new Random();
    private final Bitmap[] fishSprites;
    private final int screenWidth;
    private final int screenHeight;
    private long respawnInterval = 2000;
    private long lastRespawnTime = 0;
    private int score = 0;


    public FishManager(Bitmap[] fishSprites, int screenWidth, int screenHeight) {
        this.fishSprites = fishSprites;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void setRespawnInterval(long interval) {
        this.respawnInterval = interval;
    }

    public void addFish(Fish fish) {
        fishes.add(fish);
    }

    public void updateAll() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRespawnTime >= respawnInterval) {
            respawnFish();
            lastRespawnTime = currentTime;
        }
        for (Fish fish : fishes) {
            fish.update();
        }
    }

    public void drawAll(Canvas canvas) {
        for (Fish fish : fishes) {
            fish.draw(canvas);
        }
    }

    private void respawnFish() {
        if (fishes.size() < MAX_FISH_COUNT) {
            float startX;
            float startY = random.nextInt(screenHeight);
            float initialSpeedX = (random.nextFloat() + 0.5f) * INITIAL_FISH_SPEED_X;
            float initialSpeedY = (random.nextFloat() + 0.5f) * INITIAL_FISH_SPEED_Y;
            Bitmap fishSprite;

            if (random.nextBoolean()) {

                fishSprite = fishSprites[random.nextInt(fishSprites.length)];
                startX = -fishSprite.getWidth();
                Matrix matrix = new Matrix();
                matrix.preScale(-1.0f, 1.0f);
                fishSprite = Bitmap.createBitmap(fishSprite, 0, 0, fishSprite.getWidth(), fishSprite.getHeight(), matrix, true);
            } else {

                fishSprite = fishSprites[random.nextInt(fishSprites.length)];
                startX = screenWidth;
                Matrix matrix = new Matrix();
                matrix.preScale(1.0f, 1.0f);
                fishSprite = Bitmap.createBitmap(fishSprite, 0, 0, fishSprite.getWidth(), fishSprite.getHeight(), matrix, true);
            }

            Fish newFish = new Fish(fishSprite, startX, startY, initialSpeedX, initialSpeedY, screenWidth, screenHeight);
            addFish(newFish);
        }
    }


    public void checkBulletCollisions(Bullet bullet) {
        for (Fish fish : fishes) {
            if (fish.isHit(bullet.getX(), bullet.getY())) {
                fish.setActive(false);
                bullet.setActive(false);
                increaseScore();
            }
        }
    }

    private void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void removeInactiveFish() {
        List<Fish> inactiveFish = new ArrayList<>();
        for (Fish fish : fishes) {
            if (!fish.isActive()) {
                inactiveFish.add(fish);
            }
        }
        fishes.removeAll(inactiveFish);
    }
}

