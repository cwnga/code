package com.codepath.apps.restclienttemplate;

import com.codepath.apps.activity.PostTweetActivity;
import com.codepath.apps.activity.UserProfile;
import com.codepath.apps.adapter.TabManager;

import fragment_view.FragmentViewHomeTimeLine;
import fragment_view.FragmentViewMentionsTimeLine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;

/**
 * This demonstrates how you can implement switching between the tabs of a
 * TabHost through fragments.  It uses a trick (see the code below) to allow
 * the tabs to switch between fragments instead of simple views.
 */
public class Tabs extends FragmentActivity {
    private TabHost mTabHost;
    private TabManager mTabManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabs);
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);

        mTabHost.setCurrentTab(0);
        mTabManager.addTab(mTabHost.newTabSpec("Fragment1").setIndicator("＠HOME",this.getResources().getDrawable(android.R.drawable.ic_dialog_alert)),
        		FragmentViewHomeTimeLine.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("Fragment2").setIndicator("@METIONS",this.getResources().getDrawable(android.R.drawable.ic_lock_lock)),
        		FragmentViewMentionsTimeLine.class, null);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm); //先取得螢幕解析度
        int screenWidth = dm.widthPixels;   //取得螢幕的寬


        TabWidget tabWidget = mTabHost.getTabWidget();   //取得tab的物件
        int count = tabWidget.getChildCount();   //取得tab的分頁有幾個
        if (count > 3) {   //如果超過三個就來處理滑動
            for (int i = 0; i < count; i++) {
                tabWidget.getChildTabViewAt(i).setMinimumWidth((screenWidth) / 3);//設定每一個分頁最小的寬度
            }
        }

    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabs, menu);
		return true;
	}
	
	public void switchToProfile(MenuItem m)
	{
		Intent i = new Intent(this, UserProfile.class);
		startActivity(i);
	}
	
	public void switchToPostTweetActivity(MenuItem m)
	{
		Intent i = new Intent(this, PostTweetActivity.class);
		startActivity(i);
	}

}
