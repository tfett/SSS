package com.example.shoes.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.shoes.IO;
import com.example.shoes.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;


public class MainActivity extends Activity {
	 public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.shoes",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (Exception e)
        {
            Log.d("DEBUGGG",e.toString());
        }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream is = getResources().openRawResource(R.raw.data);
        try
        {
          String content = IO.convertStreamToString(is);

        }
        catch (Exception e)
        {
            Log.d("DEBUGGG", "cannot read file");
        }
//        InputStream ins = (InputStream)getResources().openRawResource(R.raw.data);
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
