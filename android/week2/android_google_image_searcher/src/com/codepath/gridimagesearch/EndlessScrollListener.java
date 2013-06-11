package com.codepath.gridimagesearch;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;

public class EndlessScrollListener implements OnScrollListener {

	private GridView gridView;
	private boolean isLoading;
	private boolean hasMorePages;
	private int pageNumber = 0;

	private boolean isRefreshing;

	public EndlessScrollListener(GridView gridView) {
		this.gridView = gridView;
		this.isLoading = false;
		this.hasMorePages = true;
		
	}

	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		int a = gridView.getLastVisiblePosition() + 1 ;

		Log.v("getLastVisiblePosition", String.valueOf(a) );
		Log.v("totalItemCount", String.valueOf(totalItemCount) );
		Log.v("isLoading", String.valueOf(isLoading) );
		int lastVisiblePosition = gridView.getLastVisiblePosition() + 1;
		if (lastVisiblePosition == totalItemCount
				&& !isLoading && lastVisiblePosition > 0 ) {
			
			isLoading = true;

			callback();
			if (hasMorePages && !isRefreshing) {
				isRefreshing = true;
				callback();
				
			}
		} else {
			isLoading = false;
		}

	}

	public void callback()
	{
		
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	public void noMorePages() {
		this.hasMorePages = false;
	}

	public void notifyMorePages() {
		isRefreshing = false;
		pageNumber = pageNumber + 1;
	}

}