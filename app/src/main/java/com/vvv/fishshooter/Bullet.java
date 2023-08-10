package com.vvv.fishshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bullet {
    private final Bitmap bulletBitmap;
    private float x, y;
    private float speedX;
    private float speedY;
    private boolean isActive;

    public Bullet(Bitmap bulletBitmap) {
        this.bulletBitmap = bulletBitmap;
        this.isActive = false;
    }

    public void activate(float startX, float startY, float speedX, float speedY) {
        this.x = startX;
        this.y = startY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.isActive = true;
    }

    public void update() {
        if (isActive) {
            x += speedX;
            y -= speedY;
            if (y < 0) {
                isActive = false;
            }
        }
    }

    public void draw(Canvas canvas) {
        if (isActive) {
            canvas.drawBitmap(bulletBitmap, x - bulletBitmap.getWidth() / 2, y, null);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

