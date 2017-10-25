package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.ISprite;
import com.mygdx.game.Screens.LevelOneScreen;

import java.awt.geom.Rectangle2D;

/**
 * Created by Master McCord on 10/21/2016.
 */

public class CrabSprite implements ISprite {

    private Rectangle crab = new Rectangle();;     //Define size of crab sprite

    // set sprite dimensions
    private boolean isMovingRight = true;

    // animation definitions
    private static final int CRAB_FRAME_COLS = 6;
    private static final int CRAB_FRAME_ROWS = 5;
    private static final float FRAME_DISPLAY_TIME = 0.1f;

    Animation eyeAnimation;
    Texture crabSheet;
    TextureRegion[] motionFrames;
    TextureRegion currentCrabFrame;
    float stateTime;


    public void create(int x, int y){



        crabSheet = new Texture(Gdx.files.internal("sprites/crab2.png"));


        crab.width = crabSheet.getWidth()/CRAB_FRAME_COLS;
        crab.height = crabSheet.getHeight()/CRAB_FRAME_ROWS;
        TextureRegion[][] tmp = TextureRegion.split(crabSheet,crabSheet.getWidth()/CRAB_FRAME_COLS, crabSheet.getHeight()/CRAB_FRAME_ROWS);
        motionFrames = new TextureRegion[CRAB_FRAME_COLS * CRAB_FRAME_ROWS];
        int index = 0;
        for(int i = 0; i< CRAB_FRAME_ROWS; i++){
            for(int j = 0; j < CRAB_FRAME_COLS; j++){
                motionFrames[index++] = tmp[i][j];

            }
        }
        crab.x = x;
        crab.y = y;

        eyeAnimation = new Animation(FRAME_DISPLAY_TIME,motionFrames);

        stateTime  =0f;

        // double the size of the crab to make him look better on screen
        crab.setSize(crab.width, crab.height*2);

        //set the collision rectangle for crab

    }

    public void registerCollision(){
        // place holder in case we need to register a collision.
        //MyGdxGame.VikingSprite.registerCollision();
        LevelOneScreen.VikingSprite.registerCollision();
    }

    @Override
    public Rectangle getRectangle(){

        return crab;
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch spriteBatch){

        currentCrabFrame = eyeAnimation.getKeyFrame(stateTime, true);
        stateTime += Gdx.graphics.getDeltaTime();

        /*
        //change the x position of the crab to walk back and forth.
        if (crab.x <= MyGdxGame.SCREEN_WIDTH && isMovingRight){
            crab.x +=MyGdxGame.LEVEL_SPEED;
        }else if (!isMovingRight && crab.x != 0){
            crab.x -=MyGdxGame.LEVEL_SPEED;

        }else if(crab.x == 0){
            isMovingRight = true;
        }else if (crab.x >= MyGdxGame.SCREEN_WIDTH - crab.width){
            isMovingRight = false;
        }
        */


        crab.x -= MyGdxGame.LEVEL_SPEED;


        spriteBatch.draw(currentCrabFrame, crab.x, crab.y,crab.width*2 ,crab.height );


    }

    public void dispose(){

        if (crabSheet != null) {
            crabSheet.dispose();
        }

    }

}
