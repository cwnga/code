package com.codepath.apps.restclienttemplate;

import org.json.JSONObject;

import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserHandler {
	public User user;

	public User getCurrentUser(TwitterClient client) {
		user = new User();
		client.getCurrentUser(new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject arg0) {
				user = User.fromJson(arg0);
				callback();
			}
		});
		return user;
	}
	public void callback(){
		
	}

}
