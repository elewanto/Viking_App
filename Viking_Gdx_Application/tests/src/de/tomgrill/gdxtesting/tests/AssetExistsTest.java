package de.tomgrill.gdxtesting.tests;

import com.badlogic.gdx.Gdx;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AssetExistsTest {

	// test file pathing and libGDX internal file access
	@Test
	public void sprite1Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sprites/crab2.png").exists());
	}

	@Test
	public void sprite2Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sprites/terrain/floor.png").exists());
	}

	@Test
	public void sprite3Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/music/nordic_acoustic_music.mp3").exists());
	}

	@Test
	public void sprite4Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sprites/coin_copper.png").exists());
	}

	@Test
	public void sprite5Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sprites/coin_gold.png").exists());
	}

	@Test
	public void sprite6Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sounds/coin.wav").exists());
	}

	@Test
	public void sprite7Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sprites/coin_silver.png").exists());
	}

	@Test
	public void sprite8Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sprites/classic-pegleg-ship.png").exists());
	}

	@Test
	public void sprite9Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sprites/Valkyrie_Ship.png").exists());
	}

	@Test
	public void sprite10Exists() {
		assertTrue("File not found", Gdx.files
				.internal("../android/assets/sprites/Valkyrie_big.png").exists());
	}


}
