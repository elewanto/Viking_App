package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.Accelerometer.Accelerometer;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.CollisionDetector.CollisionDetector;
import com.mygdx.game.Interfaces.ISprite;
import com.mygdx.game.Sprites.BackgroundSpritesWater;
import com.mygdx.game.Sprites.GoldCoinSprite;
import com.mygdx.game.Sprites.PirateBoatSprite;
import com.mygdx.game.Sprites.SilverCoinSprite;
import com.mygdx.game.Sprites.VikingBoatSprite;
import com.mygdx.game.BitmapFonts.DisplayText;
import java.util.ArrayList;

import static com.mygdx.game.GameLevels.MyGdxGame.SCREEN_HEIGHT;
import static com.mygdx.game.GameLevels.MyGdxGame.SCREEN_WIDTH;

/**
 * Created by Eric on 11/5/2016.
 */

public class LevelTwoScreen implements Screen {

    final MyGdxGame game;

    private OrthographicCamera camera;		// class used to render at 800x480 target resolution

    private Sound explosionSound;			// explosion sound effect

    private static final int NUMBER_OF_SPRITES = 5; // Increase this number depending on number of sprites to render.

    public static ISprite VikingBoatSprite = new VikingBoatSprite();
    ISprite PirateBoatSprite = new PirateBoatSprite();
    ISprite PirateBoatSprite2 = new PirateBoatSprite();
    ISprite BackgroundSpritesWater = new BackgroundSpritesWater();
    ISprite SilverCoinSprite = new SilverCoinSprite();
    ISprite SilverCoinSprite2 = new SilverCoinSprite();
    ISprite GoldCoinSprite1 = new GoldCoinSprite();
    ISprite SilverCoinSprite3 = new SilverCoinSprite();
    private Music celticMusic;

    private DisplayText textFont;
    private BitmapFont scoreTextFont;
    private BitmapFont scoreValueFont;
    private BitmapFont isShakinFont;
    private BitmapFont notShakinFont;
    private static final int SCORE_NEEDED_TO_WIN = 150;

    public Accelerometer mAccel;
    private long startTime;
    private long shakeTime = 0;
    private boolean mStartShakeFlag = false;

    CollisionDetector collisionDetector = new CollisionDetector(); //does the job of collision detection and reaction
    ArrayList spriteList = new ArrayList(NUMBER_OF_SPRITES);  //put all the sprites into this array.

    // constructor
    public LevelTwoScreen(final MyGdxGame gam) {
        this.game = gam;

        game.score = 0;
        game.oldScore = 0;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        // resets state time to zero
        VikingBoatSprite.create(SCREEN_WIDTH/8,0);
        PirateBoatSprite.create(1000,600);
        PirateBoatSprite2.create(10000,250);
        SilverCoinSprite.create(15000,750);
        SilverCoinSprite2.create(25000, 250);
        SilverCoinSprite3.create(40000, 900);
        GoldCoinSprite1.create(30000,110);
        BackgroundSpritesWater.create(0,0);

        // load sound effects (stored in memory; use for 10 seconds or less)
        //explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion1.wav"));

        // load music (streamed from storage; not completely stored in memory; use for longer than 10 seconds)
        celticMusic = Gdx.audio.newMusic(Gdx.files.internal("music/celtic_acoustic_music.mp3"));

        //Initialize the spriteList with all the wanted sprites.
        spriteList.add(0,VikingBoatSprite);
        spriteList.add(PirateBoatSprite);
        spriteList.add(PirateBoatSprite2);
        spriteList.add(SilverCoinSprite);
        spriteList.add(SilverCoinSprite2);
        spriteList.add(SilverCoinSprite3);
        spriteList.add(GoldCoinSprite1);

        // fonts
        textFont = new DisplayText();
        scoreTextFont = textFont.getFont("Score: ", 64, "YELLOW");
        notShakinFont = textFont.getFont("SHAKE TO WIN!!", 128, "RED");
        isShakinFont = textFont.getFont("KEEP SHAKIN AND BAKIN!!", 128, "BLUE");
        scoreValueFont = textFont.getFont(Integer.toString(game.score), 64, "RED");

        mAccel = new Accelerometer();
        startTime =TimeUtils.millis();
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
        BackgroundSpritesWater.render(camera, game.batch);
        PirateBoatSprite.render(camera, game.batch);
        VikingBoatSprite.render(camera, game.batch);
        SilverCoinSprite.render(camera, game.batch);
        SilverCoinSprite2.render(camera, game.batch);
        SilverCoinSprite3.render(camera, game.batch);
        PirateBoatSprite2.render(camera, game.batch);
        GoldCoinSprite1.render(camera, game.batch);
        // update score font value with current score if score changed
        if (game.score != game.oldScore) {
            game.oldScore = game.score;
            scoreValueFont = textFont.getFont(Integer.toString(game.score), 64, "RED");
        }

                                           // required for bitmap font drawing
        scoreTextFont.draw(game.batch, "Score: ", (SCREEN_WIDTH / 2) - 250, SCREEN_HEIGHT - 40);
        scoreValueFont.draw(game.batch, Integer.toString(game.score) , SCREEN_WIDTH / 2, SCREEN_HEIGHT - 40);

        //testFont.draw(batch, sharedUserData.getPathMessage(), 30, SCREEN_HEIGHT - 200);
        if (mStartShakeFlag) {
            if (mAccel.isShaking()) {           // updates shaking status, increments, and prints correct status message
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
                //game.sharedUserData.setPathTwo(1);          // update user record to completed level 1
                // TODO: victory loops back to level one...need to redirect to dialogue screen
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
        if (!game.isMuted) {
            // start playback of background music on startup
            celticMusic.setLooping(true);
            celticMusic.play();
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        if (!game.isMuted) {
            celticMusic.stop();
        }
    }

    @Override
    public void resume(){

    }

    @Override
    public void dispose() {
        if (SilverCoinSprite != null) {
            SilverCoinSprite.dispose();
        }
        if (SilverCoinSprite2 != null) {
            SilverCoinSprite2.dispose();
        }
        if (SilverCoinSprite3 != null) {
            SilverCoinSprite3.dispose();
        }
        if (PirateBoatSprite != null) {
            PirateBoatSprite.dispose();
        }
        if (PirateBoatSprite2 != null) {
            PirateBoatSprite2.dispose();
        }
        if (VikingBoatSprite != null) {
            VikingBoatSprite.dispose();
        }
        if (celticMusic != null) {
            celticMusic.stop();
            celticMusic.dispose();
        }
        if (explosionSound != null) {
            explosionSound.dispose();
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
