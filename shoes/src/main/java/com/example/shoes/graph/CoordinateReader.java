package com.example.shoes.graph;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CoordinateReader {

    public static ArrayList<LatLng> getCoordinates(InputStream is) throws Exception {


        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        String raw = sb.toString();
        String lines []= raw.split("\n");
        ArrayList<LatLng> points = new ArrayList<LatLng>();
        Double x,y;
        for (int i=0 ; i < lines.length; i++)
        {
            String string []= lines[i].split(",");


            x = Double.valueOf(string[0]);
            y = Double.valueOf(string[1]);
            LatLng point = new LatLng(x,y);
            points.add(point);

        }
        return points;
    }

}