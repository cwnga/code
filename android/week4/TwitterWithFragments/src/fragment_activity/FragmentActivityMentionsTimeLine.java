package fragment_activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.codepath.apps.restclienttemplate.R;

public class FragmentActivityMentionsTimeLine extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.fragment_mentions_timeline,
				container, false);

		return myFragmentView;
	}



}
