package com.example.shoes.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.shoes.R;

public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Intent intent = getIntent();
		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	}
}
