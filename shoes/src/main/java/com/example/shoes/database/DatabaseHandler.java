package com.example.shoes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shoes.R;
import com.example.shoes.model.Statistics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zac on 11/23/13.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    public static  String DB_PATH = "";
    public static  String DB_NAME = "shoes";

    // statistics table
    public static final String TABLE_STATISTICS = "Stats";
    public static final String GAMEID = "GameID";
    public static final String HALF = "Half";
    public static final String DISTANCE = "Distance";
    public static final String TIME = "Time";
    public static final String TOPSPEED = "TopSpeed";
    public static final String INZONE = "InZone";
    public static final String OUTZONE = "OutZone";
    public static final String STATS_AVGSPEED = "AvgSpeed";

    // player table
    public static final String TABLE_PLAYERS= "players";
    public static final String NAME = "name";
    public static final String IMG = "img";
    public static final String PLAYER_AVGSPEED = "avgspeed";

    //number of similiar players will return
    public static final int numberOfPlayers = 3;

    private SQLiteDatabase mDataBase;
    private  Context mContext;
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }
//    CREATE Table Stats( GameID int, Half int, Distance int, Time time, AvgSpeed float, TopSpeed float, InZone float, OutZone float);
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
      //copy the preloaded database over
    }
    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }
    //Open the database, so we can query it
    public boolean openDataBase()
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        mContext.deleteDatabase(DB_NAME);
        // Create tables again
        onCreate(db);
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException {

//        getResources().openRawResource(R.raw.gpx);
        InputStream mInput = mContext.getResources().openRawResource(R.raw.shoes);
//        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            Log.d("APPDEBUG", "read");
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();

    }

    public void insertStat(Statistics stat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DISTANCE, stat.getDistance()); // distance
        values.put(TIME, stat.getTime());
        values.put(STATS_AVGSPEED, stat.getAvg_speed());
        values.put(INZONE, stat.getTop_speed());

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
    public Cursor getSimiliarPlayers(double avg_speed)
    {
        String countQuery = "select abs(avgspeed-"+Double.toString(avg_speed)+") as diff,name,avgspeed,img from players order by diff asc limit "+ Integer.toString(numberOfPlayers);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        cursor.moveToFirst();
        //distance, time, avg_speed, top_speed
        return cursor;
    }
}