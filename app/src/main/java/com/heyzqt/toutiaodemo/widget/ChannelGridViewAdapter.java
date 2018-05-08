package com.heyzqt.toutiaodemo.widget;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heyzqt.toutiaodemo.R;
import com.heyzqt.toutiaodemo.bean.ChannelItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heyzqt on 2018/5/7.
 */

public class ChannelGridViewAdapter extends BaseAdapter {

	List<ChannelItem> mChannelItems;

	int curPressPos;

	View curPressView;

	OnTouchMoveListener mMoveListener;

	public ChannelGridViewAdapter(List<ChannelItem> datas) {
		this.mChannelItems = datas;
	}

	@Override
	public int getCount() {
		if (mChannelItems == null) {
			return 0;
		}
		return mChannelItems.size();
	}

	@Override
	public Object getItem(int i) {
		return mChannelItems.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(final int i, View view, ViewGroup viewGroup) {
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(viewGroup.getContext()).inflate(
					R.layout.item_my_channel_grid_view, viewGroup, false);
			holder = new ViewHolder(view);
			holder.title = view.findViewById(R.id.title);
			view.setTag(holder);
			view.setTag(R.integer.grid_pos, i);
			//System.out.println("set tag " + i + ", holder = " + holder);
		} else {
			holder = (ViewHolder) view.getTag();
//			System.out.println(
//					"get tag " + view.getTag(R.integer.grid_pos) + ",holder = " + holder);
		}

		holder.title.setText(mChannelItems.get(i).title);
		if (mChannelItems.get(i).isSelected) {
			holder.title.setTextColor(
					ContextCompat.getColor(viewGroup.getContext(), R.color.colorAccent));
		}

		holder.title.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				curPressPos = i;
				curPressView = view;
				return false;
			}
		});

		return view;
	}

	static class ViewHolder {
		@BindView(R.id.title)
		TextView title;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}

	interface OnTouchMoveListener {
		void touchMove();
	}

	public void setOnTouchMoveListener(OnTouchMoveListener listener) {
		mMoveListener = listener;
	}

	public void exchangeItemPos(int oldPos, int newPos) {
		ChannelItem oldItem = mChannelItems.get(oldPos);
		ChannelItem newItem = mChannelItems.get(newPos);
		mChannelItems.set(oldPos, newItem);
		mChannelItems.set(newPos, oldItem);
		notifyDataSetChanged();
	}
}
