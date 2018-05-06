package com.heyzqt.toutiaodemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heyzqt.toutiaodemo.R;
import com.heyzqt.toutiaodemo.bean.News;

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
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		System.out.println("onBindViewHolder");
		ViewHolderOne holderOne = (ViewHolderOne) holder;
		holderOne.title.setText(datas.get(position).getTitle());
		holderOne.imgOne.setImageResource(R.mipmap.ic_launcher);
		holderOne.imgTwo.setImageResource(R.mipmap.ic_launcher);
		holderOne.imgThree.setImageResource(R.mipmap.ic_launcher);
		holderOne.source.setText(datas.get(position).getSource());
		StringBuilder comment = new StringBuilder();
		comment.append(datas.get(position).getCommentNum()).append("评论");
		holderOne.comment.setText(comment);
		StringBuilder time = new StringBuilder();
		time.append(datas.get(position).getPublishTime()).append("时间");
		holderOne.time.setText(time);
	}

	@Override
	public int getItemCount() {
		System.out.println("size = " + datas.size());
		return datas.size();
	}
}
