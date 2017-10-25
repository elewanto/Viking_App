package com.mygdx.game;

import android.content.Context;

import com.mygdx.game.GameLevels.MyGdxGame;

/**
 * Created by Eric on 10/26/2016.
 */

// updated to singleton class to persist during app session
// class contains a single row of user data, matching format in SQLite database

public class UserData {

    private static UserData sUserData;
    private String  mUsername;
    private String  mPassword;
    private String  mFirstName;
    // Saved in shared preferences for now
    private String  mCharacterName;
    private int     mHighScore;
    private int     mMoneyBalance;
    private int     mDaysPlayed;
    private int     mItemOneCount;
    private int     mItemTwoCount;
    private int     mItemThreeCount;
    private int     mPathOne;
    private int     mPathTwo;
    private int     mPathThree;
    private int     mPathFour;
    private boolean mIsMuted;
    private int     mDifficultyLevel;

    private static final String DEFAULT_CHARACTER_NAME = "Leif";

    public static UserData get(){
        if (sUserData == null) {
            sUserData = new UserData();
        }
        return sUserData;
    }

    // Only using a private constructor to make a singleton class
    private UserData() {
        mUsername = "";
        mPassword = "";
        mFirstName = "";
        mCharacterName = DEFAULT_CHARACTER_NAME;
        mHighScore = 0;
        mMoneyBalance = 0;
        mDaysPlayed = 0;
        mItemOneCount = 0;
        mItemTwoCount = 0;
        mItemThreeCount = 0;
        mPathOne = 0;
        mPathTwo = 0;
        mPathThree = 0;
        mPathFour = 0;
        mIsMuted = false;
        mDifficultyLevel = 0;
    }


    public void setIsMuted(Boolean bol){
        mIsMuted = bol;

    }

    public void setDifficultyLevel(int i){
        mDifficultyLevel = i;
    }
    // setters
    public void setUsername(String username) { mUsername = username; }

    public void setPassword(String password) { mPassword = password; }

    public void setFirstName(String fname) { mFirstName = fname; }

    public void setCharacterName(String characterName) { mCharacterName = characterName; }

    public void setHighScore(int score) { mHighScore = score; }

    public void setMoneyBalance(int money) { mMoneyBalance = money; }

    public void setDaysPlayed(int days) { mDaysPlayed = days; }

    public void setItemOneCount(int count) { mItemOneCount = count; }

    public void setItemTwoCount(int count) { mItemTwoCount = count; }

    public void setItemThreeCount(int count) { mItemThreeCount = count; }

    public void setPathOne(int path) { mPathOne = path; }

    public void setPathTwo(int path) { mPathTwo = path; }

    public void setPathThree(int path) { mPathThree = path; }

    public void setPathFour(int path) { mPathFour = path; }

    // getters
    public boolean getIsMuted(){return mIsMuted;}

    public int getDifficultyLevel(){return mDifficultyLevel;}

    public String getUsername() { return mUsername; }

    public String getPassword() { return mPassword; }

    public String getFirstName() { return mFirstName; }

    public String getCharacterName() { return mCharacterName; }

    public int getHighScore() { return mHighScore; }

    public int getMoneyBalance() { return mMoneyBalance; }

    public int getDaysPlayed() { return mDaysPlayed; }

    public int getItemOneCount() { return mItemOneCount; }

    public int getItemTwoCount() { return mItemTwoCount; }

    public int getItemThreeCount() { return mItemThreeCount; }

    public int getPathOne() { return mPathOne; }

    public int getPathTwo() { return mPathTwo; }

    public int getPathThree() { return mPathThree; }

    public int getPathFour() { return mPathFour; }

}
