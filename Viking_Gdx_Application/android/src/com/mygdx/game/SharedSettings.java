package com.mygdx.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by Eric on 10/28/2016.
 */

// used to store shared preferences for preference data shared across activities
public class SharedSettings extends PreferenceActivity {

    private static final String NAME = "username";
    private static final String DIFFICULTY = "difficulty";
    private static final int DEFAULT_DIFFICULTY = 0;
    private static final String MUSIC_ON = "music";
    private static final boolean DEFAULT_MUSIC_ON = true;
    private static final String SKIP_DIALOG = "skipDialog";
    private static final boolean DEFAULT_SKIP_DIALOG = false;
    private static final String DEFAULT_NAME = "defaultname";
    private static final String CHARACTER_NAME = "characterName";
    private static final String DEFAULT_CHARACTER_NAME = "Leif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // returns user name to requesting activity/fragment in order to query correct database record with the username
    public static String getName(Context context) {
        // getString takes two arguments--the key to retrieve, and a default value to return if the preference does not exist
        return PreferenceManager.getDefaultSharedPreferences(context).getString(NAME, DEFAULT_NAME);
    }

    public static SharedPreferences getPreferences(Context activity) {
        UserData data = UserData.get();
        return activity.getSharedPreferences(data.getUsername(), MODE_PRIVATE);
    }

    // returns the difficulty setting to requesting activity/fragment
    public static int getDifficulty(Context context) {
        return getPreferences(context).getInt(DIFFICULTY, DEFAULT_DIFFICULTY);
    }

    public static boolean getMusicOn(Context context) {
        return getPreferences(context).getBoolean(MUSIC_ON, DEFAULT_MUSIC_ON);
    }

    public static boolean getSkipDialog(Context context) {
        return getPreferences(context).getBoolean(SKIP_DIALOG, DEFAULT_SKIP_DIALOG);
    }

    public static String getCharacterName(Context context) {
        return getPreferences(context).getString(CHARACTER_NAME, DEFAULT_CHARACTER_NAME);
    }
}
