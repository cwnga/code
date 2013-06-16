package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;

import com.codepath.apps.restclienttemplate.models.Tweet;
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
import android.view.View;
import android.widget.ListView;

public class ListHomeTimeLineActivity extends OAuthLoginActivity<TwitterClient> {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_home_time_line);
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
	        		.detectDiskWrites().detectNetwork().penaltyLog().build());
	
		 this.getTweetListView(null);
		
	}
	


	public void getTweetListView(View v) {
		TwitterClient twitterClient = getClient();
		final Context context = this;
		if (twitterClient != null) {
			twitterClient.getHomeTimeline(
					new JsonHttpResponseHandler() {
				public void onSuccess(JSONArray arg0) {
					ArrayList<Tweet> tweetList = Tweet.fromJson(arg0);
					ListView listView = (ListView) findViewById(R.id.list_hometimeline);
					TweetArrayAdapter adapter = new TweetArrayAdapter(context,
							tweetList);
					listView.setAdapter(adapter);

				}

			});
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
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
	

}
