package de.tomgrill.gdxtesting.tests;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Accelerometer.Accelerometer;
import com.mygdx.game.Interfaces.SharedUserData;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

import static com.badlogic.gdx.Application.ApplicationType.Android;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AccelerometerTest {

	@Test
	public void AccelerometerConstructorTest() {
		Accelerometer testAccel = new Accelerometer();
		assertNotNull(testAccel);
	}

	@Test
	public void AccelerometerShakeTest() {
		Accelerometer testAccel = new Accelerometer();
		boolean testNoShake = testAccel.isShaking();
		assertFalse(testNoShake);
	}

	@Test
	public void AccelerometerWinTest() {
		Accelerometer testAccel = new Accelerometer();
		boolean testWinShake = testAccel.winShaking();
		assertFalse(testWinShake);
	}


}
