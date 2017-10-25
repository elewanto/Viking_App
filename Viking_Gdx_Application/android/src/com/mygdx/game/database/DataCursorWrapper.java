package com.mygdx.game.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mygdx.game.UserData;

/**
 * Created by Eric on 10/27/2016.
 */

// Cursor subclass using CursorWrapper to prevent repeating code

public class DataCursorWrapper extends CursorWrapper {
    public DataCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public UserData getData() {
        String username = getString(getColumnIndex(VikingDbSchema.VikingTable.Cols.USERNAME));
        String password = getString(getColumnIndex(VikingDbSchema.VikingTable.Cols.PASSWORD));
        String firstname = getString(getColumnIndex(VikingDbSchema.VikingTable.Cols.FIRSTNAME));
        int highScore = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.HIGHSCORE));
        int moneyBalance = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.MONEY_BALANCE));
        int daysPlayed = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.DAYS_PLAYED));
        int itemOneCount = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.ITEM_ONE_COUNT));
        int itemTwoCount = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.ITEM_TWO_COUNT));
        int itemThreeCount = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.ITEM_THREE_COUNT));
        int pathOne = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.PATH_ONE));
        int pathTwo = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.PATH_TWO));
        int pathThree = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.PATH_THREE));
        int pathFour = getInt(getColumnIndex(VikingDbSchema.VikingTable.Cols.PATH_FOUR));

        UserData data = UserData.get();
        data.setUsername(username);
        data.setPassword(password);
        data.setHighScore(highScore);
        data.setFirstName(firstname);
        data.setMoneyBalance(moneyBalance);
        data.setDaysPlayed(daysPlayed);
        data.setItemOneCount(itemOneCount);
        data.setItemTwoCount(itemTwoCount);
        data.setItemThreeCount(itemThreeCount);
        data.setPathOne(pathOne);
        data.setPathTwo(pathTwo);
        data.setPathThree(pathThree);
        data.setPathFour(pathFour);

        return data;
    }

}
