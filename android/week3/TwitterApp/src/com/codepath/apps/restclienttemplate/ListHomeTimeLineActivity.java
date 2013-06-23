package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.oauth.OAuthLoginActivity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ListHomeTimeLineActivity extends OAuthLoginActivity<TwitterClient> {

	protected User user;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_home_time_line);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		//getTweetListView(null);

	}

	public void getTweetListView(View v) {

		TwitterHandler twitterHandler = new TwitterHandler() {

			public void callback_getHomeTimeline(ArrayList<Tweet> tweet) {
				TweetArrayAdapter adapter = new TweetArrayAdapter(
						getBaseContext(), tweet);
				ListView listView = (ListView) findViewById(R.id.list_hometimeline);
				listView.setAdapter(adapter);
			}

		};
		twitterHandler.getHomeTimeline(getClient());

		getClient().getHomeTimeline(new AsyncHttpResponseHandler() {

			public void onSuccess(JSONArray a) {
				Log.v("test", a.toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_home_time_line, menu);
		return true;
	}

	@Override
	public void onLoginFailure(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoginSuccess() {
		// TODO Auto-generated method stub

	}

	public void postTweet(MenuItem m) {
		Intent i = new Intent(this, PostTweetActivity.class);
		startActivity(i);
	}
	

}
