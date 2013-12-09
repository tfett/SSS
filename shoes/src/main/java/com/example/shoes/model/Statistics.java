package com.example.shoes.model;

import android.database.Cursor;

import com.example.shoes.database.DatabaseHandler;

/**
 * Created by zac on 11/23/13.
 */
public class Statistics {
    private int _id;
    private  int half;
    private double distance;
    private double time;
    private double avg_speed;
    private double top_speed;
    private int inZone;
    private int outZone;

    public Statistics(Cursor c) throws Exception
    {
        if (c.getCount() < 1)
            throw new Exception("Empty cursor");
        this._id = c.getInt(c.getColumnIndexOrThrow(DatabaseHandler.GAMEID));
        this.distance = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.DISTANCE));
        this.time = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.TIME))/60; //convert to minute;
        this.avg_speed = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.STATS_AVGSPEED)) * 1.6;//convert to km
        this.top_speed = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.TOPSPEED)) * 1.6;
        this.half = c.getInt(c.getColumnIndexOrThrow(DatabaseHandler.HALF));
        Double tmp =  c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.INZONE));
        this.inZone = tmp.intValue();
        this.outZone = 100- this.inZone;
    }

   //convert double to string, ignore the fractional part
    private String doubleToString(double val)
    {
        return String.format("%.2f", val);

    }
    /*
     * GETTERS
     *
     */
    public int get_id() {
        return _id;
    }

    public String getHalf() {
        return Integer.toString(half);
    }
    public String getOutZone() {
        return Integer.toString(outZone);
    }

    public String getInZone() {
        return Integer.toString(inZone);
    }

    public String getTop_speed() {
        return doubleToString(top_speed);
    }

    public String getAvg_speed() {
        return doubleToString(avg_speed);
    }

    public String getTime() {
        return doubleToString(time);
    }

    public String getDistance() {
        return doubleToString(distance);
    }



}
