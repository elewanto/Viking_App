package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.ISprite;
import com.mygdx.game.Screens.LevelTwoScreen;

/**
 * Created by Eric on 11/5/2016.
 */

public class PirateBoatSprite implements ISprite {

    private Rectangle pirate = new Rectangle();;     //Define size of crab sprite

    // set sprite dimensions
    private boolean isMovingRight = true;

    // animation definitions
    private static final int PIRATE_FRAME_COLS = 5;
    private static final int PIRATE_FRAME_ROWS = 6;
    private static final int NUM_RUN_FRAMES = 6;
    private static final float FRAME_DISPLAY_TIME = 0.1f;

    Animation pirateAnimation;
    Texture pirateSheet;
    TextureRegion[] motionFrames;
    TextureRegion currentPirateFrame;
    float stateTime;


    public void create(int x, int y){

        pirateSheet = new Texture(Gdx.files.internal("sprites/classic-pegleg-ship.png"));
        pirate.width = pirateSheet.getWidth()/PIRATE_FRAME_COLS;
        pirate.height = pirateSheet.getHeight()/PIRATE_FRAME_ROWS;
        TextureRegion[][] tmp = TextureRegion.split(pirateSheet,pirateSheet.getWidth()/PIRATE_FRAME_COLS, pirateSheet.getHeight()/PIRATE_FRAME_ROWS);
        motionFrames = new TextureRegion[NUM_RUN_FRAMES];
        int index = 0;
        for(int i = 0; i< PIRATE_FRAME_ROWS; i++){
                motionFrames[index++] = tmp[i][3];
        }
        pirate.x = x;
        pirate.y = y;

        pirateAnimation = new Animation(FRAME_DISPLAY_TIME, motionFrames);

        stateTime  =0f;

        // double the size of the crab to make him look better on screen
        pirate.setSize(pirate.width*3, pirate.height*3);

        //set the collision rectangle for crab
    }

    public void registerCollision(){
        // place holder in case we need to register a collision.
        //MyGdxGame.VikingBoatSprite.registerCollision();
        LevelTwoScreen.VikingBoatSprite.registerCollision();
    }

    @Override
    public Rectangle getRectangle(){

        return pirate;
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch spriteBatch){

        currentPirateFrame = pirateAnimation.getKeyFrame(stateTime, true);
        stateTime += Gdx.graphics.getDeltaTime();

        //change the x position of the crab to walk back and forth.
        if (pirate.x <= MyGdxGame.SCREEN_WIDTH && isMovingRight){
            pirate.x +=MyGdxGame.LEVEL_SPEED;
        }else if (!isMovingRight && !(pirate.x <= 0)){
            pirate.x -=MyGdxGame.LEVEL_SPEED;

        }else if(pirate.x <= 0){

            isMovingRight = true;
        }else if (pirate.x >= MyGdxGame.SCREEN_WIDTH - pirate.width){
            isMovingRight = false;
        }


        spriteBatch.draw(currentPirateFrame, pirate.x, pirate.y, pirate.width*3 , pirate.height*3 );

    }

    public void dispose(){

        if (pirateSheet != null) {
            pirateSheet.dispose();
        }
    }
}
