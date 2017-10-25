package com.mygdx.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mygdx.game.database.DataCursorWrapper;
import com.mygdx.game.database.VikingBaseHelper;
import com.mygdx.game.database.VikingDbSchema.VikingTable;

/**
 * Created by Eric on 10/23/2016.
 */

// VikingData references the entire database (all rows in table; compare to UserData which
// contains the values from a single row selected from VikingData

//similar functionality to Nerd Ranch CrimeLab class

// singleton class (reference ch 9 Nerd Ranch)
    // singleton class exists as long as app stays in memory
    // private constructor allows only one instance of class, so if get() is called again,
    // the instance will be returned, otherwise the instance will be created


public class VikingData {

    private static VikingData sVikingData;          // prefix s for static variable

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private static final String TAG = "VikingData SQL" ;

    // creates instance if doesn't exist; otherwise returns existing instance
    public static VikingData get(Context context) {
        if (sVikingData == null) {
            sVikingData = new VikingData(context);
        }
        return sVikingData;
    }

    // private constructor to prevent other classes from instantiating
    private VikingData(Context context) {
        mContext = context.getApplicationContext();
        // opens vikingBase.db and creates new database file if doesn't exist
        // calls onCreate(SQLiteDatabase) if first time database has been created; saves out latest version number
        // if not first time, checks version number in database, and if version is higher, calls onUpgrade()
        mDatabase = new VikingBaseHelper(mContext).getWritableDatabase();
    }

    // ContentValues is a key-value store class designed to store data SQLite can hold; we
    // use this to make writes and updates to the database
    private static ContentValues getContentValues(UserData userData) {
        ContentValues values = new ContentValues();

        values.put(VikingTable.Cols.USERNAME, userData.getUsername());
        values.put(VikingTable.Cols.PASSWORD, userData.getPassword());
        values.put(VikingTable.Cols.FIRSTNAME, userData.getFirstName());
        values.put(VikingTable.Cols.HIGHSCORE, userData.getHighScore());
        values.put(VikingTable.Cols.MONEY_BALANCE, userData.getMoneyBalance());
        values.put(VikingTable.Cols.DAYS_PLAYED, userData.getDaysPlayed());
        values.put(VikingTable.Cols.ITEM_ONE_COUNT, userData.getItemOneCount());
        values.put(VikingTable.Cols.ITEM_TWO_COUNT, userData.getItemTwoCount());
        values.put(VikingTable.Cols.ITEM_THREE_COUNT, userData.getItemThreeCount());
        values.put(VikingTable.Cols.PATH_ONE, userData.getPathOne());
        values.put(VikingTable.Cols.PATH_TWO, userData.getPathTwo());
        values.put(VikingTable.Cols.PATH_THREE, userData.getPathThree());
        values.put(VikingTable.Cols.PATH_FOUR, userData.getPathFour());

        return values;
    }

    public void insertRow(UserData data) {
        // get SQLite compatible values from data object
        ContentValues values = getContentValues(data);
        // insert new row into SQLite database
        mDatabase.insert(VikingTable.NAME, null, values);
        Log.d(TAG, "row inserted into SQLite database");
    }

    public void updateRow(UserData data) {
        String username = data.getUsername();
        // get database compatible values from data object
        ContentValues values = getContentValues(data);
        // update existing row in database by matching username key
        // use the ? format to prevent SQL injection attack; forces to treat as string, and not
        // possible malicious SQL code
        mDatabase.update(VikingTable.NAME, values, VikingTable.Cols.USERNAME + " = ?",
                            new String[] { username });
        Log.d(TAG, "row updated in SQLite database");

    }


    // query to retrieve row of data from database
    public UserData getRow(String username, String password) {

        DataCursorWrapper cursor = queryData(VikingTable.Cols.USERNAME + " = ? AND " + VikingTable.Cols.PASSWORD + " = ?",
                new String[] { username, password });

        //DataCursorWrapper cursor = queryData(VikingTable.Cols.USERNAME + " = ?", new String[] { username });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getData();
        } finally {
            cursor.close();
        }
    }

    // overloaded getRow method with only one username argument
    // query to retrieve row of data from database
    public UserData getRow(String username) {

        DataCursorWrapper cursor = queryData(VikingTable.Cols.USERNAME + " = ?", new String[] { username });

        //DataCursorWrapper cursor = queryData(VikingTable.Cols.USERNAME + " = ?", new String[] { username });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getData();
        } finally {
            cursor.close();
        }
    }

    // query to check if username exists in database
    public UserData checkUsername(String username) {

        DataCursorWrapper cursor = queryData(VikingTable.Cols.USERNAME + " = ?", new String[] { username });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getData();
        } finally {
            cursor.close();
        }
    }

    private DataCursorWrapper queryData(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                VikingTable.NAME,
                null,   // select all columns
                whereClause,
                whereArgs,
                null,   // groupBy
                null,   // having
                null   // orderBy
        );

        return new DataCursorWrapper(cursor);
    }

    public void closeIt() {
        mDatabase.close();
    }



}
