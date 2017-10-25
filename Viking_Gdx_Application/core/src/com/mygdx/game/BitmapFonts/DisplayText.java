package com.mygdx.game.BitmapFonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Field;


/**
 * Created by Eric on 10/31/2016.
 */

public class DisplayText {

    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    private BitmapFont font;


    public BitmapFont getFont(String text, int fontSize, String fontColor) {

        Color color;

        generator = new FreeTypeFontGenerator(Gdx.files.internal("IstokWeb-Bold.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.characters = text;
        // use reflection to convert color from string
        try {
            Field field = Color.class.getField(fontColor);
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = Color.BLACK;
        }
        parameter.color = color;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

}