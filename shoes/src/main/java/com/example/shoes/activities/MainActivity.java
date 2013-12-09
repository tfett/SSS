package com.example.shoes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.shoes.R;
import com.example.shoes.fragment.MainFragment;
import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Settings;
import com.facebook.model.GraphUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
public class MainActivity extends FragmentActivity {
    private MainFragment mainFragment;
	 public final static String EXTRA_MESSAGE = "com.example.shoes.MESSAGE";
    public void testFacebook()
    {
        Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        Settings.addLoggingBehavior(LoggingBehavior.REQUESTS);

        Request request = Request.newGraphPathRequest(null, "/4", new Request.Callback() {
            @Override
            public void onCompleted(Response response) {
                if (response.getError() != null) {
                    Log.i("MainActivity", String.format("Error making request: %s", response.getError()));
                } else {
                    GraphUser user = response.getGraphObjectAs(GraphUser.class);
                    Log.i("APPDEBUG", String.format("Name is: %s", user.getName()));
                }
            }
        });
        request.executeAsync();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            testFacebook();
        }
        catch (Exception e)
        {
            Log.d("DEBUGGG",e.toString());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }

    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            //case R.id.action_settings2:
                //openSearch();
                //return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void invokeStatisticsActivity(View view) {
    	Intent intent = new Intent(this, StatisticsActivity.class);
    	String message = "fdsfsda";
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    public void invokeLoginActivity(View view) {
    	Intent intent = new Intent(this, LoginActivity.class);
//    	EditText ed = (EditText) findViewById(R.id.edit_message);
//    	String message = ed.getText().IO();
    	String message = "fdsfsda";
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    public void invokeGraphActivity(View view) {
    	Intent intent = new Intent(this, GraphActivity.class);
//    	EditText ed = (EditText) findViewById(R.id.edit_message);
//    	String message = ed.getText().IO();
    	String message = "fdsfsda";
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, LoginActivity.class);
//    	EditText ed = (EditText) findViewById(R.id.edit_message);
//    	String message = ed.getText().IO();
    	String message = "fdsfsda";
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
}
