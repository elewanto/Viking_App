package com.mygdx.game.CollisionDetector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ArrayMap;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.ISprite;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Master McCord on 10/28/2016.
 */

public class CollisionDetector {





    public void update(ArrayList spriteList){




            ISprite vikingSprite = (ISprite) spriteList.get(0);
            for (int j = 1; j < spriteList.size() ; j++){


                    ISprite sprite = (ISprite) spriteList.get(j);

                    if(vikingSprite.getRectangle().overlaps(sprite.getRectangle())){
                        /// A COLLISION WAS DETECTED!!!
                       // Gdx.app.log("COLLISION", "IT WAS DETECTED");
                        //vikingSprite.registerCollision();
                        sprite.registerCollision();

                    }

                }
            }


    }


