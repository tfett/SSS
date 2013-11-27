package com.example.shoes.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.shoes.database.DatabaseHandler;
import com.example.shoes.R;
import com.example.shoes.statistics.Statistics;

public class StatisticsActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		Intent intent = getIntent();
		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //extract data from database

        DatabaseHandler db = new DatabaseHandler(this);
        Cursor cursor = db.getStat(3);
        //insert into text field
        try
        {
            Statistics stat;
            stat = new Statistics(cursor);
            Log.d("success", stat.getAvg_speed());

            TextView distanceText = (TextView)findViewById(R.id.distance);
            distanceText.setText("sdfdsafsdafsd");

            TextView timeText = (TextView)findViewById(R.id.time);
            timeText.setText(stat.getTime());

            TextView avg_speedText = (TextView)findViewById(R.id.avg_speed);
            avg_speedText.setText(stat.getAvg_speed());

            TextView topText = (TextView)findViewById(R.id.top);
            topText.setText(stat.getTop_speed());

        }
        catch (Exception e)
        {
            Log.d("error", "empty curssor");
        }
	}
}
