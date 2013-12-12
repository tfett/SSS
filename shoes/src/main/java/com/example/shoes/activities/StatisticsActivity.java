package com.example.shoes.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoes.R;
import com.example.shoes.database.DatabaseHandler;
import com.example.shoes.fragment.StatisticsFragment;
import com.example.shoes.helpers.Logger;
import com.example.shoes.model.Player;
import com.example.shoes.model.Statistics;

public class StatisticsActivity extends FragmentActivity {
    public final static String EXTRA_MESSAGE = "com.example.shoes.MESSAGE";
    private StatisticsFragment statisticsFragment;
    protected DatabaseHandler db;
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this);
        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            statisticsFragment = new StatisticsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, statisticsFragment)
                    .commit();

        } else {
            // Or set the fragment from restored state info
            statisticsFragment = (StatisticsFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
	}

    protected void setUpStatistics(Statistics stat)
    {
        TextView distanceText = (TextView)findViewById(R.id.distance);
        distanceText.setText(stat.getDistance());

        TextView timeText = (TextView)findViewById(R.id.time);
        timeText.setText(stat.getTime());

        TextView avg_speedText = (TextView)findViewById(R.id.avg_speed);
        avg_speedText.setText(stat.getAvg_speed());

        TextView topText = (TextView)findViewById(R.id.top);
        topText.setText(stat.getTop_speed());

        TextView inZone = (TextView)findViewById(R.id.inZone);
        inZone.setText(stat.getInZone());

        TextView outZone = (TextView)findViewById(R.id.outZone);
        outZone.setText(stat.getOutZone());
    }
    protected void setUpSimilarPlayers(Double avgSpeed)
    {
        Cursor cursor = db.getSimiliarPlayers(avgSpeed);
        int length = cursor.getCount();
        try
        {
            for (int i=0; i < length; i++)
            {
                String index = Integer.toString(i+1);
                Player player;
                player = new Player(cursor);
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
public void firstHalfSelected(View view) {
   try{
       Button first_button = (Button)findViewById(R.id.button_stat_first);
       Button second_button = (Button)findViewById(R.id.button_stat_second);
       first_button.setEnabled(false);
       second_button.setEnabled(true);
       db.openDataBase();
       Cursor cursor = db.getStat(Statistics.FIRST_HALF);
       Statistics stat = new Statistics (cursor);
       setUpStatistics(stat);
       setUpSimilarPlayers(stat.getAvg_speedAsDouble());
   }
    catch (Exception e)
    {
        Logger.debug(e.toString());
    }
}
    public void secondHalfSelected(View view) {
        try{
            Button first_button = (Button)findViewById(R.id.button_stat_first);
            Button second_button = (Button)findViewById(R.id.button_stat_second);
            first_button.setEnabled(true);
            second_button.setEnabled(false);
            db.openDataBase();
            Cursor cursor = db.getStat(Statistics.SECOND_HALF);
            Statistics stat = new Statistics (cursor);
            setUpStatistics(stat);
            setUpSimilarPlayers(stat.getAvg_speedAsDouble());
        }
        catch (Exception e)
        {
            Logger.debug(e.toString());
        }
    }
}
