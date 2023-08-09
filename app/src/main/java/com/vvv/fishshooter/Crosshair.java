package com.vvv.fishshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Crosshair {
    private final Bitmap crosshairImage;
    private float x, y;

    public Crosshair(Bitmap crosshairImage) {
        this.crosshairImage = crosshairImage;
        this.x = 0;
        this.y = 0;
    }

    public void updatePosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(crosshairImage, x - crosshairImage.getWidth() / 2, y - crosshairImage.getHeight() / 2, null);
    }
}

