package com.vvv.fishshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Fish {
    public int currentSpriteIndex;
    private final Bitmap fishSprite;
    private float x, y;
    private float speedX, speedY;
    private final int screenWidth;
    private final int screenHeight;
    public boolean isActive;

    public Fish(Bitmap fishSprite, float startX, float startY, float initialSpeedX, float initialSpeedY,
                int screenWidth, int screenHeight) {
        this.fishSprite = fishSprite;
        this.x = startX;
        this.y = startY;
        this.speedX = initialSpeedX;
        this.speedY = initialSpeedY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.isActive = true;

        Random random = new Random();
        speedX = initialSpeedX * (random.nextFloat() + 0.5f);
        speedY = initialSpeedY * (random.nextFloat() + 0.5f);

        x = random.nextInt(screenWidth);
        y = random.nextInt(screenHeight);
    }

    public void update() {
        x += speedX;
        y += speedY;

        if (x < 0 || x + fishSprite.getWidth() > screenWidth) {
            speedX = -speedX;
        }
        if (y < 0 || y + fishSprite.getHeight() > screenHeight) {
            speedY = -speedY;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(fishSprite, x, y, null);
    }

    public boolean isHit(float bulletX, float bulletY) {
        float fishLeft = x - fishSprite.getWidth() / 2;
        float fishRight = x + fishSprite.getWidth() / 2;
        float fishTop = y - fishSprite.getHeight() / 2;
        float fishBottom = y + fishSprite.getHeight() / 2;

        return bulletX >= fishLeft && bulletX <= fishRight && bulletY >= fishTop && bulletY <= fishBottom;
    }

}

