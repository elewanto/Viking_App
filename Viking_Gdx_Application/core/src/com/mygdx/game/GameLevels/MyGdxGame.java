package com.mygdx.game.GameLevels;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Interfaces.SharedUserData;
import com.mygdx.game.Screens.LevelCredits;
import com.mygdx.game.Screens.LevelOnePostDialogueScreen;
import com.mygdx.game.Screens.LevelOnePreDialogueScreen;
import com.mygdx.game.Screens.LevelOneScreen;
import com.mygdx.game.Screens.LevelThreeScreen;
import com.mygdx.game.Screens.LevelTwoScreen;

//public class MyGdxGame extends ApplicationAdapter implements ApplicationListener {
public class MyGdxGame extends Game implements ApplicationListener {
	public SpriteBatch batch;				// class used to draw 2D images

	// shared across screens
	// set screen dimensions (hopefully we can create this dynamically at some point)
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static int LEVEL_WIDTH;
	public static int score = 0;
	public static int oldScore = 0;
	public static int GOLD_COIN_SCORE = 100;
	public static int SILVER_COIN_SCORE = 50;
	public static int COPPER_COIN_SCORE = 25;
	public static int COLLISION_DEDUCTION_SCORE = -10;
	public static int HARD_MODE = 30;
	public static int MEDIUM_MODE = 20;
	public static int EASY_MODE = 10;
	public static boolean isMuted = false;
	public static int LEVEL_SPEED = MEDIUM_MODE;


	/*
	THE LEVEL STRUCTURE IS DEFINED BELOW. 1,2,3,4,5,6,7 ARE THE DIALOGUE SCREENS
	11, 21,31,41,51,61,71 ARE THE CORRESPONDING LEVELS AFTER THE DIALOGUE.
					1
					11
					111
			2				3
			21				31
		4		5		6		7
		41		51		61		71





	 */
	public static int LEVEL_NUMBER = 1;

	// shared data with main Android project
	public final SharedUserData sharedUserData;
	// constructor
	public MyGdxGame(SharedUserData sharedUserData) {
		this.sharedUserData = sharedUserData;
	}

	// accelerometer variables
	//public Accelerometer mAccel = new Accelerometer();
	//private long startTime;

	// create graphics, music, sound objects
	@Override
	public void create () {
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		LEVEL_WIDTH = SCREEN_WIDTH * 2;



		int i = sharedUserData.getDifficultyLevel();

		switch(i)
		{
			case 0: LEVEL_SPEED = EASY_MODE;
				break;
			case 1: LEVEL_SPEED = MEDIUM_MODE;
				break;
			case 2: LEVEL_SPEED = HARD_MODE;
				break;
			default: LEVEL_SPEED = HARD_MODE;
				break;

		}

		isMuted = !sharedUserData.getIsMuted();


		batch = new SpriteBatch();
		// check last user level completed




		levelSelect();








	}

	// render graphics-- called 30-50-80 times per second depending on hardware
	// render every 33, 20, or 12 milliseconds
	@Override
	public void render () {
		super.render();				// added for Game object / Screen interface
	}

	public void levelSelect(){

		isMuted = !sharedUserData.getIsMuted();


		switch (LEVEL_NUMBER){

			case 1: this.setScreen(new LevelOnePreDialogueScreen(this));
				break;
			case 11: this.setScreen(new LevelOneScreen(this));
				break;
			case 111: this.setScreen(new LevelOnePostDialogueScreen(this));
				break;
			case 2:  this.setScreen(new LevelTwoScreen(this));
				break;
			case 3:  this.setScreen(new LevelThreeScreen(this));
				break;
			case 4: this.setScreen(new LevelCredits(this));
				break;
			default: Gdx.app.log("MyGdxGame.java", "There was a problem in the levelSelect function of the MyGdxGame file");
				break;

		}

		/*
		if (sharedUserData.getPathFour() == 1) {
			// TODO: update this
			this.setScreen(new LevelOneScreen(this));    // first starting screen
		} else if (sharedUserData.getPathThree() == 1) {
			// TODO: update this
			this.setScreen(new LevelOneScreen(this));    // first starting screen
		} else if (sharedUserData.getPathTwo() == 1) {
			// TODO: update this
			this.setScreen(new LevelOneScreen(this));    // first starting screen
		} else {
			// TODO: update this
			this.setScreen(new LevelOneScreen(this));    // first starting screen
		}
		*/

	}
	// clean up objects
	@Override
	public void dispose () {

		if (batch != null) {
			batch.dispose();
	}
	}
}
