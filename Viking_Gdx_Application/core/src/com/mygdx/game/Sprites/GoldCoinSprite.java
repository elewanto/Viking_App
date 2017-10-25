package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.ISprite;

import java.awt.geom.Rectangle2D;

/**
 * Created by Master McCord on 10/24/2016.
 */

public class GoldCoinSprite implements ISprite {


    private Rectangle goldCoin = new Rectangle();
    private Sound coinSound;

    private Boolean taken = false; // registers whether the coin has been taken or not.


    // animation definitions
    private static final int GOLDCOIN_FRAME_COLS = 8;
    private static final int GOLDCOIN_FRAME_ROWS =1 ;
    private static final float FRAME_DISPLAY_TIME = 0.1f;

    private boolean isMovingRight = true;

    Animation goldCoinAnimation;
    Texture goldCoinSheet;
    TextureRegion[] motionFrames;
    TextureRegion currentGoldCoinFrame;
    float stateTime;


    public void create(int x , int y ){

        goldCoin.x = x;
        goldCoin.y = y;
        goldCoinSheet = new Texture(Gdx.files.internal("sprites/coin_gold.png"));
        coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));

        int goldCoinSheetWidth = goldCoinSheet.getWidth();
        int goldCoinSheetHeight = goldCoinSheet.getHeight();
        goldCoin.width = goldCoinSheetWidth/GOLDCOIN_FRAME_COLS;
        goldCoin.height =  goldCoinSheetHeight/GOLDCOIN_FRAME_ROWS;

        TextureRegion[][] tmp = TextureRegion.split(goldCoinSheet, (int) goldCoin.width,(int) goldCoin.height);
        motionFrames = new TextureRegion[GOLDCOIN_FRAME_ROWS * GOLDCOIN_FRAME_COLS];
        int index = 0;

        for(int i = 0; i< GOLDCOIN_FRAME_ROWS; i++){
            for(int j = 0; j < GOLDCOIN_FRAME_COLS; j++){
                motionFrames[index++] = tmp[i][j];
            }
        }
        goldCoinAnimation = new Animation(FRAME_DISPLAY_TIME, motionFrames);

        stateTime = 0f;

        //make the coin larger to fit the scene
        goldCoin.height *=6;
        goldCoin.width *=6;

    }

    public void render(OrthographicCamera camera, SpriteBatch spriteBatch){

        if (!taken) {
            currentGoldCoinFrame = goldCoinAnimation.getKeyFrame(stateTime, true);
            stateTime += Gdx.graphics.getDeltaTime();

            /*
            if (goldCoin.x <= MyGdxGame.SCREEN_WIDTH && isMovingRight) {
                goldCoin.x += MyGdxGame.LEVEL_SPEED;
            } else if (!isMovingRight && goldCoin.x != 0) {
                goldCoin.x -= MyGdxGame.LEVEL_SPEED;

            } else if (goldCoin.x == 0) {
                isMovingRight = true;
            } else if (goldCoin.x >= MyGdxGame.SCREEN_WIDTH - goldCoin.width) {
                isMovingRight = false;
            }

            */
            goldCoin.x-=MyGdxGame.LEVEL_SPEED;


            spriteBatch.draw(currentGoldCoinFrame, goldCoin.x, goldCoin.y, goldCoin.width, goldCoin.height);

        }

        if (goldCoin.x < 0){
            // remove the gold coin if it is off the screen.
            taken = true;
        }

    }

    public Rectangle getRectangle(){

        return goldCoin;
    }

    public void registerCollision(){

        if (!taken) {
            MyGdxGame.score += MyGdxGame.GOLD_COIN_SCORE;
            taken = true;
            if (!MyGdxGame.isMuted) {
                coinSound.play();
            }
        }
        // Place holder in case we need to add in collision for the coin.
    }

    public void dispose() {
        if (goldCoinSheet != null) {
            goldCoinSheet.dispose();
        }
    }

}
