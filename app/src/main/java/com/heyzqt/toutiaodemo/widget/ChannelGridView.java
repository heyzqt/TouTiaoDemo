package com.heyzqt.toutiaodemo.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.heyzqt.toutiaodemo.R;

/**
 * Created by heyzqt on 2018/5/7.
 */

public class ChannelGridView extends GridView implements AdapterView.OnItemLongClickListener {

	static final int MODE_NORMAL = 0;
	static final int MODE_DRAG = 1;
	int dragMode = MODE_NORMAL;

	View view;
	View dragView;

	WindowManager mWindowManager;

	WindowManager.LayoutParams mLayoutParams;

	float mWindowX;
	float mWindowY;
	float itemWidthDex;
	float itemHeightDex;

	/**
	 * GridView相对于屏幕左侧的宽度
	 * GridView相对于屏幕上侧的高度
	 */
	int widthWindowDex;
	int heightWindowDex;

	int savePositioin = 0;
	int tempPosition = 0;

	private static final String TAG = "ChannelGridView";

	public ChannelGridView(Context context) {
		this(context, null);
	}

	public ChannelGridView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ChannelGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		this.setOnItemLongClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		if (dragMode == MODE_DRAG) {
			return false;
		}

		Log.i(TAG, "onItemLongClick: ");

		Log.i(TAG, "onItemLongClick: view x = " + view.getX() + ",y = " + view.getY());
		this.view = view;
		this.savePositioin = position;
		this.tempPosition = position;
		this.itemWidthDex = mWindowX - this.getLeft() - view.getLeft();
		this.itemHeightDex = mWindowY - this.getTop() - view.getTop();

