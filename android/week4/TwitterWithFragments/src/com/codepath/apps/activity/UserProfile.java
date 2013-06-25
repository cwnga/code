package com.codepath.apps.activity;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.Tabs;
import com.codepath.apps.restclienttemplate.R.layout;
import com.codepath.apps.restclienttemplate.R.menu;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;


public class UserProfile extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

	public void swtichToTab(MenuItem mi) {
		Intent i = new Intent(this, Tabs.class);
		startActivity(i);

	}


}
