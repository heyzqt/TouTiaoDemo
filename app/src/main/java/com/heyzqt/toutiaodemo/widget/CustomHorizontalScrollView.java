package com.heyzqt.toutiaodemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by heyzqt on 2018/5/6.
 *
 * 首页自定义滑动tab
 */

public class CustomHorizontalScrollView extends HorizontalScrollView {

	/**
	 * tab scrollview 背后的父布局
	 */
	View scrollFatherView;

	/**
	 * tab item 滑动布局
	 */
	View scrollLinearView;


	int itemWidth;

	public CustomHorizontalScrollView(Context context) {
		super(context);
	}

	public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setParams(int width, View view1, View view2) {
		this.itemWidth = width;
		this.scrollFatherView = view1;
		this.scrollLinearView = view2;
	}
}
