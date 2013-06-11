package com.codepath.gridimagesearch;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	SearchOptations searchOptations;// add for handle searchOptations
	final static String SearchOptationsIntentKey = "SearchOptationsObj";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		searchOptations =  getSearchOptations();
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent,
					int position, long rowId) {
				Intent i = new Intent(getApplicationContext(),
						ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
		});

		// /cwnga add
		gvResults.setOnScrollListener(new EndlessScrollListener(gvResults) {
			public void callback() {
				searchOptations.startNum = searchOptations.startNum + 10;
				// setImageViewFromSeach();
				setImageViewFromSeach();

			}

		});

		setEditTextValue();
	}
	public SearchOptations getSearchOptations()
	{
		
		Intent i = getIntent();
		SearchOptations searchOptationsTmp = (SearchOptations) i
				.getSerializableExtra(this.SearchOptationsIntentKey);
		if (searchOptationsTmp != null) {
			searchOptations = searchOptationsTmp;
		} else {
			Log.v("test", "new SearchOptations");
			searchOptations =  new SearchOptations();
		
		}
		return searchOptations;
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	/**
	 * 
	 */
	public void setEditTextValue()
	{	
		EditText editTextView = (EditText) findViewById(R.id.etQuery);
	
	    editTextView.setText(searchOptations.getQuery());
	
		
	}

	/**
	 * 
	 */
	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);

	}

	/**
	 * @param query
	 * @return
	 */
	public String getImageApiUrl(String query) {
		searchOptations = this.getSearchOptations();
		searchOptations.setQuery(query);
		String url = searchOptations.getImageApiUrl();
		return url;
	}

	/**
	 * 
	 */
	public void setImageViewFromSeach() {
		AsyncHttpClient client = new AsyncHttpClient();
		// https://ajax.googleapis.com/ajax/services/search/images?q=Android&v=1.0
		String imageApiUrl = this.getImageApiUrl(searchOptations.query);
		Log.v("imageApiUrl", imageApiUrl);
		client.get(imageApiUrl, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResults = null;
				try {
					imageJsonResults = response.getJSONObject("responseData")
							.getJSONArray("results");
					// imageResults.clear();
					imageAdapter.addAll(ImageResult
							.fromJSONArray(imageJsonResults));
					Log.d("DEBUG", imageResults.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @param v
	 */
	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT)
				.show();
		searchOptations.query = query;
		imageResults.clear();// reset
		setImageViewFromSeach();
	}

	/**
	 * @param v
	 */
	public void chageToOptionViews(View v) {
		Intent i = new Intent(this, SearchOptionsActivity.class);
		i.putExtra(SearchOptationsIntentKey, this.searchOptations);
		startActivity(i);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent i = new Intent(this, SearchOptionsActivity.class);
			EditText editTextView = (EditText) findViewById(R.id.etQuery);
			String query = editTextView.getText().toString();
			this.searchOptations.setQuery(query);
			i.putExtra(SearchOptationsIntentKey, this.searchOptations);
			startActivity(i);

		}
		return true;
	}

}
