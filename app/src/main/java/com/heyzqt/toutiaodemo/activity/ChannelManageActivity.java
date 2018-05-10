package com.heyzqt.toutiaodemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.heyzqt.toutiaodemo.R;
import com.heyzqt.toutiaodemo.bean.ChannelItem;
import com.heyzqt.toutiaodemo.widget.ChannelGridView;
import com.heyzqt.toutiaodemo.widget.ChannelGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by heyzqt on 2018/5/7.
 */

public class ChannelManageActivity extends AppCompatActivity {

	@BindView(R.id.close_btn)
	ImageButton mCloseBtn;

	@BindView(R.id.my_channel_gridview)
	ChannelGridView mChannelGridView;

	ChannelGridViewAdapter mChannelGridViewAdapter;

	String[] channels = {"关注", "推荐", "热点", "视频", "图片", "娱乐",
			"问答", "科技", "懂车帝", "财经", "军事", "体育", "国际", "健康", "特卖", "房产", "小视频", "新时代",
			"时尚", "历史", "直播", "小说", "育儿", "搞笑", "数码"};

	List<ChannelItem> mChannels = new ArrayList<>();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_manage);
		ButterKnife.bind(this);

		initData();
		initView();
		initStatusbarColor();
	}

	void initData() {
		for (int i = 0; i < channels.length; i++) {
			ChannelItem channelItem = new ChannelItem(channels[i], i, false);
			mChannels.add(channelItem);
		}
	}

	void initView() {
		mChannelGridViewAdapter = new ChannelGridViewAdapter(mChannels);
		mChannelGridView.setAdapter(mChannelGridViewAdapter);
	}

	void initStatusbarColor() {
		//设置状态栏的颜色
		Window window = this.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_red));
	}

	@OnClick(R.id.close_btn)
	void onClick() {
		finish();
	}

}
