package com.mygdx.game.Interfaces;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.geom.Rectangle2D;

/**
 * Created by Master McCord on 10/21/2016.
 */

public interface ISprite{


        //This method is used to create the new sprite image and place it on the map
        void create(int x, int y);

        //This method is used to render the new sprite and draw it on the screen.
        void render(OrthographicCamera camera ,SpriteBatch batch);

        //Call this method when you are done with the sprite.
        void dispose();

        //Use this method to get the collision rectangle on the sprite
        Rectangle getRectangle();

        void registerCollision();



}
