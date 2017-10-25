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
import com.mygdx.game.Screens.LevelOneScreen;
import com.mygdx.game.Screens.LevelThreeScreen;

/**
 * Created by Master McCord on 11/28/2016.
 */

//ONLY USE THIS FOR LEVEL THREE. THE COLLISION WILL NOT WORK OTHERWISE.

public class RockSprite implements ISprite {

    private Rectangle rock = new Rectangle();     //Define size of crab sprite

    // set sprite dimensions
    private boolean isMovingRight = true;

    // animation definitions
    private static final int ROCK_FRAME_COLS = 1;
    private static final int ROCK_FRAME_ROWS = 1;
    private static final float FRAME_DISPLAY_TIME = 0.1f;


    Animation rockAnimation;
    Texture rockSheet;
    TextureRegion[] motionFrames;
    TextureRegion currentRockFrame;

    float stateTime;


    public void create(int x, int y){



        rockSheet = new Texture(Gdx.files.internal("sprites/rock.png"));


        rock.width = rockSheet.getWidth()/ROCK_FRAME_COLS;
        rock.height = rockSheet.getHeight()/ROCK_FRAME_ROWS;
        TextureRegion[][] tmp = TextureRegion.split(rockSheet,rockSheet.getWidth()/ROCK_FRAME_COLS, rockSheet.getHeight()/ROCK_FRAME_ROWS);
        motionFrames = new TextureRegion[ROCK_FRAME_COLS * ROCK_FRAME_ROWS];
        int index = 0;
        for(int i = 0; i< ROCK_FRAME_ROWS; i++){
            for(int j = 0; j < ROCK_FRAME_COLS; j++){
                motionFrames[index++] = tmp[i][j];

            }
        }
        rock.x = x;
        rock.y = y;

        rockAnimation = new Animation(FRAME_DISPLAY_TIME,motionFrames);

        stateTime  =0f;

        // double the size of the crab to make him look better on screen
        rock.setSize(rock.width*2, rock.height*4);


        //set the collision rectangle for crab

    }

    public void registerCollision(){
        // place holder in case we need to register a collision.
        //MyGdxGame.VikingSprite.registerCollision();
        LevelThreeScreen.VikingSprite.registerCollision();
    }

    @Override
    public Rectangle getRectangle(){

        return rock;
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch spriteBatch){

        currentRockFrame = rockAnimation.getKeyFrame(stateTime, true);
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


        rock.x -= MyGdxGame.LEVEL_SPEED;


        spriteBatch.draw(currentRockFrame, rock.x, rock.y, rock.width*2 ,rock.height );


    }

    public void dispose(){

        if (rockSheet != null) {
            rockSheet.dispose();
        }

    }

}
