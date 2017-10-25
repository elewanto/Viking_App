package com.mygdx.game.Accelerometer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Eric on 11/5/2016.
 */

public class Accelerometer {

    private float accelX = 0;
    private float accelY = 0;
    private float accelZ = 0;
    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    private float lastX = 0;
    private float lastY = 0;
    private float lastZ = 0;
    private long lastTimeChecked = 0;
    private long currentTime = 0;
    private boolean wasShaking = false;
    private int saturateCount = 0;
    private int shakeCount = 0;

    private static final float SHAKE_THRESHOLD = 15;
    private static final int SHAKE_COUNT_THRESHOLD = 20;

    public boolean isShaking() {

        currentTime = TimeUtils.millis();
        if (currentTime - lastTimeChecked > 100) {      // check accelerometer data at greater intervals than 100ms, otherwise return previous state
            lastTimeChecked = currentTime;
            accelX = Gdx.input.getAccelerometerX();
            accelY = Gdx.input.getAccelerometerY();
            accelZ = Gdx.input.getAccelerometerZ();
            deltaX = Math.abs(accelX - lastX);
            deltaY = Math.abs(accelY - lastY);
            deltaZ = Math.abs(accelZ - lastZ);
            lastX = accelX;
            lastY = accelY;
            if (deltaX > SHAKE_THRESHOLD || deltaY > SHAKE_THRESHOLD || deltaZ > SHAKE_THRESHOLD) {
                wasShaking = true;
                saturateCount = 1;
                shakeCount++;
            } else {
                if (saturateCount == 1) {
                    wasShaking = true;
                    saturateCount = 0;
                } else {
                    wasShaking = false;
                }
            }
            wasShaking = (deltaX > SHAKE_THRESHOLD || deltaY > SHAKE_THRESHOLD || deltaZ > SHAKE_THRESHOLD);
        }
        return wasShaking;
    }

    public boolean winShaking() {
        return (shakeCount >= SHAKE_COUNT_THRESHOLD);
    }

}
