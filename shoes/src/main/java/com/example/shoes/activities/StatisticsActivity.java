package com.example.shoes.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.shoes.R;
import com.example.shoes.database.DatabaseHandler;
import com.example.shoes.fragment.StatisticsFragment;
import com.example.shoes.model.Statistics;

public class StatisticsActivity extends FragmentActivity {
    public final static String EXTRA_MESSAGE = "com.example.shoes.MESSAGE";
    private StatisticsFragment statisticsFragment;
    protected DatabaseHandler db;
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		setContentView(R.layout.activity_statistics);
//		Intent intent = getIntent();
//		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //extract data from database


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
//    public void invokeSimiliarityActivity(View view) {
//        Intent intent = new Intent(this, SimilarityActivity.class);
//        TextView avg_speedText = (TextView)findViewById(R.id.avg_speed);
//        String message = (String)avg_speedText.getText();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
}
