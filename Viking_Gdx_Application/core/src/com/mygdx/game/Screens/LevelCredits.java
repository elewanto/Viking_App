package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameLevels.MyGdxGame;

//com.badlogic.gdx.scenes.scene2d.ui.TextField


/**
 * Created by Josh McCord on 11/28/2016.
 */




public class LevelCredits implements Screen {

    SpriteBatch batch;
    BitmapFont font;
    BitmapFontCache fontCache;
    float counter;
    MyGdxGame game;
    Boolean shouldMoveOn = false;
    Boolean canMoveOn = false;
    int secondsUntilDialogSkip;
    private Music dialogueMusic;



    //Constructor
    public LevelCredits(final MyGdxGame gam) {

        game = gam;
        batch = new SpriteBatch();

        font = new BitmapFont();
        font.getData().setScale(6);
        font.setFixedWidthGlyphs("0123456789");

        fontCache = new BitmapFontCache(font);

        dialogueMusic = Gdx.audio.newMusic(Gdx.files.internal("music/viking.mp3"));
        if (!game.isMuted) {
            dialogueMusic.setLooping(true);
            dialogueMusic.play();
        }




    }





    @Override
    public void show(){

    }
    @Override
    public void hide(){

    }
    @Override
    public void pause()
    {

        if (!game.isMuted) {
            dialogueMusic.stop();
        }
    }
    @Override
    public void resume(){


    }
    @Override
    public void resize(int x, int y){

    }

    @Override
    public void render(float f){


        if( canMoveOn ==false) {

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            //String text = String.valueOf((int)counter);

            String characterName = game.sharedUserData.getCharacterName();


            String text = "Congratulations " + characterName + "! You have finished the game. Please say a huge thanks to Josh McCord, Eric Lewantowicz, and Kevin Hernandez! Click the screen to play again, or press the BACK button to return to the options. ";
            GlyphLayout layout = fontCache.setText(text, 0, (int) (Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * .05)), Gdx.graphics.getWidth(), Align.center, true);
            //fontCache.setText(text,0, (int) (Gdx.graphics.getHeight()-);
            //Gdx.app.log("FixedWidthGlyphTest", "Layout width of text is " + layout.width);

            batch.begin();
            fontCache.draw(batch);
            batch.end();


            if (counter >= 2){
                if (Gdx.input.isTouched()) {


                    game.LEVEL_NUMBER =1;
                    pause();
                    game.levelSelect();
                }
            }else{
                counter += Gdx.graphics.getDeltaTime();
            }
        }


    }




    public void dispose(){
    //dialogueFont.dispose();
        batch.dispose();
    }
}
