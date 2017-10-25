package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.ISprite;


/**
 * Created by Master McCord on 10/31/2016.
 */



public class BackgroundSpritesPath implements ISprite {




    private Rectangle grass = new Rectangle();     //Define size of crab sprite
    private Rectangle path = new Rectangle();
    private Rectangle water = new Rectangle();
    private Rectangle darkGrass = new Rectangle();
    public int levelType = 2; // 1 is grass, 2 is path, 3 is water, and 4 is darGrass


    int shiftPixel; //Use this make the landscape shift as the viking is running.




    // animation definitions

    private static final int TERRAIN_FRAME_COLS = 4;
    private static final int TERRAIN_FRAME_ROWS = 1;



    Texture terrainSheet;
    TextureRegion[] individualFrames;


    float stateTime;




    //This method is used to create the new sprite image
    //x and y are not used since this is just the background
    public void create(int x, int y){


        // set the width of the entire level

        terrainSheet = new Texture(Gdx.files.internal("sprites/terrain/floor.png"));
        shiftPixel = 0;


        water.width = 64;
        water.height = 64;
        water.x = 0;
        water.y = 0;
        grass.width = 64;
        grass.height = 64;
        grass.x = 0;
        grass.y = 64;
        path.width = 64;
        path.height = 64;
        path.x = 0;
        path.y = 128;
        darkGrass.width = 64;
        darkGrass.height = 64;
        darkGrass.x = 0;
        darkGrass.y = 192;

        TextureRegion[][] tmp = TextureRegion.split(terrainSheet,terrainSheet.getWidth()/TERRAIN_FRAME_COLS, terrainSheet.getHeight()/TERRAIN_FRAME_ROWS);
        individualFrames = new TextureRegion[TERRAIN_FRAME_COLS * TERRAIN_FRAME_ROWS];
        int index = 0;

        for(int i = 0; i< TERRAIN_FRAME_ROWS; i++){
            for(int j = 0; j < TERRAIN_FRAME_COLS; j++){
                individualFrames[index++] = tmp[i][j];

            }
        }


        stateTime  =0f;


    }

    //This method is used to render the new sprite and draw it on the screen.
    @Override
    public void render(OrthographicCamera camera, SpriteBatch spriteBatch){

        stateTime += Gdx.graphics.getDeltaTime();



        //NOTE 400 is the height and width in pixels of each square tile.
        for (int  j= 0; j < MyGdxGame.SCREEN_HEIGHT /400 +1; j++) {
            for (int i = 0; i < MyGdxGame.LEVEL_WIDTH / 400 + 1; i++) {

                    spriteBatch.draw(individualFrames[levelType], i * 400 - shiftPixel, j * 400, 400, 400);

            }
        }


        if (shiftPixel <= MyGdxGame.SCREEN_WIDTH) {
            shiftPixel += MyGdxGame.LEVEL_SPEED;
        }else {
            shiftPixel = 0;
        }
      /*
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        currentCrabFrame = eyeAnimation.getKeyFrame(stateTime, true);
        stateTime += Gdx.graphics.getDeltaTime();

        //change the x position of the crab to walk back and forth.
        if (crab.x <= MyGdxGame.SCREEN_WIDTH && isMovingRight){
            crab.x +=10;
        }else if (!isMovingRight && crab.x != 0){
            crab.x -=10;

        }else if(crab.x == 0){
            isMovingRight = true;
        }else if (crab.x >= MyGdxGame.SCREEN_WIDTH - crab.width){
            isMovingRight = false;
        }


        spriteBatch.begin();

        spriteBatch.draw(currentCrabFrame, crab.x, crab.y,crab.width ,crab.height );
        spriteBatch.end();

*/

    }

    //Call this method when you are done with the sprite.
    public void dispose(){

    }

    //Use this method to get the collision rectangle on the sprite
    public Rectangle getRectangle(){

        // No rectangle is needed here since the objects are just being placed on the screen.
        return null;
    }

    public void registerCollision(){
        // This is to remain NULL no collision
    }

}
