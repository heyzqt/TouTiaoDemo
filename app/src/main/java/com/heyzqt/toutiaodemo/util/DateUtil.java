package com.heyzqt.toutiaodemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by heyzqt on 2018/5/6.
 */

public class DateUtil {

	/*
 * 将字符串转为时间戳
 */
	public static String getTime() {
		String re_time = null;
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Date d;
		d = new Date(currentTime);
		long l = d.getTime();
		String str = String.valueOf(l);
		re_time = str.substring(0, 10);
		return re_time;
	}
}
