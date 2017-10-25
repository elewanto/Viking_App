package com.mygdx.game.Interfaces;

/**
 * Created by Eric on 11/2/2016.
 */

public interface SharedUserData {

    public void setHighScore(int score);
    public void setPathOne(int path);
    public void setPathTwo(int path);
    public void setPathThree(int path);
    public void setPathFour(int path);
    public void setDifficultyLevel(int difficulty);
    public void setIsMuted(boolean bol);

    public boolean getIsMuted();
    public int getDifficultyLevel();
    public String getCharacterName();
    public int getHighScore();
    public int getPathOne();
    public int getPathTwo();
    public int getPathThree();
    public int getPathFour();
}
