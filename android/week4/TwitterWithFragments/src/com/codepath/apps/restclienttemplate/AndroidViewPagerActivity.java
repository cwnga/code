package com.codepath.apps.restclienttemplate;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.codepath.apps.adapter.TabsAdapter;

import fragment_activity.FragmentActivityHomeTimeLine;
import fragment_activity.FragmentActivityMentionsTimeLine;
import fragment_view.FragmentViewHomeTimeLine;
import fragment_view.FragmentViewMentionsTimeLine;

@SuppressLint("NewApi")
public class AndroidViewPagerActivity extends FragmentActivity {

	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mTabsAdapter.addTab(bar.newTab().setText("HOME"),
				FragmentViewHomeTimeLine.class, null);
		mTabsAdapter.addTab(bar.newTab().setText("MENTIONS"),
				FragmentViewMentionsTimeLine.class, null);

		if (savedInstanceState != null) {
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// super.onSaveInstanceState(outState);
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}

}