package com.vvv.fishshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Fish {
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

        if (x < (float) fishSprite.getWidth() / 2) {
            x = (float) fishSprite.getWidth() / 2;
        } else if (x > screenWidth - (float) fishSprite.getWidth() / 2) {
            x = screenWidth - (float) fishSprite.getWidth() / 2;
        }

        if (y < (float) fishSprite.getHeight() / 2) {
            y = (float) fishSprite.getHeight() / 2;
        } else if (y > screenHeight - (float) fishSprite.getHeight() / 2) {
            y = screenHeight - (float) fishSprite.getHeight() / 2;
        }

        speedX = initialSpeedX;
        speedY = initialSpeedY;
    }

    public void update() {
        x += speedX;
        y += speedY;

        if (x < (float) fishSprite.getWidth() / 2 || x > screenWidth - (float) fishSprite.getWidth() / 2) {
            speedX = -speedX;
        }

        if (y < (float) fishSprite.getHeight() / 2 || y > screenHeight - (float) fishSprite.getHeight() / 2) {
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
        float fishLeft = x - (float) fishSprite.getWidth() / 2;
        float fishRight = x + (float) fishSprite.getWidth() / 2;
        float fishTop = y - (float) fishSprite.getHeight() / 2;
        float fishBottom = y + (float) fishSprite.getHeight() / 2;

        return bulletX >= fishLeft && bulletX <= fishRight && bulletY >= fishTop && bulletY <= fishBottom;
    }

}

