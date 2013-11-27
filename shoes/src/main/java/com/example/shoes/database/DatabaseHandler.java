package com.example.shoes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shoes.statistics.Statistics;

/**
 * Created by zac on 11/23/13.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "shoes";

    // Contacts table name
    public static final String TABLE_STATISTICS = "statistics";

    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String DISTANCE = "distance";
    public static final String KEY_TIME = "time";
    public static final String AVE_SPEED= "ave_speed";
    public static final String TOP_SPEED = "top_speed";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_STATISTICS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," +
                DISTANCE + " REAL," +
                KEY_TIME + " REAL," +
                AVE_SPEED + " REAL," +
                TOP_SPEED + " REAL" +
                ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);

        // Create tables again
        onCreate(db);
    }

    public void insertStat(Statistics stat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DISTANCE, stat.getDistance()); // distance
        values.put(KEY_TIME, stat.getTime());
        values.put(AVE_SPEED, stat.getAvg_speed());
        values.put(TOP_SPEED, stat.getTop_speed());

        // Inserting Row
        db.insert(TABLE_STATISTICS, null, values);
        db.close(); // Closing database connection
    }

    //select statistic based on id
    public Cursor getStat(int id) {
        String countQuery = "SELECT  * FROM " + TABLE_STATISTICS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);


        // return count
        cursor.moveToFirst();
        //distance, time, avg_speed, top_speed
        return cursor;
    }
}