package com.example.shoes.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.shoes.R;
import com.example.shoes.graph.CoordinateReader;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.InputStream;
import java.util.ArrayList;

public class GraphActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.shoes.MESSAGE";
    public GraphActivity() {
        super();
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph);
		// Show the Up button in the action bar.
		Intent intent = getIntent();
        int half =intent.getIntExtra(EXTRA_MESSAGE,-1);
        int gpx_data = 0;
        if (half ==1)
            gpx_data = R.raw.first;
        if (half ==2)
            gpx_data = R.raw.second;
       try{
        GoogleMap map;
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        // Sets the map type to be "hybrid"
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //read coordinate

        //Draw user's gps movement data
        InputStream gpx_input = getResources().openRawResource(gpx_data);
        ArrayList<LatLng> gpx_points = CoordinateReader.getCoordinates(gpx_input);
        PolylineOptions gpxOptions = new PolylineOptions().addAll(gpx_points).width(3).color(Color.RED);
        Polyline gpx = map.addPolyline(gpxOptions);

        // Draw boundary
        InputStream boundary_input = getResources().openRawResource(R.raw.boundary);
        ArrayList<LatLng> boundary_points = CoordinateReader.getCoordinates(boundary_input);
        PolygonOptions boudaryOptions = new PolygonOptions().addAll(boundary_points).strokeWidth(10).strokeColor(Color.MAGENTA);
        Polygon  boundary = map.addPolygon(boudaryOptions);

        LatLng zoomPoint = new LatLng(45.511842,-122.686632);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(zoomPoint, 19);
        //map.animateCamera(yourLocation);
           map.moveCamera(yourLocation);
       }
       catch (Exception e)
       {
           Log.d("DEBUGGG", e.toString());
       }

//		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//		TextView textview = new TextView(this);
//		textview.setTextSize(40);
//		textview.setText(msg);
//		setContentView(textview);
	}



}
