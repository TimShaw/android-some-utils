package com.airshiplay.framework.common;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.airshiplay.framework.bean.AppBean;
import com.airshiplay.framework.download.DownloadMgr;
import com.airshiplay.framework.download.DownloadTask;
import com.airshiplay.framework.download.DownloadTaskListener;

/**
 * @author lig
 * @version 1.0
 * @since 1.0 2013-2-6
 */
public class ResStateChange {

	public static void updateProgress(View view, AppBean appBean, Context mContext) {
		DownloadTask task = DownloadMgr.findTask(appBean.resId);
		if (task != null) {
			task.addDownloadListener(new DownloadAppTaskListener(task, view, appBean, mContext), appBean);
		}
	}

	private static class DownloadAppTaskListener extends DownloadTaskListener {
		private View view;
		private AppBean info;

		public DownloadAppTaskListener(DownloadTask task, View view, AppBean appBean, Context mContext) {
			super(task);
			this.info = appBean;
			this.view = view;
		}

		@Override
		public void onStateChange(int state) {
			if (this.view == null)
				return;
			ProgressBar progressButton = (ProgressBar) this.view.findViewWithTag(this.info.resId);
			if (state == DownloadTask.STATE_PAUSED) {
				progressButton.setProgress(0);
			}

			if ((state == DownloadTask.STATE_NET_ERROR) || (state == DownloadTask.STATE_FILE_ERROR)) {

			}
			this.view.postInvalidate();
		}

		@Override
		public void onProgressChange(int percent, long loadSize) {
			ProgressBar progressBar = (ProgressBar) this.view.findViewWithTag(info.getResId());
			if (progressBar != null)
				progressBar.setProgress(percent);
		}
	};
}
