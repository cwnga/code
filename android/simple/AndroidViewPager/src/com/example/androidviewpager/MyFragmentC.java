package com.example.androidviewpager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class MyFragmentC extends Fragment {
	
	ProgressBar cProgress;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.fragment_c, container, false);
		
		String myTag = getTag();
		((AndroidViewPagerActivity)getActivity()).setTabFragmentC(myTag);
		
		cProgress = (ProgressBar)myFragmentView.findViewById(R.id.progressbar);
		
		return myFragmentView;
	}
	
	public void StartProgress(){
		new ProgressAsyncTask().execute();
	}
	
	public class ProgressAsyncTask extends 
		AsyncTask<Void, Integer, Void> {

		int myProgress;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			myProgress = 0;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			String TabOfFragmentB = ((AndroidViewPagerActivity)getActivity()).getTabFragmentB();
			MyFragmentB fragmentB = (MyFragmentB)getActivity()
					.getSupportFragmentManager()
					.findFragmentByTag(TabOfFragmentB);
			fragmentB.b_updateText("Progress finished!");
			
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			while(myProgress<100){
				myProgress++;
				publishProgress(myProgress);
					SystemClock.sleep(100);		
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			cProgress.setProgress(values[0]);
		}
		
	}

}
