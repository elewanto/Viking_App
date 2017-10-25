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




public class LevelOnePostDialogueScreen implements Screen {

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
    public LevelOnePostDialogueScreen(final MyGdxGame gam) {

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


            String text = "Congratulations " + characterName + "! You have collected enough coins to move onto the next level! There are two adventures ahead of you. One of them is heading " +
                    "to the north where the hills are cold and rocky, while the other boat is heading south towards a place with lots of treasure, but known to have pirates. Click on the LEFT to go north: (Rocks)" +
                    "Click on the RIGHT to go south: (Pirates). Choose wisely!";
            GlyphLayout layout = fontCache.setText(text, 0, (int) (Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * .05)), Gdx.graphics.getWidth(), Align.center, true);
            //fontCache.setText(text,0, (int) (Gdx.graphics.getHeight()-);
            //Gdx.app.log("FixedWidthGlyphTest", "Layout width of text is " + layout.width);

            batch.begin();
            fontCache.draw(batch);
            batch.end();


            if (counter >= 2){
                if (Gdx.input.isTouched()) {
                    if (Gdx.input.getX() < (Gdx.graphics.getWidth()/2)) {

                        //Sends the user off to level 3 which is the icy rock level
                        game.LEVEL_NUMBER = 3;
                        pause();
                        game.levelSelect();
                    }else{
                        //Sends the user off to level 2 which is the pirate boat level.
                        game.LEVEL_NUMBER = 2;
                        pause();
                        game.levelSelect();


                    }
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
