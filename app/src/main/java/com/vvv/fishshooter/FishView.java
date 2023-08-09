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

    public FishView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isCrosshairVisible = false;
        crosshair = new Crosshair(BitmapFactory.decodeResource(getResources(), R.drawable.crosshair));
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
                performClick();
                invalidate();
                break;
        }

        return true;
    }

    @Override
    public boolean performClick() {
        Log.d("FishView", "Performing click");
        return super.performClick();
    }
}
