package com.mygdx.game.database;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// allow references to String constants in VikingDbSchema.VikingTable
// use format 'VikingTable.Cols.USERNAME'
import com.mygdx.game.database.VikingDbSchema.VikingTable;

import java.util.ArrayList;

/**
 * Created by Eric on 10/23/2016.
 */

// helper class to handle tasks:
// check if database already exists
    // if not exists, create DB and create tables with initial data
    // if exists, open and check schema version
    // if old schema version, upgrade to current version
public class VikingBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "vikingBase.db";

    public VikingBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // create SQLite database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + VikingTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                VikingTable.Cols.USERNAME + " TEXT NOT NULL UNIQUE, " + // disallow duplicate usernames
                VikingTable.Cols.PASSWORD + " TEXT, " +
                VikingTable.Cols.FIRSTNAME + " TEXT, " +
                VikingTable.Cols.HIGHSCORE + " INTEGER, " +
                VikingTable.Cols.MONEY_BALANCE + " INTEGER, " +
                VikingTable.Cols.DAYS_PLAYED + " INTEGER, " +
                VikingTable.Cols.ITEM_ONE_COUNT + " INTEGER, " +
                VikingTable.Cols.ITEM_TWO_COUNT + " INTEGER, " +
                VikingTable.Cols.ITEM_THREE_COUNT + " INTEGER, " +
                VikingTable.Cols.PATH_ONE + " INTEGER, " +          // SQLite boolean is integer 0, 1
                VikingTable.Cols.PATH_TWO + " INTEGER, " +          // SQLite boolean is integer 0, 1
                VikingTable.Cols.PATH_THREE + " INTEGER, " +        // SQLite boolean is integer 0, 1
                VikingTable.Cols.PATH_FOUR + " INTEGER)"            // SQLite boolean is integer 0, 1
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Android Database Manager method
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }

}
