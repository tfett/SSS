package com.example.shoes.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.shoes.R;
import com.example.shoes.database.DatabaseHandler;
import com.example.shoes.fragment.StatisticsFragment;
import com.example.shoes.model.Statistics;

import java.io.IOException;

public class StatisticsActivity extends FragmentActivity {
    public final static String EXTRA_MESSAGE = "com.example.shoes.MESSAGE";
    private StatisticsFragment statisticsFragment;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		setContentView(R.layout.activity_statistics);
		Intent intent = getIntent();
		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //extract data from database

        DatabaseHandler db = new DatabaseHandler(this);
       try
       {
           db.createDataBase();
       }
       catch (IOException e)
       {
           Log.d("APPDEBUG", e.toString());
       }
        db.openDataBase();
        Cursor cursor = db.getStat(3);
        //insert into text field
        try
        {
            Statistics stat;
            stat = new Statistics(cursor);
            Log.d("success", stat.getAvg_speed());

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
        catch (Exception e)
        {
            Log.d("error", "empty curssor");
        }
	}
    public void invokeSimiliarityActivity(View view) {
        Intent intent = new Intent(this, SimilarityActivity.class);
        TextView avg_speedText = (TextView)findViewById(R.id.avg_speed);
        String message = (String)avg_speedText.getText();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
