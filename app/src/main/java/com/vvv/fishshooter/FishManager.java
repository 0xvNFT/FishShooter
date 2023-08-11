package com.vvv.fishshooter;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class FishManager {
    private final List<Fish> fishes = new ArrayList<>();
    public void addFish(Fish fish) {
        fishes.add(fish);
    }

    public void updateAll() {
        for (Fish fish : fishes) {
            fish.update();
        }
    }

    public void drawAll(Canvas canvas) {
        for (Fish fish : fishes) {
            fish.draw(canvas);
        }
    }

    public void checkBulletCollisions(Bullet bullet) {
        for (Fish fish : fishes) {
            if (fish.isHit(bullet.getX(), bullet.getY())) {
                fish.setActive(false);
                bullet.setActive(false);
            }
        }
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

