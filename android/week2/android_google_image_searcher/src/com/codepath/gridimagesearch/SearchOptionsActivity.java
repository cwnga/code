package com.codepath.gridimagesearch;

import java.lang.reflect.Array;
import java.util.Map;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchOptionsActivity extends Activity {
	SearchOptations searchOptations;
	String[] imageSize;
	String[] imageColor;
	String[] imageType;

	/**
	 * 
	 */
	protected void initStringArrayToArray() {
		//searchOptations = new SearchOptations();
		imageSize = getResources().getStringArray(R.array.image_size);
		imageColor = getResources().getStringArray(R.array.imgcolor);
		imageType = getResources().getStringArray(R.array.image_type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		searchOptations = getSearchOptations();
		initStringArrayToArray();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_options);
		// initStringArrayToArray();
		setSpinnerImageSizeData();
		setSpinnerColorFilterData();
		setSpinnerImageTypeData();

	}
	public SearchOptations getSearchOptations()
	{
		
		Intent i = getIntent();
		SearchOptations searchOptationsTmp = (SearchOptations) i
				.getSerializableExtra(SearchActivity.SearchOptationsIntentKey);
		if (searchOptationsTmp != null) {
			searchOptations = searchOptationsTmp;
		} else {
			Log.v("test", "new SearchOptations");
			searchOptations =  new SearchOptations();
		
		}
		return searchOptations;
		
	}

	/**
	 * @param v
	 */
	public void advanceSearch(View v) {
		Intent i = new Intent(this, SearchActivity.class);
		EditText edtextView = (EditText) findViewById(R.id.editTextSiteFilter);
		searchOptations.setAsSiteSearch(edtextView.getText().toString());
		i.putExtra(SearchActivity.SearchOptationsIntentKey, searchOptations);
		startActivity(i);
	}

	/**
	 * 
	 */
	public void setSpinnerImageSizeData() {
		setSpinnerInfo(R.id.spinner_image_size, R.array.image_size,
				new SpinnerImageSizeLister());

	}

	/**
	 * 
	 */
	public void setSpinnerColorFilterData() {
		setSpinnerInfo(R.id.spinner_color_filter, R.array.imgcolor,
				new SpinnerColorFilterLister());
	}

	/**
	 * 
	 */
	public void setSpinnerImageTypeData() {
		setSpinnerInfo(R.id.spinner_image_type, R.array.image_type,
				new SpinnerImageTypeLister());
	}

	/**
	 * @param rSpinnerId
	 * @param rArrayId
	 */
	public void setSpinnerInfo(int rSpinnerId, int rArrayId,
			OnItemSelectedListener onItemSelectedListener) {
		Spinner SpinnerS = (Spinner) findViewById(rSpinnerId);
		// 設定功能表項目陣列，使用createFromResource()
		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,
				rArrayId, android.R.layout.simple_spinner_item);
		// 設定選單
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 設定adapter
		SpinnerS.setAdapter(adapter);
		SpinnerS.setOnItemSelectedListener(onItemSelectedListener);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_options, menu);
		return true;
	}

	/**
	 * @author cwnga
	 *
	 */
	class SpinnerImageSizeLister implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			searchOptations.setImageSize(imageSize[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}

	}

	/**
	 * @author cwnga
	 *
	 */
	class SpinnerColorFilterLister implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			searchOptations.setImageColor(imageColor[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}

	}

	/**
	 * @author cwnga
	 *
	 */
	class SpinnerImageTypeLister implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			searchOptations.setImageType(imageType[arg2]);

		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}

	}
	/*
	 * public void displayToastSelectValue(String field, String value) {
	 * Toast.makeText(this, field + ":" + value, Toast.LENGTH_SHORT).show(); }
	 */
}
