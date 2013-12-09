package com.example.shoes.model;

import android.database.Cursor;

import com.example.shoes.database.DatabaseHandler;

/**
 * Created by zac on 12/5/13.
 */
public class Player {

    public Player(Cursor c) throws Exception
    {
        if (c.getCount() < 1)
            throw new Exception("Empty cursor");
        this.name = c.getString(c.getColumnIndexOrThrow(DatabaseHandler.NAME));
        this.avg_speed = c.getDouble(c.getColumnIndexOrThrow(DatabaseHandler.PLAYER_AVGSPEED));
        this.img_id = c.getString(c.getColumnIndexOrThrow(DatabaseHandler.IMG));
    }

    public String getAvg_speed() {
        return Double.toString(avg_speed);
    }

    public void setAvg_speed(double avg_speed) {
        this.avg_speed = avg_speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }
    private String name;

    private double avg_speed;
    private String img_id;

}