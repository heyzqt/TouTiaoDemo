package com.heyzqt.toutiaodemo.util;

import com.heyzqt.toutiaodemo.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyzqt on 2018/5/6.
 */

public class DataUtil {

	public static List<News> getNewsList() {
		List<News> newsList = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			News news = new News();
			news.setId(i);
			news.setNewsId(i);
			news.setCollectStatus(false);
			news.setCommentNum(i + 10);
			news.setInterestedStatus(true);
			news.setLikeStatus(true);
			news.setReadStatus(false);
			news.setNewsCategory("推荐");
			news.setNewsCategoryId(1);
			news.setTitle("喝柠檬水好处多多，其中这6个就让很多人受益");
			List<String> url_list = new ArrayList<String>();
			if (i % 2 == 1) {
				String url1 = "http://p3.pstatp.com/large/pgc-image/1525320600730c2b6990899";
				String url2 = "http://p3.pstatp.com/large/pgc-image/152532063198616d33b449a";
				String url3 = "http://p9.pstatp.com/large/pgc-image/15253206404948bb5097990";
				news.setPicOne(url1);
				news.setPicTwo(url2);
				news.setPicThr(url3);
				news.setSource_url("https://www.toutiao.com/a6550968697664569860/");
				url_list.add(url1);
				url_list.add(url2);
				url_list.add(url3);
				news.setSource("家庭医生在线网");
				news.setSummary(
						"一提起柠檬，想必大家的第一反应应该就会是感到牙齿一软吧。酸!大写的“酸”——这是柠檬给大家最大的印象了。然而你知道吗?柠檬可是一种很好的药物水果，尤其对女性朋友来说，柠檬可并不陌生，柠檬更是很好的一种水果，它的营养价值高，许多女性朋友都喜欢泡柠檬水喝。");
			} else {
				news.setTitle("特朗普兴奋模拟射击：“只要我还当总统，就支持人人配枪”");
				news.setSource_url("https://www.toutiao.com/a6552295642843054595/");
				String url1 = "http://p3.pstatp.com/large/pgc-image/152557851746072664dd92c";
				String url2 = "http://p9.pstatp.com/large/pgc-image/1525578362910a8953fc422";
				String url3 = "http://p1.pstatp.com/large/pgc-image/15255788554554741984440";
				news.setPicOne(url1);
				news.setPicTwo(url2);
				news.setPicThr(url3);
				url_list.add(url1);
				url_list.add(url2);
				url_list.add(url3);
				news.setSource("长安观察");
				news.setSummary("美国总统特朗普常常能给新闻以“惊喜”。当然，对美国普通民众来说，这很有可能是“惊吓”——");


//				news.setTitle("韩媒：美朝会谈地点已经暂定新加坡，时间或在G7峰会之后");
//				news.setSource_url("https://www.toutiao.com/a6552340866160132616/");
//				String url = "http://p1.pstatp.com/large/pgc-image/152558574093475a4471d3b";
//				news.setPicOne(url);
//				url_list.add(url);
//				news.setSource("澎湃新闻");
//				news.setSummary(
//
// "随着美朝会谈日期的临近，一度成悬念的会议地点也有了更加确切的消息。据韩媒报道，目前新加坡已经被暂定为美朝会谈的地点。如果没有意外，美朝会谈很有可能在G7峰会之后的6月9日至15
// 日之间在新加坡举行。");
			}
			news.setPicList(url_list);
			news.setPublishTime(Long.valueOf(i));
			news.setReadStatus(false);
			news.setMark(i);


			if (i == 3) {
				news.setTitle("部落战争强势回归");
				news.setLocal("推广");
				news.setIsLarge(true);
				String url = "http://imgt2.bdstatic.com/it/u=3269155243,2604389213&fm=21&gp=0.jpg";
				news.setSource_url("http://games.sina.com.cn/zl/duanpian/2014-05-21/141297.shtml");
				news.setPicOne(url);
				url_list.clear();
				url_list.add(url);
			} else {
				news.setIsLarge(false);
			}
			if (i == 2 || i == 4) {
				//news.setComment("评论部分，说的非常好。");
				news.setTitle("40多天两度会晤，习近平同金正恩谈了哪些大事");
				String url = "https://www.toutiao.com/a6553545257034711565/";
				news.setSource_url(url);
				url_list.clear();
			}

			if (i <= 2) {
				news.setPublishTime(Long.valueOf(DateUtil.getTime()));
			} else if (i > 2 && i <= 5) {
				news.setPublishTime(Long.valueOf(DateUtil.getTime()) - 86400);
			} else {
				news.setPublishTime(Long.valueOf(DateUtil.getTime()) - 86400 * 2);
			}
			newsList.add(news);
		}

		return newsList;
	}
}
