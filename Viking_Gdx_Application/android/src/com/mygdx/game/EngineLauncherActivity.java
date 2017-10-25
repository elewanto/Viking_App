package com.mygdx.game;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.GameLevels.MyGdxGame;

public class EngineLauncherActivity extends AndroidApplication {

	private static final String TAG = "EngineLauncherActivity";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called");

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		// disable compass use to conserve device battery
		config.useCompass=false;
		// enable accelerometer for gdx game sensor use
		config.useAccelerometer=true;
		initialize(new MyGdxGame(new AndroidUserData(this)), config);
		//initialize(new MyGdxGame(), config);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume() called");
		// TODO: handles exiting Gdx if back button pressed in-game?
		//Gdx.app.exit();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}
}
