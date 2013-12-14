package com.example.shoes.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.shoes.R;
import com.example.shoes.fragment.LoginFragment;
import com.example.shoes.helpers.Logger;
import com.facebook.Session;
import com.facebook.SessionState;

public class LoginActivity extends FragmentActivity{
    public final static String EXTRA_MESSAGE = "com.example.shoes.MESSAGE";
    private LoginFragment loginFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            loginFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, loginFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            loginFragment = (LoginFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }

	}
//    @Override
    protected void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
        } else if (state.isClosed()) {
            Logger.debug("log out in logout");
        }
    }
}
