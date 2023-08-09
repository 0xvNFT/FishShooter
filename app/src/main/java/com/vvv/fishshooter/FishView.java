package com.vvv.fishshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class FishView extends View {
    private FishManager fishManager;

    public FishView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFishManager(FishManager fishManager) {
        this.fishManager = fishManager;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.TRANSPARENT);

        fishManager.drawAll(canvas);
    }
}
