package com.example.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.loopj.android.http.*;
public class MainActivity extends Activity {
public AsyncHttpClient client;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 client = new AsyncHttpClient();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is Precent.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * @param v
	 */
	public void calculateTenPrecent(View v) {
		Double tipPersent = 0.1;
		   Log.v("ws","sss");
		this.client.get("http://www.google.com", new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
		        System.out.print(response);
		    	   Log.v("ws","ssssdfetwe");
		        System.out.print(response);
		    }
		});
		setCalculateTextView(tipPersent);
	}

	/**
	 * @param v
	 */
	public void calculateFifteenPrecent(View v) {
		Double tipPersent = 0.15;
		setCalculateTextView(tipPersent);
	}

	/**
	 * @param v
	 */
	public void calculateTwentyPrecent(View v) {
		Double tipPersent = 0.2;
		setCalculateTextView(tipPersent);
	}

	/**
	 * @param tipPersent
	 */
	public void setCalculateTextView(Double tipPersent) {
		EditText edText = (EditText) findViewById(R.id.amount1);
		Double amount = new Double("0");
		String edTextInputString = edText.getText().toString();
		Double tipAmount = new Double("0");
		if (edTextInputString.length() > 0) {
			amount = Double.parseDouble(edTextInputString);
			tipAmount = amount * tipPersent;

			DecimalFormat df = new DecimalFormat("0.00");

			String tipAmountString = df.format(tipAmount);

			TextView resultTextView = (TextView) findViewById(R.id.result);
			String resultString = getResources().getString(R.string.tip_is);
			resultString = resultString + tipAmountString;
			resultTextView.setText(resultString);
		}
	}

}
