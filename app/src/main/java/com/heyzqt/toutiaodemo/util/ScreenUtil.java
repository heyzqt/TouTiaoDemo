package com.heyzqt.toutiaodemo.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by heyzqt on 2018/5/6.
 */

public class ScreenUtil {

	public static int getScreenWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public static int getScreenHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}
