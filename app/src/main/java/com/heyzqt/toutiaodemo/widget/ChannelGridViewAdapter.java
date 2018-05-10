package com.heyzqt.toutiaodemo.widget;

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

	int curPos = -1;

	boolean isMove = false;

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
//		ViewHolder holder;
//		if (view == null) {
//			view = LayoutInflater.from(viewGroup.getContext()).inflate(
//					R.layout.item_my_channel_grid_view, null, false);
//			holder = new ViewHolder(view);
//			holder.title = view.findViewById(R.id.title);
//			view.setTag(holder);
//			//view.setTag(R.integer.grid_pos, i);
//			//System.out.println("set tag " + i + ", holder = " + holder);
//		} else {
//			holder = (ViewHolder) view.getTag();
////			System.out.println(
////					"get tag " + view.getTag(R.integer.grid_pos) + ",holder = " + holder);
//		}

		view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.item_my_channel_grid_view, null, false);
		TextView title = view.findViewById(R.id.title);

		title.setText(mChannelItems.get(i).title);

		if (i == curPos && isMove) {
			view.setVisibility(View.INVISIBLE);
		}
		return view;
	}

	static class ViewHolder {
		@BindView(R.id.title)
		TextView title;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}

	public void exchangeItemPos(int oldPos, int newPos, boolean isMove) {
		ChannelItem channel = mChannelItems.get(oldPos);
		mChannelItems.remove(oldPos);
		mChannelItems.add(newPos, channel);
		curPos = newPos;
		this.isMove = isMove;
		notifyDataSetChanged();
	}
}
