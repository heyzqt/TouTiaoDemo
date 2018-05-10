package com.heyzqt.toutiaodemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heyzqt.toutiaodemo.R;
import com.heyzqt.toutiaodemo.adapter.NewsAdapter;
import com.heyzqt.toutiaodemo.bean.ChannelItem;
import com.heyzqt.toutiaodemo.fragment.NewsFragment;
import com.heyzqt.toutiaodemo.util.ScreenUtil;
import com.heyzqt.toutiaodemo.widget.CustomHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
		View.OnClickListener {

	@BindView(R.id.edit_search)
	EditText mEditText;

	@BindView(R.id.img_camera)
	ImageView mCameraBtn;

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

	@BindView(R.id.viewpager)
	ViewPager mViewPager;

	@BindView(R.id.home_img)
	ImageView mHomeIcon;

	@BindView(R.id.video_img)
	ImageView mVideoIcon;

	@BindView(R.id.toutiao_img)
	ImageView mToutiaoIcon;

	@BindView(R.id.person_img)
	ImageView mPersonIcon;

	int mScreenWidth;
	int itemWidth;
	int saveCurPosition = 0;

	int chooseBottomPos = 0;

	List<ChannelItem> mChannelItems = new ArrayList<>();
	List<NewsFragment> mFragments = new ArrayList<>();

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mScreenWidth = ScreenUtil.getScreenWidth(this);
		// 一行最多放置7个item
		itemWidth = mScreenWidth / 7;

		initData();
		initStatusbarColor();
		initView();
		initBottomView(0);
	}

	void initData() {
		String[] str = {"关注", "推荐", "热点", "视频", "图片", "娱乐",
				"问答", "科技", "懂车帝", "财经", "军事", "体育", "国际", "健康", "特卖", "房产", "小视频", "新时代",
				"时尚", "历史", "直播", "小说", "育儿", "搞笑", "数码"};
		for (int i = 0; i < 13; i++) {
			ChannelItem channelItem = new ChannelItem(str[i], i, false);
			mChannelItems.add(channelItem);
		}
		mChannelItems.get(saveCurPosition).isSelected = true;
	}

	void initStatusbarColor() {
		//设置状态栏的颜色
		Window window = this.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_red));
	}

	void initView() {
		mEditText.setInputType(InputType.TYPE_NULL);

		initTabView();
		initFragments();
	}

	void initBottomView(int pos) {
		switch (pos) {
			case 0:
				mHomeIcon.setSelected(true);
				mVideoIcon.setSelected(false);
				mToutiaoIcon.setSelected(false);
				mPersonIcon.setSelected(false);
				break;
			case 1:
				mHomeIcon.setSelected(false);
				mVideoIcon.setSelected(true);
				mToutiaoIcon.setSelected(false);
				mPersonIcon.setSelected(false);
				break;
			case 2:
				mHomeIcon.setSelected(false);
				mVideoIcon.setSelected(false);
				mToutiaoIcon.setSelected(true);
				mPersonIcon.setSelected(false);
				break;
			case 3:
				mHomeIcon.setSelected(false);
				mVideoIcon.setSelected(false);
				mToutiaoIcon.setSelected(false);
				mPersonIcon.setSelected(true);
				break;
		}
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
			textView.setTextSize(16);
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
							mViewPager.setCurrentItem(j);
							saveCurPosition = j;
						}
					}

					Toast.makeText(MainActivity.this, textView.getText(),
							Toast.LENGTH_SHORT).show();
				}
			});
			mScrollLinearLayout.addView(textView, i, params);
		}
	}

	void initFragments() {
		mFragments.clear();
		int count = mChannelItems.size();
		for (int i = 0; i < count; i++) {
			NewsFragment newsFragment = new NewsFragment();
			Bundle bundle = new Bundle();
			bundle.putString("text", mChannelItems.get(i).title);
			newsFragment.setArguments(bundle);
			mFragments.add(newsFragment);
		}
		mViewPager.setAdapter(
				new NewsAdapter<NewsFragment>(getSupportFragmentManager(), mFragments));
		mViewPager.addOnPageChangeListener(this);
		mViewPager.setCurrentItem(0);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		selectTab(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	void selectTab(int pos) {
		saveCurPosition = pos;

		//更新字体的状态
		for (int i = 0; i < mScrollLinearLayout.getChildCount(); i++) {
			TextView textView = (TextView) mScrollLinearLayout.getChildAt(i);
			if (i == pos) {
				textView.setSelected(true);
			} else {
				textView.setSelected(false);
			}
		}


		mHorizontalScrollView.smoothScrollTo(0, 0);

		Log.i(TAG, "selectTab: pos = " + pos);
		for (int i = 0; i < mScrollLinearLayout.getChildCount(); i++) {
			View focusView = mScrollLinearLayout.getChildAt(i);
			if (saveCurPosition == i) {
				Log.i(TAG, "selectTab: width = " + focusView.getWidth());
				Log.i(TAG, "selectTab: getMeasuredWidth = " + focusView.getMeasuredWidth());
				Log.i(TAG, "selectTab: getLeft = " + focusView.getLeft());
				Log.i(TAG, "selectTab: getRight = " + focusView.getRight());
			}
		}

		//for (int i = 0; i < mScrollLinearLayout.getChildCount(); i++) {
		View checkView = mScrollLinearLayout.getChildAt(pos);
		int k = checkView.getMeasuredWidth();
		int l = checkView.getLeft();
		int i2 = l + k / 2 - mScreenWidth / 2;
		// rg_nav_content.getParent()).smoothScrollTo(i2, 0);
		mHorizontalScrollView.smoothScrollTo(i2, 0);
		// mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
		// mItemWidth , 0);
		//}

	}

	@OnClick(R.id.img_camera)
	public void onClick(View view) {
		Toast.makeText(this, "click camera", Toast.LENGTH_SHORT).show();
	}

	@OnClick(R.id.edit_search)
	public void onClickEdittext(View view) {
		Toast.makeText(this, "click search", Toast.LENGTH_SHORT).show();
	}

	@OnClick(R.id.add_img)
	public void onClick(ImageView view) {
		Intent intent = new Intent(this, ChannelManageActivity.class);
		startActivity(intent);
	}

	@OnClick({R.id.home_img, R.id.video_img, R.id.toutiao_img, R.id.person_img})
	public void onClickBottom(ImageView view) {
		switch (view.getId()) {
			case R.id.home_img:
				initBottomView(0);
				break;
			case R.id.video_img:
				initBottomView(1);
				break;
			case R.id.toutiao_img:
				initBottomView(2);
				break;
			case R.id.person_img:
				initBottomView(3);
				break;
		}
	}
}
