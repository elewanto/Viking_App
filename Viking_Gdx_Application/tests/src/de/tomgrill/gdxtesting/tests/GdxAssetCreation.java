package de.tomgrill.gdxtesting.tests;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.CollisionDetector.CollisionDetector;
import com.mygdx.game.Interfaces.ISprite;
import com.mygdx.game.Sprites.CrabSprite;
import com.mygdx.game.Sprites.GoldCoinSprite;
import com.mygdx.game.Sprites.VikingSprite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import de.tomgrill.gdxtesting.GdxTestRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


@RunWith(GdxTestRunner.class)
public class GdxAssetCreation {

	@Test
	public void createViking() {
		ISprite vikingSprite = new VikingSprite();
		vikingSprite.create(100, 0);
		assertNotNull(vikingSprite);
	}

	@Test
	public void createCrab() {
		ISprite crabSprite = new CrabSprite();
		crabSprite.create(15000, 250);
		assertNotNull(crabSprite);
	}

	@Test
	public void createCoin() {
		ISprite coinSprite = new GoldCoinSprite();
		coinSprite.create(15000, 750);
		assertNotNull(coinSprite);
	}

	// test sprite constructor attributes
	@Test
	public void createViking1() {
		ISprite vikingSprite = new VikingSprite();
		vikingSprite.create(100, 0);
		assertEquals((400-150/2), vikingSprite.getRectangle().getX(), 0.1); // expected, actual, delta
	}

	@Test
	public void createCrab1() {
		ISprite crabSprite = new CrabSprite();
		crabSprite.create(15000, 250);
		assertEquals(15000.0, crabSprite.getRectangle().getX(), 0.1); // expected, actual, delta

	}

	@Test
	public void createCoin1() {
		ISprite coinSprite = new GoldCoinSprite();
		coinSprite.create(15000, 750);
		assertEquals(750.0, coinSprite.getRectangle().getY(), 0.1); // expected, actual, delta

	}

}
