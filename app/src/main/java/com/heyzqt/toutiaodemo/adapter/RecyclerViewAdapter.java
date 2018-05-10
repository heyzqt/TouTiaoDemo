package com.heyzqt.toutiaodemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.heyzqt.toutiaodemo.R;
import com.heyzqt.toutiaodemo.bean.News;
import com.heyzqt.toutiaodemo.util.GlideApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyzqt on 2018/5/6.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	List<News> datas = new ArrayList<>();

	public RecyclerViewAdapter(List<News> datas) {
		this.datas = datas;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		System.out.println("onCreateViewHolder  ");
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_one,
				parent, false);
		return new ViewHolderOne(view);
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
		System.out.println("onBindViewHolder");
		final ViewHolderOne holderOne = (ViewHolderOne) holder;
		holderOne.title.setText(datas.get(position).getTitle());
		if (datas.get(position).getIsLarge()) {
			holderOne.imgOne.setVisibility(View.GONE);
			holderOne.imgTwo.setVisibility(View.GONE);
			holderOne.imgThree.setVisibility(View.GONE);
			holderOne.bigImg.setVisibility(View.VISIBLE);

			GlideApp.with(holder.itemView.getContext())
					.load(datas.get(position).getPicOne())
					.placeholder(R.mipmap.loading)
					.fitCenter()
					.into(holderOne.bigImg);
		} else {
			if (datas.get(position).getPicList().size() == 0) {
				// 仅仅显示文字
				holderOne.bigImg.setVisibility(View.GONE);
				holderOne.imgOne.setVisibility(View.GONE);
				holderOne.imgTwo.setVisibility(View.GONE);
				holderOne.imgThree.setVisibility(View.GONE);
			} else {
				holderOne.imgOne.setImageResource(R.mipmap.ic_launcher);
				holderOne.imgTwo.setImageResource(R.mipmap.ic_launcher);
				holderOne.imgThree.setImageResource(R.mipmap.ic_launcher);
				holderOne.bigImg.setVisibility(View.GONE);
				holderOne.imgOne.setVisibility(View.VISIBLE);
				holderOne.imgTwo.setVisibility(View.VISIBLE);
				holderOne.imgThree.setVisibility(View.VISIBLE);

				GlideApp.with(holder.itemView.getContext())
						.load(datas.get(position).getPicOne())
						.placeholder(R.mipmap.loading)
						.fitCenter()
						.into(holderOne.imgOne);
				GlideApp.with(holder.itemView.getContext())
						.load(datas.get(position).getPicTwo())
						.placeholder(R.mipmap.loading)
						.fitCenter()
						.into(holderOne.imgTwo);
				GlideApp.with(holder.itemView.getContext())
						.load(datas.get(position).getPicThr())
						.placeholder(R.mipmap.loading)
						.fitCenter()
						.into(holderOne.imgThree);
			}
		}
		holderOne.source.setText(datas.get(position).getSource());
		StringBuilder comment = new StringBuilder();
		comment.append(datas.get(position).getCommentNum()).append("评论");
		holderOne.comment.setText(comment);
		StringBuilder time = new StringBuilder();
		time.append(datas.get(position).getPublishTime()).append("时间");
		holderOne.time.setText(time);

		holderOne.moreBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(view.getContext(), "smile face " + position,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		System.out.println("size = " + datas.size());
		return datas.size();
	}
}
