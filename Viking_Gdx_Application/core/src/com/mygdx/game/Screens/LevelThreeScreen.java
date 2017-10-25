package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Accelerometer.Accelerometer;
import com.mygdx.game.BitmapFonts.DisplayText;
import com.mygdx.game.CollisionDetector.CollisionDetector;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.ISprite;
import com.mygdx.game.Sprites.BackgroundSprites;
import com.mygdx.game.Sprites.BackgroundSpritesDarGrass;
import com.mygdx.game.Sprites.BackgroundSpritesPath;
import com.mygdx.game.Sprites.CopperCoinSprite;
import com.mygdx.game.Sprites.CrabSprite;
import com.mygdx.game.Sprites.GoldCoinSprite;
import com.mygdx.game.Sprites.RockSprite;
import com.mygdx.game.Sprites.SilverCoinSprite;
import com.mygdx.game.Sprites.VikingSprite;

import java.util.ArrayList;

import static com.mygdx.game.GameLevels.MyGdxGame.SCREEN_HEIGHT;
import static com.mygdx.game.GameLevels.MyGdxGame.SCREEN_WIDTH;

/**
 * Created by Eric on 11/5/2016.
 */

public class LevelThreeScreen implements Screen {

    final MyGdxGame game;

    private OrthographicCamera camera;		// class used to render at 800x480 target resolution

    private Music nordicMusic;				// background music

    private static final int NUMBER_OF_SPRITES = 5; // Increase this number depending on number of sprites to render.
    private int SCORE_NEEDED_TO_WIN = 300;

    //create each sprite here
    public static ISprite VikingSprite = new VikingSprite();
    ISprite RockSprite = new RockSprite();
    ISprite GoldCoinSprite = new GoldCoinSprite();
    ISprite GoldCoinSprite2 = new GoldCoinSprite();
    ISprite GoldCoinSprite3 = new GoldCoinSprite();
    ISprite SilverCoinSprite = new SilverCoinSprite();
    ISprite SilverCoinSprite2 = new SilverCoinSprite();
    ISprite CopperCoinSprite = new CopperCoinSprite();
    ISprite CopperCoinSprite2 = new CopperCoinSprite();
    ISprite BackgroundSprites = new BackgroundSpritesPath();
    ISprite RockSprite2 = new RockSprite();
    ISprite RockSprite3 = new RockSprite();
    ISprite RockSprite4 = new RockSprite();
    ISprite RockSprite5 = new RockSprite();
    ISprite RockSprite6 = new RockSprite();
    ISprite RockSprite7 = new RockSprite();
    ISprite RockSprite8 = new RockSprite();
    ISprite RockSprite9 = new RockSprite();
    ISprite RockSprite10 = new RockSprite();

    private DisplayText textFont;
    private BitmapFont scoreTextFont;
    private BitmapFont scoreValueFont;
    private BitmapFont isShakinFont;
    private BitmapFont notShakinFont;

    private Accelerometer mAccel;
    private long startTime = 0;
    private long shakeTime = 0;
    private boolean mStartShakeFlag = false;

    CollisionDetector collisionDetector = new CollisionDetector(); //does the job of collision detection and reaction
    ArrayList spriteList = new ArrayList(NUMBER_OF_SPRITES);  //put all the sprites into this array.

    // constructor
    public LevelThreeScreen(final MyGdxGame gam) {
        this.game = gam;

        game.score = 0;
        game.oldScore = 0;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        // resets state time to zero
        VikingSprite.create(SCREEN_WIDTH / 8, 0);
        RockSprite.create(15000,250);
        RockSprite2.create(10000,250);
        RockSprite3.create(25000, 750);
        RockSprite4.create(40000, 200);
        RockSprite5.create(33000, 700);
        RockSprite6.create(21000,0);
        RockSprite7.create(18000, 300);
        RockSprite8.create(29000, 700);
        GoldCoinSprite.create(15000,750);
        GoldCoinSprite2.create(25000, 250);
        GoldCoinSprite3.create(40000, 900);
        CopperCoinSprite.create(33000,350);
        CopperCoinSprite2.create(21000, 500);
        SilverCoinSprite.create(18000, 800);
        SilverCoinSprite2.create(29000, 100);
        BackgroundSprites.create(0,0);

        // load music (streamed from storage; not completely stored in memory; use for longer than 10 seconds)
        nordicMusic = Gdx.audio.newMusic(Gdx.files.internal("music/nordic_acoustic_music.mp3"));

        //Initialize the spriteList with all the wanted sprites.
        spriteList.add(0,VikingSprite);
        spriteList.add(RockSprite);
        spriteList.add(RockSprite2);
        spriteList.add(RockSprite3);
        spriteList.add(RockSprite4);
        spriteList.add(RockSprite5);
        spriteList.add(RockSprite6);
        spriteList.add(RockSprite7);
        spriteList.add(RockSprite8);
        spriteList.add(GoldCoinSprite);
        spriteList.add(GoldCoinSprite2);
        spriteList.add(GoldCoinSprite3);

        spriteList.add(SilverCoinSprite);
        spriteList.add(SilverCoinSprite2);
        spriteList.add(CopperCoinSprite);
        spriteList.add(CopperCoinSprite2);

        // fonts
        textFont = new DisplayText();
        scoreTextFont = textFont.getFont("Score: ", 64, "YELLOW");
        notShakinFont = textFont.getFont("SHAKE TO WIN!!", 128, "RED");
        isShakinFont = textFont.getFont("KEEP SHAKIN AND BAKIN!!", 128, "BLUE");
        scoreValueFont = textFont.getFont(Integer.toString(game.score), 64, "RED");

        mAccel = new Accelerometer();
        startTime = TimeUtils.millis();

    }

