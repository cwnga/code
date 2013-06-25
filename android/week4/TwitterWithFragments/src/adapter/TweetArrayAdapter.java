package adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.ImageCacheHandler;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.R.id;
import com.codepath.apps.restclienttemplate.R.layout;
import com.codepath.apps.restclienttemplate.models.Tweet;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
	ImageCacheHandler imageCacheHandler;
	public TweetArrayAdapter(Context context, List<Tweet> tweetList) {
		super(context, R.layout.list_tweet, tweetList);
		imageCacheHandler = new ImageCacheHandler();
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tweet tweet = this.getItem(position);
		
		
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			convertView =  inflator.inflate(R.layout.list_tweet, parent, false);
		} else {
		
		}
		ImageView profileImageView = (ImageView)convertView.findViewById(R.id.icon);
		Log.v("image", tweet.getUser().getProfileBackgroundImageUrl());
		Bitmap bm = this.imageCacheHandler.getBitmapURLResources(tweet.getUser().getProfileImageUrl());
		profileImageView.setImageBitmap(bm);
		TextView userName = (TextView)convertView.findViewById(R.id.textView1); 
		TextView text = (TextView)convertView.findViewById(R.id.textView2); 
		userName.setText(tweet.getUser().getName());
		text.setText(tweet.getBody());
		
		return convertView;
	}
}
