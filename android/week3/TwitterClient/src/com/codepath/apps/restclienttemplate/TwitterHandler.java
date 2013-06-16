package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TwitterHandler {
	public User user;

	public void getCurrentUser(TwitterClient client) {
		user = new User();
		client.getCurrentUser(new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject arg0) {
				user = User.fromJson(arg0);
				callback_getCurrentUser(user);
				callback(arg0);
			}
		});

	}

	public void postTweet(String status, TwitterClient client) {
		client.postTweet(status, new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject arg0) {
				callbackPostTweet(arg0);
			}
		});

	}
	public void callbackPostTweet(JSONObject arg0) {

	}

	public void getHomeTimeline(TwitterClient client) {

		client.getHomeTimeline(new JsonHttpResponseHandler() {
			public void onSuccess(JSONArray arg0) {
				Log.v("onSuccess JSONArray arg0", arg0.toString());
				ArrayList<Tweet> tweet = Tweet.fromJson(arg0);
				callback_getHomeTimeline(tweet);
				callback(arg0);
			}
			public void onSuccess(JSONObject arg0) {
				Log.v("onSuccess JSONObject arg0", arg0.toString());
			
			}
		});

	}

	public void callback(Object obj) {

	}

	public void callback_getCurrentUser(User user) {

	}

	public void callback_getHomeTimeline(ArrayList<Tweet> tweet) {

	}
}
