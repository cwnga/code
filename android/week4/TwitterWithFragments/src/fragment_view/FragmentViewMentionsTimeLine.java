package fragment_view;

import java.util.ArrayList;

import adapter.TweetArrayAdapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.client.TwitterClient;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterHandler;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

public class FragmentViewMentionsTimeLine extends Fragment {

	protected User user;

	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(
				R.layout.fragment_view_mentions_timeline, container, false);
		getMentionsTimeLineList(null);
		return myFragmentView;

	}

	public void getMentionsTimeLineList(View v) {

		TwitterHandler twitterHandler = new TwitterHandler() {

			public void callback_getMensionsTimeline(ArrayList<Tweet> tweet) {
				TweetArrayAdapter adapter = new TweetArrayAdapter(getActivity()
						.getBaseContext(), tweet);
				ListView listView = (ListView) getActivity().findViewById(
						R.id.list_fragement_mentions_timeline);
				if (listView != null) {
					listView.setAdapter(adapter);
				}
			}

		};
		// twitterHandler.getHomeTimeline(com.codepath.apps.client.SimpleTwitterApp.getRestClient());
		TwitterClient client = (TwitterClient) TwitterClient.getInstance(
				TwitterClient.class, getActivity().getBaseContext());
		twitterHandler.getMensionsTimeline(client);

	}

}
