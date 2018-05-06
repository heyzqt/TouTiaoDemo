package com.heyzqt.toutiaodemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heyzqt.toutiaodemo.R;
import com.heyzqt.toutiaodemo.bean.ChannelItem;
import com.heyzqt.toutiaodemo.util.ScreenUtil;
import com.heyzqt.toutiaodemo.widget.CustomHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.scroll_tab)
	RelativeLayout mScrollAllRelaLayout;

	@BindView(R.id.scroll_father)
	RelativeLayout mScrollFatherLayout;

	@BindView(R.id.horizontalview_linear)
	LinearLayout mScrollLinearLayout;

	@BindView(R.id.horizontalscrollview)
	CustomHorizontalScrollView mHorizontalScrollView;

	@BindView(R.id.add_more_layout)
	LinearLayout mAddmoreLayout;

	@BindView(R.id.add_img)
	ImageView mAddImg;

	int mScreenWidth;
	int itemWidth;

	List<ChannelItem> mChannelItems = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mScreenWidth = ScreenUtil.getScreenWidth(this);
		// 一行最多放置7个item
		itemWidth = mScreenWidth / 7;

		initData();
		initView();
	}

	void initData() {
		String[] str = {"关注", "推荐", "热点", "视频", "图片", "娱乐",
				"问答", "科技", "懂车帝", "财经", "军事", "体育", "国际", "健康", "特卖", "房产", "小视频", "新时代",
				"时尚", "历史", "直播", "小说", "育儿", "搞笑", "数码"};
		for (int i = 0; i < 13; i++) {
			ChannelItem channelItem = new ChannelItem(str[i], i, false);
			mChannelItems.add(channelItem);
		}
		mChannelItems.get(0).isSelected = true;
	}

	void initView() {
		initTabView();
	}

	void initTabView() {
		mScrollLinearLayout.removeAllViews();
		mHorizontalScrollView.setParams(itemWidth, mScrollFatherLayout, mScrollLinearLayout);
		for (int i = 0; i < mChannelItems.size(); i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.leftMargin = 5;
			params.rightMargin = 5;
			//初始化item
			final TextView textView = new TextView(this);
			textView.setGravity(Gravity.CENTER);
			textView.setPadding(5, 5, 5, 5);
			textView.setText(mChannelItems.get(i).title);
			textView.setTextColor(
					getResources().getColorStateList(R.color.top_category_scroll_text_color));
			if (mChannelItems.get(i).isSelected) {
				textView.setSelected(true);
			}
			textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					for (int j = 0; j < mScrollLinearLayout.getChildCount(); j++) {
						View child = mScrollLinearLayout.getChildAt(j);
						if (child != v) {
							child.setSelected(false);
						} else {
							child.setSelected(true);
						}
					}

					Toast.makeText(MainActivity.this, textView.getText(),
							Toast.LENGTH_SHORT).show();
				}
			});
			mScrollLinearLayout.addView(textView, i, params);
		}
	}
}
