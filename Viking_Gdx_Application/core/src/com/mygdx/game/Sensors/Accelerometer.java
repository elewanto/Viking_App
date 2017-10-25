package com.mygdx.game.Sensors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


/**
 * Created by Eric on 11/3/2016.
 */

// this uses libGdx accelerometer implementation

public class Accelerometer {

    private int orientation;

    // check if accelerometer is available on device
    public boolean isAvailable() {
        return Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    public boolean isAccelerating() {

        // vibrate for x milliseconds
        Gdx.input.vibrate(20);

        return true;
    }

}
