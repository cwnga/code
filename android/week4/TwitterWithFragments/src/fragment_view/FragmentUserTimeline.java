package fragment_view;

import java.util.ArrayList;

import org.json.JSONArray;

import adapter.TweetArrayAdapter;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.codepath.apps.client.TwitterClient;
import com.codepath.apps.restclienttemplate.ImageCacheHandler;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterHandler;
import com.codepath.apps.restclienttemplate.UserHandler;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class FragmentUserTimeline extends Fragment {
	ImageCacheHandler imageCacheHandler;
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.fragment_view_home_timeline,
				container, false);
		getUserTimelineView(null);
		imageCacheHandler = new ImageCacheHandler();
		return myFragmentView;

	}


	public void getUserTimelineView(View v) {

		TwitterHandler twitterHandler = new TwitterHandler() {

			@SuppressLint("NewApi")
			public void callback_getUserTimeline(ArrayList<Tweet> tweet) {
				TweetArrayAdapter adapter = new TweetArrayAdapter(
						getActivity().getBaseContext(), tweet);
				ListView listView = (ListView) getActivity().findViewById(R.id.list_fragement_hometimeline);
				listView.setAdapter(adapter);
			}

		};
		//twitterHandler.getHomeTimeline(com.codepath.apps.client.SimpleTwitterApp.getRestClient());
		TwitterClient client = (TwitterClient) TwitterClient.getInstance(TwitterClient.class, getActivity().getBaseContext());
		twitterHandler.getUserTimeline(client);
		client.getUserTimeline(new AsyncHttpResponseHandler() {

			public void onSuccess(JSONArray a) {
				Log.v("test", a.toString());
			}
		});
	}


}
