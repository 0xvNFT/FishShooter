package com.vvv.fishshooter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class FishView extends View {
    private FishManager fishManager;
    private final Crosshair crosshair;
    private boolean isCrosshairVisible;
    private final Bullet bullet;
    private float releaseTouchX;
    private float releaseTouchY;

    public FishView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isCrosshairVisible = false;
        crosshair = new Crosshair(BitmapFactory.decodeResource(getResources(), R.drawable.crosshair));
        bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullet));

    }

    public void setFishManager(FishManager fishManager) {
        this.fishManager = fishManager;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        fishManager.drawAll(canvas);

        canvas.drawColor(Color.TRANSPARENT);
        if (isCrosshairVisible) {
            crosshair.draw(canvas);
        }
        if (bullet != null && bullet.isActive()) {
            bullet.draw(canvas);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float touchX = event.getX();
        float touchY = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                isCrosshairVisible = true;
                crosshair.updatePosition(touchX, touchY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isCrosshairVisible = false;
                releaseTouchX = event.getX();
                releaseTouchY = event.getY();

                if (bullet != null) {
                    float bulletStartX = getWidth() / 2;
                    float bulletStartY = getHeight();
                    float bulletSpeedY = 10;

                    float bulletSpeedX = (releaseTouchX - bulletStartX) * bulletSpeedY / (bulletStartY - releaseTouchY);

                    bullet.activate(bulletStartX, bulletStartY, bulletSpeedX, bulletSpeedY);
                }
                performClick();
                invalidate();
                break;
        }
        return true;
    }
    public void updateBullet() {
        if (bullet != null && bullet.isActive()) {
            bullet.update();
            fishManager.checkBulletCollisions(bullet);
            fishManager.removeInactiveFish();

            if (!bullet.isActive()) {
                releaseTouchX = 0;
                releaseTouchY = 0;
            }
        }
    }
    @Override
    public boolean performClick() {
        Log.d("FishView", "Performing click");
        return super.performClick();
    }

}
