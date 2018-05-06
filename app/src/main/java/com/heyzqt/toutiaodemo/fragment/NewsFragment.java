package com.heyzqt.toutiaodemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heyzqt.toutiaodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by heyzqt on 2018/5/6.
 */

public class NewsFragment extends Fragment {

	@BindView(R.id.textview)
	TextView textview;

	Context mContext;

	private Unbinder mUnbinder;

	private static final String TAG = "NewsFragment";

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_news,
				null,
				false);
		mUnbinder = ButterKnife.bind(this, view);

		Bundle bundle = getArguments();
		String str = bundle.getString("text");
		textview.setText(str);
		return view;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mContext = context;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mUnbinder.unbind();
	}
}
