package com.heyzqt.toutiaodemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heyzqt.toutiaodemo.R;
import com.heyzqt.toutiaodemo.adapter.RecyclerViewAdapter;
import com.heyzqt.toutiaodemo.bean.News;
import com.heyzqt.toutiaodemo.util.DataUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by heyzqt on 2018/5/6.
 */

public class NewsFragment extends Fragment {

//	@BindView(R.id.textview)
//	TextView textview;

	@BindView(R.id.recyclerview)
	RecyclerView mRecyclerView;

	List<News> mNews;

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

		initData();
		initView();
		Bundle bundle = getArguments();
		String str = bundle.getString("text");
		//textview.setText(str);
		return view;
	}

	void initData() {
		mNews = DataUtil.getNewsList();
	}

	void initView() {
		RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNews);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager
				.VERTICAL, false));
		mRecyclerView.setAdapter(adapter);
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
