package com.codepath.apps.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {

	private static final long serialVersionUID = 1L;
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
	public static final String REST_URL = "https://api.twitter.com/1.1";
	public static final String REST_CONSUMER_KEY = "uH7IibLGx8MfIg7RPjyqw";
	public static final String REST_CONSUMER_SECRET = "HD3wUix3xBJQBzhekedUmQEmkJVDpQFDs1l5rO2C2c";
	public static final String REST_CALLBACK_URL = "http://anson.com";

	public Context context;

	public TwitterClient(Context context) {

		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY,
				REST_CONSUMER_SECRET, REST_CALLBACK_URL);
		this.context = context;

	}
	
	public void postTweet(String status, AsyncHttpResponseHandler handler) 
	{
		String apiUrl = "statuses/update.json";
		RequestParams params = new RequestParams();
		try {
			params.put("status", URLEncoder.encode(status,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.v(this.getClass().getName(), "getHomeTimeline");
		post(apiUrl, handler, params);
		
	}
	/**
	 * @param handler
	 */
	public void getCurrentUser(AsyncHttpResponseHandler handler) 
	{
		String apiUrl = "account/verify_credentials.json";
		RequestParams params = new RequestParams();
		Log.v(this.getClass().getName(), "getHomeTimeline");
		get(apiUrl, handler, params);
		
	}

	/**
	 * @param handler
	 */
	public void getHomeTimeline(AsyncHttpResponseHandler handler) {
		String apiUrl = "statuses/home_timeline.json";
		RequestParams params = new RequestParams();
		params.put("format", "json");
		Log.v(this.getClass().getName(), "getHomeTimeline");
		get(apiUrl, handler, params);
	}
	
	/**
	 * @param handler
	 */
	public void getMensionsTimeline(AsyncHttpResponseHandler handler) {
		String apiUrl = "statuses/mentions_timeline.json";
		RequestParams params = new RequestParams();
		params.put("format", "json");
		params.put("count","25");
		
		Log.v(this.getClass().getName(), "getHomeTimeline");
		get(apiUrl, handler, params);
	}

	/**
	 * @param apiUrl
	 * @param handler
	 * @param params
	 */
	public void get(String apiUrl, AsyncHttpResponseHandler handler,
			RequestParams params) {
		String fullApiUrl = getApiUrl(apiUrl);
		params.put("format", "json");
		client.get(fullApiUrl, params, handler);
	}
	

	/**
	 * @param apiUrl
	 * @param handler
	 * @param params
	 */
	public void post(String apiUrl, AsyncHttpResponseHandler handler,
			RequestParams params) {
		String fullApiUrl = getApiUrl(apiUrl);
		params.put("format", "json");
		client.post(fullApiUrl, params, handler);
	}



	/*
	 * 1. Define the endpoint URL with getApiUrl and pass a relative path to the
	 * endpoint i.e getApiUrl("statuses/home_timeline.json"); 2. Define the
	 * parameters to pass to the request (query or body) i.e RequestParams
	 * params = new RequestParams("foo", "bar"); 3. Define the request method
	 * and make a call to the client i.e client.get(apiUrl, params, handler);
	 * i.e client.post(apiUrl, params, handler);
	 */
}