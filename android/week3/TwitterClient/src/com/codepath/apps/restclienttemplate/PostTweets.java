package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PostTweets extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_tweets);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_tweets, menu);
		return true;
	}

}
