package com.heyzqt.toutiaodemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.heyzqt.toutiaodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heyzqt on 2018/5/6.
 *
 *
 * yizhang
 */

public class ViewHolderOne extends RecyclerView.ViewHolder {

	@BindView(R.id.title_text)
	TextView title;

	@BindView(R.id.img_one)
	ImageView imgOne;

	@BindView(R.id.img_two)
	ImageView imgTwo;

	@BindView(R.id.img_three)
	ImageView imgThree;

	@BindView(R.id.big_img)
	ImageView bigImg;

	@BindView(R.id.source_text)
	TextView source;

	@BindView(R.id.comment_text)
	TextView comment;

	@BindView(R.id.time_text)
	TextView time;

	@BindView(R.id.more_btn)
	ImageButton moreBtn;

	public ViewHolderOne(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
