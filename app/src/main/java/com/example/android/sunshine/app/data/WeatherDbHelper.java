package com.example.android.sunshine.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.sunshine.app.data.WeatherContract.LocationEntry;
import com.example.android.sunshine.app.data.WeatherContract.WeatherEntry;
import com.example.android.sunshine.app.data.WeatherContract.DescriptionEntry;

/**
 * Created by kenzhang on 15-11-12.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "weather.db";

    public WeatherDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        // Create a table to hold locations.
        final String SQL_CREATE_LOCATION_TABLE =
                "CREATE TABLE " + LocationEntry.TABLE_NAME +
                "(" + LocationEntry._ID + " INTEGER PRIMARY KEY," +
                      LocationEntry.COLUMN_LOCATION_SETTING_CITY + " TEXT NOT NULL, " +
                      LocationEntry.COLUMN_LOCATION_SETTING_COUNTRY + " TEXT NOT NULL," +
                      LocationEntry.COLUMN_COORD_LAT + " REAL NOT NULL," +
                      LocationEntry.COLUMN_COORD_LONG + " REAL NOT NULL" +")";

        final String SQL_CREATE_WEATHER_TABLE =
                "CREATE TABLE " + WeatherEntry.TABLE_NAME +
                        "(" + WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                              WeatherEntry.COLUMN_LOC_KEY + " INTEGER NOT NULL," +
                              WeatherEntry.COLUMN_DATE + " INTEGER NOT NULL," +
                              WeatherEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL," +
                              WeatherEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL," +

                              WeatherEntry.COLUMN_MIN_TEMP + " REAL NOT NULL," +
                              WeatherEntry.COLUMN_MAX_TEMP + " REAL NOT NULL," +

                              WeatherEntry.COLUMN_HUMIDITY + " REAL NOT NULL, " +
                              WeatherEntry.COLUMN_PRESSURE + " REAL NOT NULL, " +
                              WeatherEntry.COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                              WeatherEntry.COLUMN_DEGREES + " REAL NOT NULL, " +
                              WeatherEntry.COLUMN_ICON_ID+ " TEXT NOT NULL, " +
                              WeatherEntry.COLUMN_USER_DESC + " TEXT," +
                        // Set up the location column as a foreign key to location table.
                        " FOREIGN KEY (" + WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
                        LocationEntry.TABLE_NAME + " (" + LocationEntry._ID + "), " +

                        // To assure the application have just one weather entry per day
                        // per location, it's created a UNIQUE constraint with REPLACE strategy
                        " UNIQUE (" + WeatherEntry.COLUMN_DATE + ", " +
                        WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_DESCRIPTION_TABLE =
                "CREATE TABLE " + DescriptionEntry.TABLE_NAME +
                        "(" + DescriptionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DescriptionEntry.COLUMN_DATE + " INTEGER NOT NULL," +
                        DescriptionEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
                        DescriptionEntry.COLUMN_CITY + " TEXT NOT NULL," +
                        DescriptionEntry.COLUMN_COUNTRY + " TEXT NOT NULL," +
                        // To assure that users won't enter the same description twice on the same day
                        " UNIQUE (" + DescriptionEntry.COLUMN_DATE + ", " +
                        DescriptionEntry.COLUMN_DESCRIPTION + ") ON CONFLICT REPLACE);";

                        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
                        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
                        sqLiteDatabase.execSQL(SQL_CREATE_DESCRIPTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
