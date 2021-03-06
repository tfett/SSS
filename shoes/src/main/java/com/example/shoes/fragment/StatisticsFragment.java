package com.example.shoes.fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoes.R;
import com.example.shoes.database.DatabaseHandler;
import com.example.shoes.helpers.Logger;
import com.example.shoes.model.Player;
import com.example.shoes.model.Statistics;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by zac on 12/11/13.
 */
public class StatisticsFragment extends MainFragment{

    protected DatabaseHandler db;
    protected  Button first_button;
    protected  Button second_button;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            pendingPublishReauthorization =
                    savedInstanceState.getBoolean(PENDING_PUBLISH_KEY, false);
        };


        view = inflater.inflate(R.layout.activity_statistics, container, false);

        //the default is to display the first half
        first_button = (Button)view.findViewById(R.id.button_stat_first);
        second_button = (Button)view.findViewById(R.id.button_stat_second);
        first_button.setEnabled(false);
        //add listerner for share button
        shareButton = (Button) view.findViewById(R.id.shareButton2);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishStory();
            }
        });
        db = new DatabaseHandler(getActivity());
        try
        {
            db.createDataBase();
        }
        catch (IOException e)
        {
            Log.d("APPDEBUG", e.toString());
        }
        db.openDataBase();
        Cursor cursor = db.getStat(Statistics.FIRST_HALF);
        //insert into text field
        try
        {
            Statistics stat;
            stat = new Statistics(cursor);
            setUpStatistics(stat);
            setUpSimilarPlayers(stat.getAvg_speedAsDouble());
        }
        catch (Exception e)
        {
            Logger.debug(e.toString());
        }

        return view;
    }
    public Bitmap takeScreenshot() {
        View u = view.findViewById(R.id.scroll_view_statistics);
        ScrollView z = (ScrollView) view.findViewById(R.id.scroll_view_statistics);
        int totalHeight = z.getChildAt(0).getHeight();
        int totalWidth = z.getChildAt(0).getWidth();
        Bitmap b = Bitmap.createBitmap(u.getWidth(), totalHeight, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        u.draw(c);
        return b;



    }
    protected void publishStory() {
        byte[] data = null;
        Bitmap bitmap = takeScreenshot();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        data = baos.toByteArray();
        Logger.debug(data.length);
        Session session = Session.getActiveSession();

        if (session != null){

            // Check for publish permissions
            List<String> permissions = session.getPermissions();
            if (!isSubsetOf(PERMISSIONS, permissions)) {
                pendingPublishReauthorization = true;
                Session.NewPermissionsRequest newPermissionsRequest = new Session
                        .NewPermissionsRequest(this, PERMISSIONS);
                session.requestNewPublishPermissions(newPermissionsRequest);
                return;
            }

            Bundle postParams = new Bundle();
//            postParams.putString("name", "Smart Soccer Shoes");
//            postParams.putString("caption", "App that helps you play soccer smarter");
//            postParams.putString("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
//            postParams.putString("link", "https://developers.facebook.com/android");
//
//            postParams.putByteArray("picture", data);
            Request.Callback callback= new Request.Callback() {
                public void onCompleted(Response response) {
                    JSONObject graphResponse = response
                            .getGraphObject()
                            .getInnerJSONObject();
                    String postId = null;
                    try {
                        postId = graphResponse.getString("id");
                    } catch (JSONException e) {
                        Logger.debug("JSON error " + e.getMessage());
                    }
                    FacebookRequestError error = response.getError();
                    if (error != null) {
                        Toast.makeText(getActivity()
                                .getApplicationContext(),
                                error.getErrorMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity()
                                .getApplicationContext(),
                                postId,
                                Toast.LENGTH_LONG).show();
                    }
                }
            };
            postParams.putByteArray("photo", data);
            postParams.putString("message", "From Smart Soccer Shoes app");
            postParams.putString("link", "http://www.google.com");

            Request request = new Request(session, "me/photos", postParams, HttpMethod.POST, callback);

            RequestAsyncTask task = new RequestAsyncTask(request);
            task.execute();
        }

    }
    protected void setUpStatistics(Statistics stat)
    {
        TextView distanceText = (TextView)view.findViewById(R.id.distance);
        distanceText.setText(stat.getDistance());

        TextView timeText = (TextView)view.findViewById(R.id.time);
        timeText.setText(stat.getTime());

        TextView avg_speedText = (TextView)view.findViewById(R.id.avg_speed);
        avg_speedText.setText(stat.getAvg_speed());

        TextView topText = (TextView)view.findViewById(R.id.top);
        topText.setText(stat.getTop_speed());

        TextView inZone = (TextView)view.findViewById(R.id.inZone);
        inZone.setText(stat.getInZone());

        TextView outZone = (TextView)view.findViewById(R.id.outZone);
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

                name = (TextView)view.findViewById(id);
                name.setText(player.getName());
                //set image
                id = getResources().getIdentifier(player_num+"img", "id", "com.example.shoes");
                img = (ImageView)view.findViewById(id);
                int imgID = getResources().getIdentifier("img_"+player.getImg_id(),"drawable", "com.example.shoes");
                img.setImageResource(imgID);

                //set speed
                id = getResources().getIdentifier(player_num+"avg_speed", "id", "com.example.shoes");
                speed = (TextView)view.findViewById(id);
                speed.setText(player.getAvg_speed());
                cursor.moveToNext();
            }
        }
        catch (Exception e)
        {
            Log.d("APPDEBUG", e.toString());
        }
    }


}
