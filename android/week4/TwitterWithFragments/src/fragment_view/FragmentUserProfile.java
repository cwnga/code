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

public class FragmentUserProfile extends Fragment {
	ImageCacheHandler imageCacheHandler;
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.fragment_user_profile,
				container, false);
		getUserProfileView(null);
		imageCacheHandler = new ImageCacheHandler();
		return myFragmentView;

	}

	public void getUserProfileView(View v) {

	
		UserHandler userHandler = new UserHandler() {

			@SuppressLint("NewApi")
			public void callback_getCurrentUser() {
				ImageView userImage = (ImageView) getActivity().findViewById(
						R.id.userImage);

				 TextView userName = (TextView) getActivity().findViewById(
						R.id.user_name);
				if (user!=null && user.getName()!=null) {
					
				userName.setText(user.getName());
				} else {
					userName.setText("---");
				}
				Bitmap bm = imageCacheHandler.getBitmapURLResources(user.getProfileImageUrl());
				userImage.setImageBitmap(bm);
			}

		};
		// twitterHandler.getHomeTimeline(com.codepath.apps.client.SimpleTwitterApp.getRestClient());
		TwitterClient client = (TwitterClient) TwitterClient.getInstance(
				TwitterClient.class, getActivity().getBaseContext());

		userHandler.getCurrentUser(client);
	}


}
