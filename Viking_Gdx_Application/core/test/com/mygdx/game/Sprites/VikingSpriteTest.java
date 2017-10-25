package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Interfaces.ISprite;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eric on 11/26/2016.
 */

public class VikingSpriteTest extends GameTest {
    @Test
    public void create() throws Exception {
        Texture texture = new Texture(512, 512, Pixmap.Format.RGB888);
        System.out.println("Test Sprite: " + Gdx.files.getLocalStoragePath());

        ISprite vikingSprite = new VikingSprite();
        //Texture vikingSheet = new Texture(Gdx.files.internal("../android/assets/sprites/Valkyrie_big.png"));
        Texture vikingSheet = new Texture(Gdx.files.internal("sprites/Valkyrie_big.png"));

        //vikingSprite.create(100, 0);
    }

    @Test
    public void render() throws Exception {

    }

    @Test
    public void dispose() throws Exception {

    }

    @Test
    public void getRectangle() throws Exception {

    }

    @Test
    public void registerCollision() throws Exception {

    }

}