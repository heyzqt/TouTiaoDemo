package com.heyzqt.toutiaodemo.bean;

import java.io.Serializable;

/**
 * Created by heyzqt on 2018/5/6.
 *
 * 首页滑动tab的每个Item对象
 */

public class ChannelItem implements Serializable {

	public String title;

	public int rank;

	public boolean isSelected;

	public ChannelItem(String title, int rank, boolean isSelected) {
		this.title = title;
		this.rank = rank;
		this.isSelected = isSelected;
	}

	@Override
	public String toString() {
		return "ChannelItem [name=" + title + ",rank = " + rank
				+ ", selected=" + isSelected + "]";
	}
}
