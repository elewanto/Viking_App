package com.mygdx.game;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eric on 11/27/2016.
 */
public class AndroidUserDataTest {

    UserData testData;
    UserData testData2;
    AndroidUserData testAndroidData;

    @Before
    public void setUp() throws Exception {
        testData = UserData.get();
    }

    @After
    public void tearDown() throws Exception {
        testData.setCharacterName("");
        testData.setHighScore(0);
        testData.setItemOneCount(0);
        testData.setPathOne(0);
    }

    // UserData getter tests
    @Test
    public void UserDataConstructor1() throws Exception {
        int val = testData.getHighScore();
        assertEquals(0, val);
    }

    @Test
    public void UserDataConstructor2() throws Exception {
        int val = testData.getItemOneCount();
        assertEquals(0, val);
    }

    @Test
    public void UserDataConstructor3() throws Exception {
        int val = testData.getPathOne();
        assertEquals(0, val);
    }

    @Test
    public void UserDataConstructor4() throws Exception {
        String val = testData.getUsername();
        assertEquals("", val);
    }

    // UserData setter tests
    @Test
    public void setHighScore() throws Exception {
        int val = 5;
        testData.setHighScore(val);
        assertEquals(5, testData.getHighScore());
    }

    @Test
    public void setPath() throws Exception {
        int val = 1;
        testData.setPathOne(val);
        assertEquals(1, testData.getPathOne());
    }

    @Test
    public void setCount() throws Exception {
        int val = 3;
        testData.setItemOneCount(val);
        assertEquals(3, testData.getItemOneCount());
    }

    @Test
    public void setCharactername() throws Exception {
        testData.setCharacterName("Slappy");
        assertEquals("Slappy", testData.getCharacterName());
    }

    // UserData singleton behavior test
    @Test
    public void singletonTest() throws Exception {
        testData.setHighScore(5);
        testData2 = UserData.get();
        assertEquals(5, testData2.getHighScore());
        testData2.setHighScore(0);
    }




}