		Log.i(TAG, "onItemLongClick: view = " + view);
		//Android系统6.0及以上悬浮窗权限动态申请
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (Settings.canDrawOverlays(getContext())) {
				initWindow();
			} else {
				Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
				getContext().startActivity(intent);
			}
		} else {
			//6.0以下直接开启悬浮窗
			initWindow();
		}
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.i(TAG, "onTouchEvent: ");
		int action = ev.getAction();
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				mWindowX = ev.getRawX();
				mWindowY = ev.getRawY();
				widthWindowDex = (int) (mWindowX - ev.getX());
				heightWindowDex = (int) (mWindowY - ev.getY());
				Log.i(TAG, "onTouchEvent: ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i(TAG, "onTouchEvent: ACTION_MOVE");
				if (dragMode == MODE_DRAG) {
					updateWindow(ev);
				}
				break;
			case MotionEvent.ACTION_UP:
				Log.i(TAG, "onTouchEvent: ACTION_UP");
				if (dragMode == MODE_DRAG) {
					Log.i(TAG, "onTouchEvent: closeWindow()");
					closeWindow();
				}
				break;
		}
		return super.onTouchEvent(ev);
	}

	int count = 1;

	void initWindow() {
		//初始化ImageView
		if (dragView == null) {
			dragView = View.inflate(getContext(), R.layout.item_my_channel_grid_view, null);
			Log.i(TAG, "initWindow: drag view init");
			//dragView = new ImageView(getContext());
			//dragView.setImageBitmap(getDragBitmap(curPositioin));
			TextView textView = dragView.findViewById(R.id.title);
			TextView realText = view.findViewById(R.id.title);
			textView.setText(realText.getText());
		}

		if (mLayoutParams == null) {
			mLayoutParams = new WindowManager.LayoutParams();
			mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
			mLayoutParams.alpha = 0.5f;
			mLayoutParams.format = PixelFormat.RGBA_8888;
			mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
			mLayoutParams.width = (int) (view.getWidth() * 1.2f);
			mLayoutParams.height = (int) (view.getHeight() * 1.2f);
			Log.i(TAG, "initWindow: dragwiew width = " + dragView.getWidth() + ",dragview height"
					+ " = "
					+ dragView.getHeight());
			Log.i(TAG,
					"initWindow: view width = " + view.getWidth() + ",height" + view.getHeight());
			mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
					| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
					| WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
			mLayoutParams.x = view.getLeft() + this.getLeft();
			mLayoutParams.y = view.getTop() + this.getTop();
			Log.i(TAG, "initWindow: view getLeft = " + view.getLeft() + ",this.getleft = "
					+ this.getLeft());
			view.setVisibility(View.INVISIBLE);
		}
		mWindowManager.addView(dragView, mLayoutParams);

		dragMode = MODE_DRAG;
	}

	Bitmap getDragBitmap(int pos) {
		ViewGroup group = (ViewGroup) getChildAt(pos - getFirstVisiblePosition());
		Log.i(TAG, "getDragBitmap: pos = " + pos + " getFirstVisiblePosition = " +
				getFirstVisiblePosition());
		group.destroyDrawingCache();
		group.setDrawingCacheEnabled(true);
		return Bitmap.createBitmap(group.getDrawingCache());
	}

	void updateWindow(MotionEvent event) {
		if (mLayoutParams != null) {
			float x = event.getRawX() - itemWidthDex;
			float y = event.getRawY() - itemHeightDex;
			mLayoutParams.x = (int) x;
			mLayoutParams.y = (int) y;
			mWindowManager.updateViewLayout(dragView, mLayoutParams);
			Log.i(TAG, "updateWindow: event x = " + x + ",y = " + y);
		}

		int x = (int) event.getX();
		int y = (int) event.getY();
		int dropPos = pointToPosition(x, y);
		if (dropPos == tempPosition || dropPos == GridView.INVALID_POSITION) {
			return;
		}
		itemMove(dropPos);
	}

	void closeWindow() {
		if (dragView != null) {
			mWindowManager.removeView(dragView);
			dragView = null;
			mLayoutParams = null;
		}
		itemDrop();
		dragMode = MODE_NORMAL;
	}


	void itemMove(int dropPos) {
		TranslateAnimation translateAnimation;
		//当停止的item位置在原来item位置的前面时
		if (dropPos < tempPosition) {
			for (int i = dropPos; i < tempPosition; i++) {
				View view = getChildAt(i);
				View nextView = getChildAt(i + 1);
				float xValue = (nextView.getLeft() - view.getLeft()) * 1.0f / view.getWidth();
				float yValue = (nextView.getTop() - view.getTop()) * 1.0f / view.getHeight();
				translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
						Animation.RELATIVE_TO_SELF, xValue, Animation.RELATIVE_TO_PARENT, 0,
						Animation.RELATIVE_TO_SELF, yValue);
				translateAnimation.setFillAfter(true);
				translateAnimation.setInterpolator(new LinearInterpolator());
				translateAnimation.setDuration(300);
				if (i == (tempPosition - 1)) {
					translateAnimation.setAnimationListener(animationListener);
				}
				view.startAnimation(translateAnimation);
			}
		} else {
			//当停止的item的位置在原来item位置的后面时
			for (int i = tempPosition + 1; i <= dropPos; i++) {
				View view = getChildAt(i);
				View prevView = getChildAt(i - 1);
				float xValue = (view.getLeft() - prevView.getLeft()) * 1.0f / view.getWidth();
				float yValue = (view.getTop() - prevView.getTop()) * 1.0f / view.getHeight();
				translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
						Animation.RELATIVE_TO_SELF, xValue, Animation.RELATIVE_TO_PARENT, 0,
						Animation.RELATIVE_TO_SELF, yValue);
				translateAnimation.setFillAfter(true);
				translateAnimation.setInterpolator(new LinearInterpolator());
				translateAnimation.setDuration(300);
				if (i == dropPos) {
					translateAnimation.setAnimationListener(animationListener);
				}
				view.startAnimation(translateAnimation);
			}

		}
		tempPosition = dropPos;
	}

	Animation.AnimationListener animationListener = new Animation.AnimationListener() {
		@Override
		public void onAnimationStart(Animation animation) {
			Log.i(TAG, "onAnimationStart: ");
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			Log.i(TAG, "onAnimationEnd: ");
			ListAdapter adapter = getAdapter();
			if (adapter != null && adapter instanceof ChannelGridViewAdapter) {
				((ChannelGridViewAdapter) adapter).exchangeItemPos(savePositioin, tempPosition);
			}
			savePositioin = tempPosition;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}
	};


	//当手指抬起结束排序时
	void itemDrop() {
		if (tempPosition == savePositioin || tempPosition == GridView.INVALID_POSITION) {
			getChildAt(tempPosition).setVisibility(View.VISIBLE);
		} else {
			//更新集合顺序
			ListAdapter adapter = getAdapter();
			if (adapter != null && adapter instanceof ChannelGridViewAdapter) {
				((ChannelGridViewAdapter) adapter).exchangeItemPos(savePositioin, tempPosition);
			}
		}
	}
}
