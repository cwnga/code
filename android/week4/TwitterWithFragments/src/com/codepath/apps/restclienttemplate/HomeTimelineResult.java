package com.codepath.apps.restclienttemplate;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codepath.apps.restclienttemplate.models.Tweet;

import android.util.Log;

public class HomeTimelineResult implements Serializable {
	private static final long serialVersionUID = 2636389073439281291L;
	private String fullUrl;
	private String thumbUrl;
	protected ArrayList<Tweet> tweetArrayList;

	public HomeTimelineResult(String jsonStr) {
		try {
			// JSONObject myjson = new JSONObject(jsonStr);
			JSONArray myjson = new JSONArray(jsonStr);
			for (int i = 0; i < myjson.length(); i++) {
				JSONObject data = myjson.getJSONObject(i);
				Log.v("data", data.getString("text"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
