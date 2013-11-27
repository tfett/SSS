package com.example.shoes.statistics;

import android.database.Cursor;

import com.example.shoes.database.DatabaseHandler;

/**
 * Created by zac on 11/23/13.
 */
public class Statistics {

    public Statistics(Cursor c) throws Exception
    {
        if (c.getCount() < 1)
            throw new Exception("Empty cursor");
        this._id = c.getInt(c.getColumnIndexOrThrow(DatabaseHandler.KEY_ID));
        this.distance = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.DISTANCE));
        this.time = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.DISTANCE));
        this.avg_speed = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.AVE_SPEED));
        this.top_speed = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.TOP_SPEED));
    }
    public Statistics(int _id, double distance, double time, double avg_speed, double top_speed) {
        this._id = _id;
        this.distance = distance;
        this.time = time;
        this.avg_speed = avg_speed;
        this.top_speed = top_speed;
    }

    public Statistics(double distance, int time, double avg_speed, double top_speed) {

        this.distance = distance;
        this.time = time;
        this.avg_speed = avg_speed;
        this.top_speed = top_speed;
    }

    private int _id;
    private double distance;
    private double time;

    private double avg_speed;
    private double top_speed;
    /*
     *  SETTER AND GETTERS
     *
     */
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
    public String getTop_speed() {
        return Double.toString(top_speed);
    }

    public void setTop_speed(double top_speed) {
        this.top_speed = top_speed;
    }

    public String getAvg_speed() {
        return Double.toString(avg_speed);
    }

    public void setAvg_speed(double avg_speed) {
        this.avg_speed = avg_speed;
    }

    public String getTime() {
        return Double.toString(time);
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDistance() {
        return Double.toString(distance);
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


}
