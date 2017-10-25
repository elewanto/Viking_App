package com.mygdx.game.database;

/**
 * Created by Eric on 10/23/2016.
 */

public class VikingDbSchema {

    // inner class to define String constants needed to describe table definition
    public static final class VikingTable {

            public static final String NAME = "vikingtable";     // name of table in database

            public static final class Cols {                    // table columns
                public static final String USERNAME = "username";
                public static final String PASSWORD = "password";
                public static final String FIRSTNAME = "firstname";
                public static final String HIGHSCORE = "highscore";
                public static final String MONEY_BALANCE = "balance";
                public static final String DAYS_PLAYED = "daysplayed";
                public static final String ITEM_ONE_COUNT = "itemonecount";
                public static final String ITEM_TWO_COUNT = "itemtwocount";
                public static final String ITEM_THREE_COUNT = "itemthreecount";
                public static final String PATH_ONE = "pathone";
                public static final String PATH_TWO = "pathtwo";
                public static final String PATH_THREE = "paththree";
                public static final String PATH_FOUR = "pathfour";
            }
    }
}
