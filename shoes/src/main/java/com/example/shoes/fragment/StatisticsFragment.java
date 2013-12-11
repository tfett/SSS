package com.example.shoes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shoes.R;
import com.example.shoes.helpers.Logger;

/**
 * Created by zac on 12/11/13.
 */
public class StatisticsFragment extends MainFragment{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            pendingPublishReauthorization =
                    savedInstanceState.getBoolean(PENDING_PUBLISH_KEY, false);
        };


        View view = inflater.inflate(R.layout.activity_statistics, container, false);


        //add listerner for share button
        shareButton = (Button) view.findViewById(R.id.shareButton2);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.debug("CALLED");
                publishStory();
            }
        });
        return view;

    }
}
