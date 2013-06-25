package com.codepath.apps.activity;

import com.codepath.apps.client.TwitterClient;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.Tabs;
import com.codepath.apps.restclienttemplate.TwitterHandler;
import com.codepath.apps.restclienttemplate.R.id;
import com.codepath.apps.restclienttemplate.R.layout;
import com.codepath.apps.restclienttemplate.R.menu;
import com.codepath.oauth.OAuthLoginActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class PostTweetActivity extends OAuthLoginActivity<TwitterClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_tweet);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_tweet, menu);
		return true;
	}

	/**
	 * @param v
	 */
	public void postTweet(View v) {
		String status = "";
		EditText edtTweets = (EditText) findViewById(R.id.edtTweets);
		status = edtTweets.getText().toString();
		if (status.length() > 0) {
			TwitterHandler twitterHandler = new TwitterHandler();
			twitterHandler.postTweet(status, getClient());
			Intent i = new Intent(this, Tabs.class);
			startActivity(i);
		}

	}

	@Override
	public void onLoginFailure(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoginSuccess() {
		// TODO Auto-generated method stub

	}

	public void swtichToTab(MenuItem mi) {
		Intent i = new Intent(this, Tabs.class);
		startActivity(i);

	}

}
