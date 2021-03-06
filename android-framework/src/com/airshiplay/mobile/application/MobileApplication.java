package com.airshiplay.mobile.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.view.WindowManager;

import com.airshiplay.mobile.log.LoggerFactory;
import com.airshiplay.mobile.system.MobileDexClassLoader;
import com.airshiplay.mobile.util.ScreenUtil;

/**
 * @author airshiplay
 * @Create Date 2013-3-8
 * @version 1.0
 * @since 1.0
 */
public class MobileApplication extends Application {
	private WindowManager.LayoutParams winLayoutParams = new WindowManager.LayoutParams();
	private static MobileApplication instance;
	private List<Activity> listActivities;

	public static MobileApplication getInstance() {
		return instance;
	}

	public final WindowManager.LayoutParams getWinLayoutParams() {
		return this.winLayoutParams;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		getClassLoader();
		Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(
				new MobileDexClassLoader("/data/app/com.airshiplay.framework-1.apk", "/data/data/com.airshiplay.framework/lib", null,
						getClassLoader()));

		instance = this;
		this.listActivities = new ArrayList<Activity>();
		ScreenUtil.init(getApplicationContext());
		LoggerFactory.init(getApplicationContext());
	}

	public void addAcitvity(Activity activity) {
		this.listActivities.add(activity);
	}

	public void finishAllActivity() {
		Iterator<Activity> itr = this.listActivities.iterator();
		while (itr.hasNext())
			(itr.next()).finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public List<Activity> getList() {
		return this.listActivities;
	}

	public void remove(Activity activity) {
		this.listActivities.remove(activity);
	}
}
