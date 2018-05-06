package com.heyzqt.toutiaodemo.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.heyzqt.toutiaodemo.R;

/**
 * Created by heyzqt on 2018/5/6.
 */

public class ItemLineDividerDecoration extends RecyclerView.ItemDecoration {

	int oritation;

	int dividerHeight;

	Paint paint;

	public ItemLineDividerDecoration(int oritation) {
		super();
		this.oritation = oritation;
		dividerHeight = 2;
		initPaint(R.color.divider_gray);
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);
		if (oritation == LinearLayoutManager.VERTICAL) {
			drawVerticalLine(c, parent);
		}
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		if (oritation == LinearLayoutManager.VERTICAL) {
			outRect.set(0, 0, 0, 2);
		}
	}

	private void initPaint(int dividerColor) {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(dividerColor);
		paint.setStyle(Paint.Style.FILL);
	}

	void drawVerticalLine(Canvas canvas, RecyclerView parent) {
		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();
		for (int i = 0; i < parent.getChildCount(); i++) {
			View child = parent.getChildAt(i);
			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			int top = child.getBottom() + params.bottomMargin;
			int bottom = top + dividerHeight;
			canvas.drawRect(left, top, right, bottom, paint);
		}
	}
}
