package fragment_view;

import java.util.ArrayList;

import org.json.JSONArray;

import com.codepath.apps.client.SimpleTwitterApp;
import com.codepath.apps.client.TwitterClient;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TweetArrayAdapter;
import com.codepath.apps.restclienttemplate.TwitterHandler;
import com.codepath.apps.restclienttemplate.models.User;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.codepath.apps.restclienttemplate.models.*;
import com.loopj.android.http.AsyncHttpResponseHandler;



public class FragmentViewHomeTimeLine extends Fragment{

	protected User user;

	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.fragment_view_home_timeline,
				container, false);
		getTweetListView(null);
		return myFragmentView;


	}

	public void getTweetListView(View v) {

		TwitterHandler twitterHandler = new TwitterHandler() {

			public void callback_getHomeTimeline(ArrayList<Tweet> tweet) {
				TweetArrayAdapter adapter = new TweetArrayAdapter(
						getActivity().getBaseContext(), tweet);
				ListView listView = (ListView) getActivity().findViewById(R.id.list_fragement_hometimeline);
				listView.setAdapter(adapter);
			}

		};
		//twitterHandler.getHomeTimeline(com.codepath.apps.client.SimpleTwitterApp.getRestClient());
		TwitterClient client = (TwitterClient) TwitterClient.getInstance(TwitterClient.class, getActivity().getBaseContext());
		twitterHandler.getHomeTimeline(client);
		client.getHomeTimeline(new AsyncHttpResponseHandler() {

			public void onSuccess(JSONArray a) {
				Log.v("test", a.toString());
			}
		});
	}



}
