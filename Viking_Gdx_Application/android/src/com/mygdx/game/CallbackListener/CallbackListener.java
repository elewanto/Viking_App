package com.mygdx.game.CallbackListener;

/**
 * Created by Master McCord on 11/2/2016.
 */

public interface CallbackListener {

    // This interface is needed so that the game and the activity can communicate. 
    public interface MyCallbackListener {
        void sendData(int data);
    }
}
