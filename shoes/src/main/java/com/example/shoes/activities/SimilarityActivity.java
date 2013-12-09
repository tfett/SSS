package com.example.shoes.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoes.R;
import com.example.shoes.database.DatabaseHandler;
import com.example.shoes.model.Player;

/**
 * Created by zac on 12/5/13.
 */
public class SimilarityActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similiarity);
        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Double avgSpeed = Double.parseDouble(msg) ;
        //extract data from database

        DatabaseHandler db = new DatabaseHandler(this);
        Cursor cursor = db.getSimiliarPlayers(avgSpeed);
        int length = cursor.getCount();
        Log.d("APPDEBUG", Integer.toString(length));
        try
        {
            for (int i=0; i < length; i++)
            {
                String index = Integer.toString(i+1);
                Player player;
                player = new Player(cursor);
                Log.d("APPDEBUG", player.getName());
//                bind the data to UI
                ImageView img;
                TextView name;
                TextView speed;
                int id;
                String player_num = "player"+index+"_";

                //set name
                id = getResources().getIdentifier(player_num+"name", "id", "com.example.shoes");
                name = (TextView)findViewById(id);
                name.setText(player.getName());
                //set image
                id = getResources().getIdentifier(player_num+"img", "id", "com.example.shoes");
                img = (ImageView)findViewById(id);
                int imgID = getResources().getIdentifier("img_"+player.getImg_id(),"drawable", "com.example.shoes");
                img.setImageResource(imgID);

                //set speed
                id = getResources().getIdentifier(player_num+"avg_speed", "id", "com.example.shoes");
                speed = (TextView)findViewById(id);
                speed.setText(player.getAvg_speed());





                cursor.moveToNext();
            }





        }
        catch (Exception e)
        {
            Log.d("APPDEBUG", e.toString());
        }
    }
}

