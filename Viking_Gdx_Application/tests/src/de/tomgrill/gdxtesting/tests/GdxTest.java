package de.tomgrill.gdxtesting.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.CollisionDetector.CollisionDetector;
import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.ISprite;
import com.mygdx.game.Interfaces.SharedUserData;
import com.mygdx.game.Screens.LevelOneScreen;
import com.mygdx.game.Sprites.CrabSprite;
import com.mygdx.game.Sprites.GoldCoinSprite;
import com.mygdx.game.Sprites.VikingSprite;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import de.tomgrill.gdxtesting.GdxTestRunner;

import static com.mygdx.game.GameLevels.MyGdxGame.SCREEN_WIDTH;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class GdxTest {

	@Before
	public void initialize() {

	}

	@Test
	public void testCollisionFalse() {
		System.out.println("Test 1: " + Gdx.files.getLocalStoragePath());
		ISprite vikingSprite = new VikingSprite();
		ISprite crabSprite = new CrabSprite();
		ISprite coinSprite = new GoldCoinSprite();
		vikingSprite.create(100, 0);
		crabSprite.create(15000, 250);
		coinSprite.create(15000, 750);
		assertFalse(vikingSprite.getRectangle().overlaps(crabSprite.getRectangle()));
	}

	@Test
	public void testCollisionTrue() {
		ISprite vikingSprite = new VikingSprite();
		ISprite crab2Sprite = new CrabSprite();
		vikingSprite.create(100, 0);
		crab2Sprite.create((400-150/2), 480 - 200);
		assertTrue(vikingSprite.getRectangle().overlaps(crab2Sprite.getRectangle()));
	}

	@Test
	public void testCollisionDetectorNoCollision() {
		ISprite vikingSprite = new VikingSprite();
		ISprite crabSprite = new CrabSprite();
		//ISprite crab2Sprite = new CrabSprite();
		vikingSprite.create(100, 0);
		crabSprite.create(15000, 250);
		//crab2Sprite.create((400-150/2), 480 - 200);
		System.out.println(vikingSprite.getRectangle().getY());

		CollisionDetector collisionDetector = new CollisionDetector();
		ArrayList spriteList = new ArrayList(2);
		spriteList.add(0, vikingSprite);
		spriteList.add(crabSprite);
		//spriteList.add(crab2Sprite);
		collisionDetector.update(spriteList);
		System.out.println(vikingSprite.getRectangle().getY());

		float newVikingX = vikingSprite.getRectangle().getX();
		assertFalse(vikingSprite.getRectangle().overlaps(crabSprite.getRectangle()));
	}
/*
	@Test
	public void testCollisionDetectorYesCollision() {

		SharedUserData testData = Mockito.mock(SharedUserData.class, Mockito.CALLS_REAL_METHODS);
		MyGdxGame testGame = new MyGdxGame(testData);
		//LevelOneScreen testScreen = new LevelOneScreen(testGame);
		LevelOneScreen testScreen = Mockito.mock(LevelOneScreen.class);

		ISprite vikingSprite = new VikingSprite();
		ISprite crabSprite = new CrabSprite();
		ISprite crab2Sprite = new CrabSprite();
		crab2Sprite = Mockito.mock(CrabSprite.class, Mockito.CALLS_REAL_METHODS);
		Mockito.doNothing().when(crab2Sprite).registerCollision();
		vikingSprite.create(100, 0);
		crabSprite.create(15000, 250);
		crab2Sprite.create((400-150/2), 480 - 200);
		System.out.println(vikingSprite.getRectangle().getY());

		CollisionDetector collisionDetector = new CollisionDetector();
		ArrayList spriteList = new ArrayList(3);
		spriteList.add(0, vikingSprite);
		spriteList.add(crabSprite);
		spriteList.add(crab2Sprite);
		collisionDetector.update(spriteList);
		System.out.println(vikingSprite.getRectangle().getY());

		float newVikingX = vikingSprite.getRectangle().getX();
		assertTrue(vikingSprite.getRectangle().overlaps(crab2Sprite.getRectangle()));
	}
*/

}
