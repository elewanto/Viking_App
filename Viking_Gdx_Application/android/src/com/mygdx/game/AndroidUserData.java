package com.mygdx.game;

import android.content.Context;

import com.mygdx.game.GameLevels.MyGdxGame;
import com.mygdx.game.Interfaces.SharedUserData;

/**
 * Created by Eric on 11/2/2016.
 */

public class AndroidUserData implements SharedUserData {

    private final UserData mUserData;
    private Context context;

    public AndroidUserData(Context context) {
        mUserData = UserData.get();
        this.context = context;
    }



    // setters
    public void setHighScore(int score) {
        String mUserName = mUserData.getUsername();
        mUserData.setHighScore(score);
        // update database
        VikingData vikingData = VikingData.get(context);
        vikingData.updateRow(mUserData);
    }

    public void setPathOne(int path) {
        String mUserName = mUserData.getUsername();
        mUserData.setPathOne(path);
        // update database
        VikingData vikingData = VikingData.get(context);
        vikingData.updateRow(mUserData);
    }

    public void setPathTwo(int path) {
        String mUserName = mUserData.getUsername();
        mUserData.setPathTwo(path);
        // update database
        VikingData vikingData = VikingData.get(context);
        vikingData.updateRow(mUserData);
    }

    public void setPathThree(int path) {
        String mUserName = mUserData.getUsername();
        mUserData.setPathThree(path);
        // update database
        VikingData vikingData = VikingData.get(context);
        vikingData.updateRow(mUserData);
    }

    public void setDifficultyLevel(int level){

        mUserData.setDifficultyLevel(level);

    }

    public void setIsMuted(boolean bol){
        mUserData.setIsMuted(bol);
    }

    public void setPathFour(int path) {
        String mUserName = mUserData.getUsername();
        mUserData.setPathFour(path);
        // update database
        VikingData vikingData = VikingData.get(context);
        vikingData.updateRow(mUserData);
    }


    // getters
    public int getHighScore() {
        return mUserData.getHighScore();
    }

    public String getCharacterName() {
        return mUserData.getCharacterName().toString();}

    public int getPathOne() {
        return mUserData.getPathOne();
    }

    public int getPathTwo() {
        return mUserData.getPathTwo();
    }

    public int getPathThree() {
        return mUserData.getPathThree();
    }

    public int getPathFour() {
        return mUserData.getPathFour();
    }

    public boolean getIsMuted(){
        return SharedSettings.getMusicOn(context);
    }

    public int getDifficultyLevel(){return SharedSettings.getDifficulty(context);}



}
