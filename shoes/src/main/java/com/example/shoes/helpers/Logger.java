package com.example.shoes.helpers;

import android.util.Log;

/**
 * Created by zac on 12/11/13.
 */
public class Logger {
    public  static void debug(String msg)
    {
        Log.d("APPDEBUG", msg);
    }
    public  static void debug(int msg)
    {
        Log.d("APPDEBUG", Integer.toString(msg));
    }
}
