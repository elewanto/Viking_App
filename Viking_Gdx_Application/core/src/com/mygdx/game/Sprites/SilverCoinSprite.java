package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.ISprite;

/**
<<<<<<< Updated upstream
 * Created by Eric on 11/5/2016.
=======
 * Created by Master McCord on 10/24/2016.
>>>>>>> Stashed changes
 */

public class SilverCoinSprite implements ISprite {


    private Rectangle silverCoin = new Rectangle();
    private Sound coinSound;

    private Boolean taken = false;  // registers whether the coin has been taken or not
    private boolean isMovingRight = true;

    // animation definitions
    private static final int SILVERCOIN_FRAME_COLS = 8;
    private static final int SILVERCOIN_FRAME_ROWS = 1;
    private static final float FRAME_DISPLAY_TIME = 0.1f;

    Animation silverCoinAnimation;
    Texture silverCoinSheet;
    TextureRegion[] motionFrames;
    TextureRegion currentSilverCoinFrame;
    float stateTime;




    public void create(int x , int y ){


        silverCoin.x = x;
        silverCoin.y = y;
        silverCoinSheet = new Texture(Gdx.files.internal("sprites/coin_silver.png"));
        coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));

        int goldCoinSheetWidth = silverCoinSheet.getWidth();
        int goldCoinSheetHeight = silverCoinSheet.getHeight();
        silverCoin.width = goldCoinSheetWidth/SILVERCOIN_FRAME_COLS;
        silverCoin.height =  goldCoinSheetHeight/SILVERCOIN_FRAME_ROWS;

        TextureRegion[][] tmp = TextureRegion.split(silverCoinSheet, (int) silverCoin.width,(int) silverCoin.height);
        motionFrames = new TextureRegion[SILVERCOIN_FRAME_ROWS * SILVERCOIN_FRAME_COLS];
        int index = 0;

        for(int i = 0; i< SILVERCOIN_FRAME_ROWS; i++){
            for(int j = 0; j < SILVERCOIN_FRAME_COLS; j++){
                motionFrames[index++] = tmp[i][j];
            }
        }
        silverCoinAnimation = new Animation(FRAME_DISPLAY_TIME, motionFrames);

        stateTime = 0f;


        //make the coin larger to fit the scene
        silverCoin.height *=6;
        silverCoin.width *=6;



    }

    public void render(OrthographicCamera camera, SpriteBatch spriteBatch){


        if (!taken) {
            currentSilverCoinFrame = silverCoinAnimation.getKeyFrame(stateTime, true);
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
            silverCoin.x-=MyGdxGame.LEVEL_SPEED;


            spriteBatch.draw(currentSilverCoinFrame, silverCoin.x, silverCoin.y, silverCoin.width, silverCoin.height);

        }

        if (silverCoin.x < 0){
            // remove the gold coin if it is off the screen.
            taken = true;

        }

    }


    public Rectangle getRectangle() {

        return silverCoin;
    }





    public void registerCollision(){




        if (!taken) {
            MyGdxGame.score += MyGdxGame.SILVER_COIN_SCORE;
            taken = true;
            if (!MyGdxGame.isMuted) {
                coinSound.play();
            }
        }

    }


    public void dispose() {
        if (silverCoinSheet != null) {
            silverCoinSheet.dispose();
        }
    }




        // Place holder in case we need to add in collision for the coin.
    }

