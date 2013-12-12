package com.example.shoes.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shoes.R;

/**
 * Created by zac on 12/11/13.
 */
public class GraphHalfSelectActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.shoes.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_half_select);
        Intent intent = getIntent();
//        String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

    }
    public void invokeGraphActivity(View view) {
       int id =  view.getId();
        int message = 0;
        if (id ==R.id.button_graph_first){
            message =1;
        }
        else if (id == R.id.button_graph_second){
            message =2;
        }
        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
