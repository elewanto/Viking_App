package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameLevels.MyGdxGame;

import java.awt.geom.Rectangle2D;

/**
 * Created by Master McCord on 10/21/2016.
 */

public class VikingSprite implements com.mygdx.game.Interfaces.ISprite {


    private final String TAG = "VikingSprite";

    private Texture vikingImage;			// viking sprite
    private Rectangle viking;				// define size of viking sprite



    Vector3 touchPos = new Vector3();		// 3-dimensional vector (used with Libgdx orthographic 3D camera)

    // set sprite dimensions
    private static final int VIKING_WIDTH = 150;
    private static final int VIKING_HEIGHT = 200;

    // animation definitions
    private static final int VIKING_FRAME_COLS = 6;
    private static final int VIKING_FRAME_ROWS = 6;
    private static final int NUM_RUN_FRAMES = 6;
    private static final float FRAME_DISPLAY_TIME = 0.1f;

    Animation idleAnimation;
    Animation runAnimation;
    Animation attackAnimation;
    Animation jumpAnimation;
    Animation swimAnimation;
    Texture vikingSheet;
    TextureRegion[] runFrames;
    TextureRegion currentVikingFrame;
    float stateTime;





    public void create(int x, int y){

        stateTime = 0f;
        //vikingImage = new Texture(Gdx.files.internal("sprites/Shieldmaiden/5x/attack_0_64px.png"));
        viking = new Rectangle();
//viking.x = 800 / 2 - VIKING_WIDTH / 2;			// default x-coord positionE
        viking.x = 400 - VIKING_WIDTH / 2;					// default x-coord position
        //viking.y = 480 / 2 - VIKING_HEIGHT / 2;				// default y-coord position
        viking.y = 480 - VIKING_HEIGHT;				// default y-coord position
        viking.width = VIKING_WIDTH;						// width in pixels
        viking.height = VIKING_HEIGHT;						// height in pixels

        // load animation frames
        vikingSheet = new Texture(Gdx.files.internal("sprites/Valkyrie_big.png"));
        TextureRegion[][] tmp = TextureRegion.split(vikingSheet, vikingSheet.getWidth()/VIKING_FRAME_COLS, vikingSheet.getHeight()/VIKING_FRAME_ROWS);
        runFrames = new TextureRegion[6];
        int index = 0;
        for (int i = 0; i < NUM_RUN_FRAMES; i++) {
            runFrames[index++] = tmp[i][1];			// load run frames from sprite sheet
        }
        // sets time to display each frame
        runAnimation = new Animation(FRAME_DISPLAY_TIME, runFrames);	// f designates float value instead of double

        viking.width = VIKING_WIDTH ;
        viking.height = VIKING_HEIGHT ;

    }

    public void render(OrthographicCamera camera, SpriteBatch spriteBatch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentVikingFrame = runAnimation.getKeyFrame(stateTime, true);		// get current frame; true tells animation to restart after last frame

        // polling method; gdx also supports event listener if needed
        if (Gdx.input.isTouched()) {


            // check if screen is currently touched (or mouse pressed)
            //Vector3 touchPos = new Vector3();						// 3-dimensional vector (used with Libgdx orthographic 3D camera)
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);    // transform touch coordinates to camera coordinate system
            camera.unproject(touchPos);


            if (touchPos.x < MyGdxGame.SCREEN_WIDTH / 4 & touchPos.y > MyGdxGame.SCREEN_HEIGHT / 2) {
                //Gdx.app.log("VikingSprite", "Top left was pushed");
                if (viking.y <= MyGdxGame.SCREEN_HEIGHT - viking.height) {
                    viking.y += MyGdxGame.LEVEL_SPEED*2;
                }

            } else if (touchPos.x < MyGdxGame.SCREEN_WIDTH / 4 & touchPos.y <= MyGdxGame.SCREEN_WIDTH) {
                //Gdx.app.log("VikingSprite", "Bottom left was pushed");
                if (viking.y >= 0) {
                    viking.y -= MyGdxGame.LEVEL_SPEED *2;
                }
            }

        }
/*
            viking.y = touchPos.y;				// update viking sprite y-coordinate (can only move in y-direction); center position on cursor
            //viking.x = touchPos.x;            //This allows the viking to move in X-coordinate, but this should be disabled.
            // don't allow sprite to travel beyond screen boundaries


            if (viking.y < 0) viking.y = 0;
            if (viking.y > MyGdxGame.SCREEN_HEIGHT - viking.height) viking.y = MyGdxGame.SCREEN_HEIGHT - viking.height;
        }
        */


            spriteBatch.draw(currentVikingFrame, viking.x, viking.y, viking.width*2, viking.height);



        }


    public void dispose(){
        if (vikingSheet != null) {
            vikingSheet.dispose();
        }
        if (vikingImage != null) {
            vikingImage.dispose();
        }
    }

    public Rectangle getRectangle(){

        return viking;
    }


    // This method deals with the collision of the sprite.
    public void registerCollision(){

        viking.y = MyGdxGame.SCREEN_HEIGHT - viking.height;
        MyGdxGame.score += MyGdxGame.COLLISION_DEDUCTION_SCORE;

    }

}