    @Override
    public void render(float delta) {

        //BitmapFont testFont = textFont.getFont(sharedUserData.getPathMessage(), 64, "BLUE");
        // render background color (r, g, b, alpha)
        Gdx.gl.glClearColor(0, 1, 0, 1);
        // instruct OpenGL to clear screen
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera.update();								// update camera once per frame
        game.batch.setProjectionMatrix(camera.combined);
        // get current animation frame
        // add elapsed time since last render
        // use batch to tell OpenGL to render multiple images at once
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        game.batch.begin();
        BackgroundSprites.render(camera, game.batch);
        RockSprite.render(camera,  game.batch);
        RockSprite2.render(camera, game.batch);
        RockSprite3.render(camera, game.batch);
        RockSprite4.render(camera, game.batch);
        RockSprite5.render(camera, game.batch);
        RockSprite6.render(camera, game.batch);
        RockSprite7.render(camera, game.batch);
        RockSprite8.render(camera, game.batch);
        VikingSprite.render( camera, game.batch);
        GoldCoinSprite.render(camera, game.batch);
        GoldCoinSprite2.render(camera, game.batch);
        GoldCoinSprite3.render(camera,game.batch);

        SilverCoinSprite2.render(camera, game.batch);
        SilverCoinSprite.render(camera, game.batch);
        CopperCoinSprite.render(camera, game.batch);
        CopperCoinSprite2.render(camera, game.batch);

        // update score font value with current score if score changed
        if (game.score != game.oldScore) {
            game.oldScore = game.score;
            scoreValueFont = textFont.getFont(Integer.toString(game.score), 64, "RED");
        }
                                   // required for bitmap font drawing
        scoreTextFont.draw(game.batch, "Score: ", (SCREEN_WIDTH / 2) - 250, SCREEN_HEIGHT - 40);
        scoreValueFont.draw(game.batch, Integer.toString(game.score), SCREEN_WIDTH / 2, SCREEN_HEIGHT - 40);
        //testFont.draw(batch, sharedUserData.getPathMessage(), 30, SCREEN_HEIGHT - 200);
        if (mStartShakeFlag) {
            if (mAccel.isShaking()) {       // updates shaking status, increments, and prints correct status message
                isShakinFont.draw(game.batch, "KEEP SHAKIN AND BAKIN!!", 20, SCREEN_HEIGHT - 400);
                Gdx.input.vibrate(50);
            } else {
                notShakinFont.draw(game.batch, "SHAKE TO WIN!!", 20, SCREEN_HEIGHT - 400);
            }
        }
        game.batch.end();

        //batch.draw(vikingImage, viking.x, viking.y);
        // user movement control
        collisionDetector.update(spriteList);
        // check end of level




        if (game.score >= SCORE_NEEDED_TO_WIN) {
            if (!mStartShakeFlag) {                         // start shake timer once required score reached
                mStartShakeFlag = true;
                shakeTime = TimeUtils.millis();
            } else if (mAccel.winShaking()) {               // wins shaking; proceed to next level dialogue
                //game.sharedUserData.setPathOne(1);          // update user record to completed level 1
                game.LEVEL_NUMBER = 4;
                pause();
                game.levelSelect();


            }
        }

        // restart level if level timer exceeds limit or shake timer exceeds limit (milliseconds)
        if (TimeUtils.timeSinceMillis(startTime) > 40000 || (mStartShakeFlag && TimeUtils.timeSinceMillis(shakeTime) > 6000)) {
            pause();
            game.levelSelect();


        }


    } // end render()

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        // start playback of background music on startup
        if (!game.isMuted) {
            nordicMusic.setLooping(true);
            nordicMusic.play();
        }

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        if (!game.isMuted) {
            nordicMusic.stop();
        }
    }

    @Override
    public void resume() {


    }

    @Override
    public void dispose() {
        if (GoldCoinSprite != null) {
            GoldCoinSprite.dispose();
        }
        if (GoldCoinSprite2 != null) {
            GoldCoinSprite2.dispose();
        }
        if (GoldCoinSprite3 != null) {
            GoldCoinSprite3.dispose();
        }
        if (RockSprite != null) {
            RockSprite.dispose();
        }
        if (RockSprite2 != null) {
            RockSprite2.dispose();
        }
        if (VikingSprite != null) {
            VikingSprite.dispose();
        }
        if (nordicMusic != null) {
            nordicMusic.stop();
            nordicMusic.dispose();
        }
        if (isShakinFont != null) {
            isShakinFont.dispose();
        }
        if (notShakinFont != null) {
            notShakinFont.dispose();
        }
        if (scoreValueFont != null) {
            scoreValueFont.dispose();
        }
        if (scoreTextFont != null) {
            scoreTextFont.dispose();
        }
    }

}
