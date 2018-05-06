package com.heyzqt.toutiaodemo.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by heyzqt on 2018/5/6.
 */

public class NewsAdapter<T> extends FragmentPagerAdapter {

	List<T> datas;

	public NewsAdapter(FragmentManager fm, List<T> datas) {
		super(fm);
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Fragment getItem(int position) {
		return (Fragment) datas.get(position);
	}
}
